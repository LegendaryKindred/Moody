package com.example.moody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FriendRequestFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassFriendRequest> friendRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_request, container, false);

        initData();
        recyclerView = view.findViewById(R.id.friendRequest);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // load the list items to the recycler view
        adapter = new FriendRequestAdapter(friendRequest);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    // hardcode to check if list shows
    private void initData() {
        friendRequest = new ArrayList<>();

        friendRequest.add(new ModelClassFriendRequest(R.drawable.profile_icon, "James", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "John", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Jannah", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Hollie", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Susan", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Ross", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Rachel", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Cooper", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Gabi", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Hannah", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Yolanda", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Josh", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Crystal", R.drawable.add_btn, R.drawable.block));
        friendRequest.add(new ModelClassFriendRequest(R.drawable.img, "Fallon", R.drawable.add_btn, R.drawable.block));

    }
}