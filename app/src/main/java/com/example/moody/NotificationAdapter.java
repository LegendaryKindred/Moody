package com.example.moody;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.AppViewHolder> {

    String[] notiTitleData = {};
    String[] notiDetailData = {"new user","from your friend","system ","Friend Request","Cheer Up","Recent Moods"};

    LayoutInflater layoutInflater;

    NotificationAdapter(String[] _data){
        notiTitleData = _data;
    }


    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        //this view is passed to the view holder and getItemCount item is used to
        // create the uis for the given array
        View view = layoutInflater.inflate(R.layout.noti_items, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        String notifications = notiTitleData[position];
        String notificationDetail = notiDetailData[position];
        holder.noti_title.setText(notifications);
        holder.noti_detail.setText(notificationDetail);

        //onclick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if onclick is working
                Toast.makeText(view.getContext(), notifications,Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return notiTitleData.length;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{

        TextView noti_title;
        TextView noti_detail;
        ImageView checkBox;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            noti_title = itemView.findViewById(R.id.notificationTitle);
            noti_detail = itemView.findViewById(R.id.notificationDetail);
            checkBox = itemView.findViewById(R.id.notiCheck);
        }
    }
}
