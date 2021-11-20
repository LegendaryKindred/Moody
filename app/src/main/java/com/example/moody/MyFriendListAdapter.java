package com.example.moody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyFriendListAdapter extends RecyclerView.Adapter<MyFriendListAdapter.ViewHolder> {

    private List<ModelClassMyFriends> myFriendList;

    public MyFriendListAdapter(List<ModelClassMyFriends>myFriendList){this.myFriendList = myFriendList;}

    @NonNull
    @Override
    public MyFriendListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myfriendlist_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendListAdapter.ViewHolder holder, int position) {
        int resource = myFriendList.get(position).getImageview();
        String name = myFriendList.get(position).getTextviewName();
        String mood = myFriendList.get(position).getTextViewMood();

        holder.setData(resource, name, mood);

    }

    @Override
    public int getItemCount() {
        return myFriendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilepic;
        TextView friendName;
        TextView friendmood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.myfriendpic);
            friendName = itemView.findViewById(R.id.myfriendlistname);
            friendmood = itemView.findViewById(R.id.myfriendmood);
        }

        public void setData(int resource, String name, String mood) {
            profilepic.setImageResource(resource);
            friendName.setText(name);
            friendmood.setText(mood);
        }
    }
}
