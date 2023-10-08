package com.example.mobileproject.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobileproject.R;

public class InfomationConfirmMotelRoomOwner extends AppCompatActivity {

    ImageView imgBack;
    LinearLayout llCall;

    TextView tvSDT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_confirm_account_motel_room_owner_layout);

        imgBack = findViewById(R.id.imgBack);
        tvSDT = findViewById(R.id.tvSDT);
        llCall = findViewById(R.id.llCall);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String phone = "tel:"+tvSDT.getText();

        llCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(phone));
                startActivity(callIntent);
            }
        });
    }
}