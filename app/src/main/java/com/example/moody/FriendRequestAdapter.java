package com.example.moody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class FriendRequestAdapter extends RecyclerView.Adapter<FriendRequestAdapter.ViewHolder> {

    private List<ModelClassFriendRequest> friendRequests;

    public FriendRequestAdapter(List<ModelClassFriendRequest>friendRequests){this.friendRequests = friendRequests;}


    @NonNull
    @Override
    public FriendRequestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_request_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestAdapter.ViewHolder holder, int position) {
        int resource = friendRequests.get(position).getImage();
        String name = friendRequests.get(position).getName();
        int add_pic = friendRequests.get(position).getAdd();
        int block_pic = friendRequests.get(position).getBlock();

        holder.setData(resource, name, add_pic, block_pic);
    }

    @Override
    public int getItemCount() {
        return friendRequests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilepic;
        TextView friendName;
        ImageButton add;
        ImageButton block;
        private FirebaseUser user;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.friend_request_pic);
            friendName = itemView.findViewById(R.id.friend_request_name);
            add = itemView.findViewById(R.id.friend_add);
            block = itemView.findViewById(R.id.friend_block);

            user = FirebaseAuth.getInstance().getCurrentUser();

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseHelper helper = new FirebaseHelper(user);
                    helper.addFriend(user, (String) friendName.getText());
                }
            });
        }

        public void setData(int resource, String name, int add_pic, int block_pic) {
            profilepic.setImageResource(resource);
            friendName.setText(name);
            add.setImageResource(add_pic);
            block.setImageResource(block_pic);
        }
    }
}
