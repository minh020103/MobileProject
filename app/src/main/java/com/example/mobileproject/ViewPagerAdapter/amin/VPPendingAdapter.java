package com.example.mobileproject.ViewPagerAdapter.amin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mobileproject.R;
import com.example.mobileproject.fragment.admin.pending.AbstractFragment;
import com.example.mobileproject.fragment.admin.ManagementFragment;
import com.example.mobileproject.fragment.admin.MotelRoomOwnerFragment;
import com.example.mobileproject.fragment.admin.PackageFragment;
import com.example.mobileproject.fragment.admin.PendingFragment;
import com.example.mobileproject.fragment.admin.ProfileFragment;
import com.example.mobileproject.fragment.admin.pending.MotelRoomDeleteFragment;
import com.example.mobileproject.fragment.admin.pending.OwnerAccountFragment;
import com.example.mobileproject.fragment.admin.pending.PackageRegisterFragment;



public class VPPendingAdapter extends FragmentStateAdapter {

    private final int OWNER_ACCOUNT_ID = 0;
    private final int PACKAGE_REGISTER_ID = 1;
    private final int MOTEL_ROOM_DELETE_ID = 2;

    AbstractFragment fragment;
    FragmentActivity fragmentActivity;

    public VPPendingAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    public VPPendingAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public VPPendingAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return replaceFragment(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }


    // replace fragment
    private Fragment replaceFragment(int screenID){
        FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(screenID + "") != null) {
            fragment = (AbstractFragment) fragmentManager.findFragmentByTag(screenID + "");
        } else {

            switch (screenID){
                case 0:
                    fragment = new OwnerAccountFragment();
                    break;
                case 1:
                    fragment = new PackageRegisterFragment();
                    break;
                case 2:
                    fragment = new MotelRoomDeleteFragment();
                    break;
                default:
                    fragment = new OwnerAccountFragment();
                    break;
            }
        }
        return fragment;
    }

}
