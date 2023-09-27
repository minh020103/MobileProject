package com.example.mobileproject.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mobileproject.R;
import com.example.mobileproject.fragment.admin.AbstractFragment;
import com.example.mobileproject.fragment.admin.MotelRoomOwnerFragment;
import com.example.mobileproject.fragment.admin.PackageFragment;
import com.example.mobileproject.fragment.admin.ManagementFragment;
import com.example.mobileproject.fragment.admin.ProfileFragment;
import com.example.mobileproject.fragment.admin.PendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AdminActivity extends AppCompatActivity {

    private final int MOTEL_ROOM_SCREEN = 1000;
    private final int PACKAGE_SCREEN = 1001;
    private final int PENDING_SCREEN = 1002;
    private final int MANAGEMENT_SCREEN = 1003;
    private final int PROFILE_SCREEN = 1004;

    private int screenID = MOTEL_ROOM_SCREEN;


    AbstractFragment fragment;
    FragmentTransaction transaction;


    private BottomNavigationView bottomNavigationView;


    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_admin_layout);


        drawerLayout = findViewById(R.id.idDrawerAdminLayout);
        getLayoutInflater().inflate(R.layout.main_admin_layout, drawerLayout);
        replaceFragment();
        bottomNavigationView = findViewById(R.id.bnvAdmin);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nvaChuTro:
                        screenID = MOTEL_ROOM_SCREEN;
                        replaceFragment();
                        break;
                    case R.id.nvaGoiDichVu:
                        screenID = PACKAGE_SCREEN;
                        replaceFragment();
                        break;
                    case R.id.nvaChoDuyet:
                        screenID = PENDING_SCREEN;
                        replaceFragment();
                        break;
                    case R.id.nvaQuanLy:
                        screenID = MANAGEMENT_SCREEN;
                        replaceFragment();
                        break;
                    case R.id.nvaTaiKhoan:
                        screenID = PROFILE_SCREEN;
                        replaceFragment();
                        break;
                }
                return true;
            }
        });
    }


    // replace fragment
    private void replaceFragment(){
        if (getSupportFragmentManager().findFragmentByTag(screenID + "") != null) {
            fragment = (AbstractFragment) getSupportFragmentManager().findFragmentByTag(screenID + "");
        } else {
            if (screenID == MOTEL_ROOM_SCREEN) {
                fragment = new MotelRoomOwnerFragment();
            }
            if (screenID == PACKAGE_SCREEN) {
                fragment = new PackageFragment();
            }
            if (screenID == PENDING_SCREEN) {
                fragment = new PendingFragment();
            }
            if (screenID == MANAGEMENT_SCREEN) {
                fragment = new ManagementFragment();
            }
            if (screenID == PROFILE_SCREEN) {
                fragment = new ProfileFragment();
            }
        }

        if (fragment != null) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.idFragmentAdmin, fragment, screenID + "");
            if (getSupportFragmentManager().findFragmentByTag(screenID + "") == null) {
                transaction.addToBackStack(screenID + "");
            }
            transaction.commit();
        }
    }

}