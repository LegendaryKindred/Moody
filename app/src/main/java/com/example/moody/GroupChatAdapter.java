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

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {

    private List<ModelClassGroupChat> GCList;

    public GroupChatAdapter(List<ModelClassGroupChat>GCList){this.GCList = GCList;}



    @NonNull
    @Override
    public GroupChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_chat_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupChatAdapter.ViewHolder holder, int position) {
        int resource = GCList.get(position).getGCImage();
        String name = GCList.get(position).getGCName();

        holder.setData(resource, name);

    }

    @Override
    public int getItemCount() {
        return GCList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilepic;
        TextView groupName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.group_chat_pic);
            groupName = itemView.findViewById(R.id.group_chat_name);
        }

        public void setData(int resource, String name) {
            profilepic.setImageResource(resource);
            groupName.setText(name);
        }
    }
}
