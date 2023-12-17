package com.example.mobileproject.activity.admin;

import android.Manifest;
import android.app.Activity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.RealPathUtil;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceDung;
import com.example.mobileproject.datamodel.Quan;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDistrictActivity extends AppCompatActivity {
    AppCompatImageView ic_back;

    ImageView imgQuan;
    EditText editQuan;
    TextView name_quan;
    Button btnSuaQuan;
    Button btnKhoaQuan;
    Integer idQuan;
    Integer trangThai;
    public static final String TAG = Manifest.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private Uri mUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_district);
        anhXa();
        showThongTinTienIch();
        setSuKien();
    }

    private boolean kiemTra(EditText editQuan){
        if(!editQuan.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
    private void setSuKien(){
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        btnSuaQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra(editQuan)){
                    if(mUri!=null){
                        capNhatQuan1(editQuan,mUri);
                    }else{
                        capNhatQuan2(editQuan);
                    }
                }else{
                    thongBao("Thiếu Ảnh Hoặc Tên Tiện Ích");
                }
            }
        });
        btnKhoaQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Integer> call = ApiServiceDung.apiServiceDung.capnhattrangthaiquan(idQuan);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        thongBao("Cập Nhật Thành Công " );
                    }
                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        thongBao("Cập Nhật Thất Bại" );
                    }
                });
            }
        });
    }
    private void layTrangThai(Integer trangThai){
        this.trangThai= trangThai;
    }
    private void capNhatQuan1(EditText editQuan, Uri mUri){
        String strRealPath = RealPathUtil.getRealPath(this,mUri);
        File file = new File(strRealPath);

        String ten = editQuan.getText().toString();

        RequestBody idQuanForm = RequestBody.create(MediaType.parse("multipart/form-data"),idQuan+"");

        RequestBody tenQuan = RequestBody.create(MediaType.parse("multipart/form-data"),ten);

        RequestBody trangQuan = RequestBody.create(MediaType.parse("multipart/form-data"),trangThai+"");

        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part mulPart = MultipartBody.Part.createFormData("hinh",file.getName(),requestBodyImage);
        ApiServiceDung.apiServiceDung.capnhatquan(idQuanForm,tenQuan,trangQuan,mulPart).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                thongBao("Up load thành công Quan");
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                thongBao("Không Up được");
            }
        });
    }
    private void capNhatQuan2(EditText editQuan){
        String ten = editQuan.getText().toString();

        RequestBody idQuanForm = RequestBody.create(MediaType.parse("multipart/form-data"),idQuan+"");

        RequestBody tenQuan = RequestBody.create(MediaType.parse("multipart/form-data"),ten);

        RequestBody trangQuan = RequestBody.create(MediaType.parse("multipart/form-data"),trangThai+"");
        ApiServiceDung.apiServiceDung.capnhatquan2(idQuanForm,tenQuan,trangQuan).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                thongBao("Up load thành công Quận");
            }
            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                thongBao("Không Up được");
            }
        });
    }

    private void onClickRequestPermission(){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            openGallery();
        }else{
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permission,MY_REQUEST_CODE);
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG,"onActivityResult");
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data==null){
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
//                        Toast.makeText(getApplicationContext(),mUri.getPath(),Toast.LENGTH_SHORT).show();
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imgQuan.setImageBitmap(bitmap);

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }

    }


    private void showThongTinTienIch(){
        idQuan = getIntent().getIntExtra("key",-1);
        Call<Quan> call= ApiServiceDung.apiServiceDung.layQuanTheoID(idQuan);
        call.enqueue(new Callback<Quan>() {
            @Override
            public void onResponse(Call<Quan> call, Response<Quan> response) {
                layTrangThai(response.body().getTrangThai());
                editQuan.setText(response.body().getTenQuan().toString());
                name_quan.setText("Chỉnh Sửa " + response.body().getTenQuan().toString());
                Glide.with(EditDistrictActivity.this).load(Const.DOMAIN+response.body().getHinh()).into(imgQuan);
                if(response.body().getTrangThai()==0){
                    btnKhoaQuan.setText("Khóa Tiên Ích Lại");
                    btnKhoaQuan.setBackgroundColor(getResources().getColor(R.color.red));
                }else{
                    btnKhoaQuan.setText("Mở Khóa Tiện Ích");
                    btnKhoaQuan.setBackgroundColor(getResources().getColor(R.color.black));
                }
            }
            @Override
            public void onFailure(Call<Quan> call, Throwable t) {
                thongBao("Không Thể Load Dữ Liệu");
            }
        });
    }

    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(EditDistrictActivity.this);
        builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }
    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        name_quan = findViewById(R.id.text_name_quan);
        imgQuan = findViewById(R.id.imgQuan);
        editQuan= findViewById(R.id.editQuan);
        btnSuaQuan = findViewById(R.id.btnSuaQuan);
        btnKhoaQuan = findViewById(R.id.btnKhoaQuan);
    }
}
