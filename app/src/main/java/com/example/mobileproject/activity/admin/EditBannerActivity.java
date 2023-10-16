package com.example.mobileproject.activity.admin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.api.Const;
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

public class EditBannerActivity extends AppCompatActivity {
    public static final String TAG = EditBannerActivity.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private Button btnUpdate;
    private ImageView imgBanner, imgViewBack;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    String mBanner;
    int id;
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
        setContentView(R.layout.admin_edit_banner_layout);

        Intent intent = getIntent();
        mBanner = intent.getStringExtra("hinh");
        id = intent.getIntExtra("id",0);

        btnUpdate = findViewById(R.id.btn_update);
        imgBanner = findViewById(R.id.img_from_gallery);
        imgViewBack = findViewById(R.id.img_view_back);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please wait ...");

        Glide.with(this.getLayoutInflater().getContext()).load(Const.DOMAIN + mBanner).into(imgBanner);
        imgViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mUri != null){
                    callApiEditBanner();
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
    private void callApiEditBanner() {
        String strRealPath = RealPathUtil.getRealPath(this, mUri);
        Log.e("phuc", strRealPath);
        File file = new File(strRealPath);

        RequestBody requestBodyBanner = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartBodyBanner = MultipartBody.Part.createFormData("hinh", file.getName(), requestBodyBanner);
        ApiServivePhuc.apiService.editBanner(id, multipartBodyBanner).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                alertSuccess("Cập nhật Banner thành công ");
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                alertFail("Cập nhật Banner thất bại");
            }
        });


    }
    private void alertSuccess(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Success")
                .setIcon(R.drawable.iconp_check_banner)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
        builder.create();
        builder.show();
    }
    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.iconp_error_banner)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }


}