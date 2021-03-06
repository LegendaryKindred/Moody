package com.example.moody;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


        recyclerView = view.findViewById(R.id.myfriendlist);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // load the list items to the recycler view
        adapter = new MyFriendListAdapter(myFriendList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }


}