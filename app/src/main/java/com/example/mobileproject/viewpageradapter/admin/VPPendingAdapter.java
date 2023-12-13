package com.example.mobileproject.viewpageradapter.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileproject.R;
import com.example.mobileproject.fragment.admin.pending.AbstractFragment;
import com.example.mobileproject.fragment.admin.pending.MotelRoomDeleteFragment;
import com.example.mobileproject.fragment.admin.pending.OwnerAccountFragment;
import com.example.mobileproject.fragment.admin.pending.PackageRegisterFragment;



public class VPPendingAdapter extends FragmentStateAdapter {

    private final int OWNER_ACCOUNT_ID = 0;
    private final int PACKAGE_REGISTER_ID = 1;

    AbstractFragment fragment;
    FragmentActivity fragmentActivity;
    FragmentTransaction transaction;

    public VPPendingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return replaceFragment(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


    // replace fragment
    private Fragment replaceFragment(int screenID){
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(screenID + "") != null) {
            fragment = (AbstractFragment) fragmentManager.findFragmentByTag(screenID + "");
        } else {

            switch (screenID){
                case OWNER_ACCOUNT_ID:
                    fragment = new OwnerAccountFragment();
                    break;
                case PACKAGE_REGISTER_ID:
                    fragment = new PackageRegisterFragment();
                    break;
                default:
                    fragment = new OwnerAccountFragment();
                    break;
            }
        }
        if (fragment != null) {
            transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
            if (fragmentActivity.getSupportFragmentManager().findFragmentByTag(screenID + "") == null) {
                transaction.addToBackStack(screenID + "");
            }
            transaction.commit();
        }
        return fragment;
    }

}
