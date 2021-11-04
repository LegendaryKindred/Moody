package com.example.moody;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {
    
    private String[] titles = new String[]{"My Friends", "Requests"};

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MyFriendsListFragment();
            case 1:
                return new RequestFriend();
        }
        return new MyFriendsListFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
