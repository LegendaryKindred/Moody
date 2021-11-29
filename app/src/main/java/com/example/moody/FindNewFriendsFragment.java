
package com.example.moody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FindNewFriendsFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassNewFriends> newFriendList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.find_new_friends_fragment, container, false);

        initData();
        recyclerView = view.findViewById(R.id.newFriendList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // load the list items to the recycler view
        adapter = new NewFriendAdapter(newFriendList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    // hardcode to check if list shows
    private void initData() {
        newFriendList = new ArrayList<>();

        newFriendList.add(new ModelClassNewFriends(R.drawable.profile_icon, "James", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "John", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Jannah", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Hollie", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Susan", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Ross", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Rachel", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Cooper", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Gabi", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Hannah", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Yolanda", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Josh", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Crystal", R.drawable.add));
        newFriendList.add(new ModelClassNewFriends(R.drawable.img, "Fallon", R.drawable.add));

    }
}
