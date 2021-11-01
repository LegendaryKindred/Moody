package com.example.moody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.AppViewHolder> {

    String[] notificationData = {};
    LayoutInflater layoutInflater;

    NotificationAdapter(String[] _data){
        notificationData = _data;
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
        String notifications = notificationData[position];
        holder.notification.setText(notifications);

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
        return notificationData.length;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{

        TextView notification;
        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            notification = itemView.findViewById(R.id.notificationTitle);
        }
    }
}
