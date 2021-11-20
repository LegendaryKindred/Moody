package com.example.moody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.ViewHolder> {

    private List<ModelClassNewFriends> newFriendList;

    public NewFriendAdapter(List<ModelClassNewFriends>newFriendList){this.newFriendList = newFriendList;}



    @NonNull
    @Override
    public NewFriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_friend_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewFriendAdapter.ViewHolder holder, int position) {
        int resource = newFriendList.get(position).getNewFriendImage();
        String name = newFriendList.get(position).getNewFriendName();
        int add_pic = newFriendList.get(position).getNewFriendAdd();

        holder.setData(resource, name, add_pic);

    }

    @Override
    public int getItemCount() {
        return newFriendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilepic;
        TextView friendName;
        ImageButton add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.new_friend_pic);
            friendName = itemView.findViewById(R.id.new_friend_name);
            add = itemView.findViewById(R.id.new_friend_add);
        }

        public void setData(int resource, String name, int add_pic) {
            profilepic.setImageResource(resource);
            friendName.setText(name);
            add.setImageResource(add_pic);
        }
    }
}
