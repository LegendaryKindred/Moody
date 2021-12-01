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

public class GroupChatFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassGroupChat> groupChatList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.group_chat, container, false);

        initData();
        recyclerView = view.findViewById(R.id.group_chat_list);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // load the list items to the recycler view
        adapter = new GroupChatAdapter(groupChatList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        return view;
    }

    private void initData() {
        groupChatList = new ArrayList<>();

        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 1"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 2"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 3"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 4"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 5"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 6"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 7"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 8"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 9"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 10"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 11"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 12"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 13"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 14"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 15"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 16"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 17"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 18"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 19"));
        groupChatList.add(new ModelClassGroupChat(R.drawable.img, "Group Name 20"));

    }
}
