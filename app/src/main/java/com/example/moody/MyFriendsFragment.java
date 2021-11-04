package com.example.moody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MyFriendsFragment extends Fragment {

    VPAdapter vpAdapter;
    TabLayout tabLayout;
    ViewPager2 vp2;

    private String[] titles = new String[]{"My Friends", "Requests"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_friends_fragment, container, false);

        vpAdapter = new VPAdapter(getActivity());
        tabLayout = view.findViewById(R.id.myFriendTab);
        vp2 = view.findViewById(R.id.viewPagerMyFriends);

        vp2.setAdapter(vpAdapter);

        new TabLayoutMediator(tabLayout, vp2, ((tab, position) -> tab.setText(titles[position]))).attach();

        return view;
    }
}
