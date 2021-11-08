package com.example.moody;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFriendsListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassMyFriends> myFriendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_friends_list, container, false);

        initData();
        recyclerView = view.findViewById(R.id.myfriendlist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // load the list items to the recycler view
        adapter = new MyFriendListAdapter(myFriendList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    // hardcode to check if list shows
    private void initData() {
        myFriendList = new ArrayList<>();

        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "James", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "John", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Jannah", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Hollie", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Susan", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Ross", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Rachel", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Cooper", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Gabi", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Hannah", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Yolanda", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Josh", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Crystal", "1"));
        myFriendList.add(new ModelClassMyFriends(R.drawable.img, "Fallon", "1"));

    }
}