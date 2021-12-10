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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


public class MyFriendsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassNewFriends> newFriendList;
    FirebaseUser cu;
    ArrayList <String> friendList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_friends_fragment, container, false);

        cu = FirebaseAuth.getInstance().getCurrentUser();
        newFriendList = new ArrayList<>();

        recyclerView = view.findViewById(R.id.myFriendList);
        FirebaseHelper helper = new FirebaseHelper(cu);

//        friendList = helper.getFriendList(helper.getFriendString(cu));

//        for (String email: friendList) {
//            newFriendList.add(new ModelClassNewFriends(R.drawable.img, email, R.drawable.add));
//        }

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // load the list items to the recycler view
        adapter = new NewFriendAdapter(newFriendList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;


    }
}
