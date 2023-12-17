package com.example.mobileproject.activity.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.example.mobileproject.R;
import com.example.mobileproject.RealPathUtil;
import com.example.mobileproject.api.Const;
import com.example.mobileproject.api.admin.ApiServiceNghiem;
import com.example.mobileproject.datamodel.Admin;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileAdminActivity extends AppCompatActivity {
    AppCompatImageView ic_back;
    RoundedImageView imgAdmin;
    EditText edtTenAdmin;
    EditText edtSdtAdmin;
    EditText edtSTKAdmin;
    EditText edtTenNguoiThuHuong;
    Button btnXacNhan;

    public static final String TAG = Manifest.class.getName();
    private static final int MY_REQUEST_CODE = 10;
    private Uri mUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_admin);
        anhXa();
        imgAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        setUpDuLieu();
        setSuKien();

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
                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            imgAdmin.setImageBitmap(bitmap);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
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





    private boolean kiemTra(){
        if(!edtTenAdmin.getText().toString().isEmpty()&&!edtSdtAdmin.getText().toString().isEmpty()&&
        !edtSTKAdmin.getText().toString().isEmpty()&&!edtTenNguoiThuHuong.getText().toString().isEmpty()
        ){
            return true;
        }
        return false;
    }
    private void setSuKienXacNhan(Admin admin){
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kiemTra()){



                    String ten = edtTenAdmin.getText().toString();
                    String sdt = edtSdtAdmin.getText().toString();
                    String stk = edtSTKAdmin.getText().toString();
                    String tenNH = edtTenNguoiThuHuong.getText().toString();

                    RequestBody tenAdmin = RequestBody.create(MediaType.parse("multipart/form-data"),ten);
                    RequestBody sdtAdmin = RequestBody.create(MediaType.parse("multipart/form-data"),sdt);
                    RequestBody stkAdmin = RequestBody.create(MediaType.parse("multipart/form-data"),stk);
                    RequestBody tenNHAdmin = RequestBody.create(MediaType.parse("multipart/form-data"),tenNH);


                    if(mUri==null){
                        Call<Integer> call = ApiServiceNghiem.apiService.capNhatThongTinAdmin2(1,tenAdmin,sdtAdmin,stkAdmin,tenNHAdmin);
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                thongBao("Cập nhật thông tin thành công");
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                thongBao("Cập nhật thông tin thất bại!");
                            }
                        });
                    }else{
                        String strRealPath = RealPathUtil.getRealPath(getApplicationContext(),mUri);
                        File file = new File(strRealPath);
                        RequestBody requestBodyImage = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                        MultipartBody.Part mulPart = MultipartBody.Part.createFormData("hinh",file.getName(),requestBodyImage);
                        Call<Integer> call = ApiServiceNghiem.apiService.capNhatThongTinAdmin(1,tenAdmin,sdtAdmin,stkAdmin,tenNHAdmin,mulPart);
                        call.enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {
                                thongBao("Thành COng");
                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {
                                thongBao("That Bai");
                            }
                        });
                    }


                }else{
                    thongBao("Thiếu Dữ Liệu!");
                }
            }
        });
    }
    private void setSuKien(){

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setUpDuLieu(){
        Call<Admin> call = ApiServiceNghiem.apiService.layThongTinAdmin(1);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.body()!=null) {
                    Admin admin = response.body();
                    Glide.with(EditProfileAdminActivity.this).load(Const.DOMAIN + admin.getHinh()).into(imgAdmin);
                    edtTenAdmin.setText(admin.getTen());
                    edtSdtAdmin.setText(admin.getSoDienThoai());
                    edtSTKAdmin.setText(admin.getSoTaiKhoanNganHang());
                    edtTenNguoiThuHuong.setText(admin.getTenChuTaiKhoan());
                    setSuKienXacNhan(admin);
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }
    private void thongBao(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("OKE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();
    }
    private void thongBaoYesNo(String message, String matKhauMoi){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.show();
    }
    private void anhXa(){
        ic_back = findViewById(R.id.icon_back);
        imgAdmin = findViewById(R.id.imgAdmin);
        edtTenAdmin = findViewById(R.id.edtEditTenAdmin);
        edtSdtAdmin = findViewById(R.id.edtEditSDTAdmin);
        edtSTKAdmin = findViewById(R.id.edtSTKAdmin);
        edtTenNguoiThuHuong = findViewById(R.id.edtTenNguoiThuHuong);
        btnXacNhan = findViewById(R.id.btnXacNhan);
    }
}
