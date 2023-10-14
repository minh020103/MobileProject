package com.example.mobileproject.activity.admin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.admin.ApiServivePhuc;
import com.example.mobileproject.datamodel.Banner;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBannerActivity extends AppCompatActivity {
    public static final String TAG = AddBannerActivity.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private ImageView imgBanner, imgViewBack, imgViewFromApi;
    private Button btnUpLoad;
    private Uri mUri;
    private ProgressDialog mProgressDialog;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        //Du anh lieu tu gallery
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgBanner.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_banner_layout);

        btnUpLoad = findViewById(R.id.btn_upload);
        imgBanner = findViewById(R.id.img_from_gallery);
        imgViewBack = findViewById(R.id.img_view_back);
        imgViewFromApi = findViewById(R.id.img_from_api);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait ...");
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        btnUpLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    callApiUpLoadFile();
                }
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        //Xin cap phep
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();

        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission, MY_REQUEST_CODE);
        }
    }

    //Nguoi dung cho phep or tu choi
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            //Khi nguoi dung cho phep
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }

    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Seletec Picture"));
    }

    private void callApiUpLoadFile() {
        mProgressDialog.show();

        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        Log.e("phuc", strRealPath);
        File file = new File(strRealPath);

        RequestBody requestBodyBanner = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyBanner = MultipartBody.Part.createFormData("hinh", file.getName(), requestBodyBanner);

        ApiServivePhuc.apiService.uploadFileBanner(multipartBodyBanner).enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                mProgressDialog.dismiss();
                Banner banner = response.body();
                if(banner != null){
                    Glide.with(AddBannerActivity.this).load(banner.getHinhBanner()).into(imgViewFromApi);
                }
            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {
                mProgressDialog.dismiss();
                Toast.makeText(AddBannerActivity.this, "Call api fail", Toast.LENGTH_SHORT).show();
            }
        });


//        ApiServivePhuc.apiService.uploadFileBanner(multipartBodyBanner).enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                mProgressDialog.dismiss();
//                Toast.makeText(AddBannerActivity.this, "Call api success", Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//                mProgressDialog.dismiss();
//                Toast.makeText(AddBannerActivity.this, "Call api fail", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}