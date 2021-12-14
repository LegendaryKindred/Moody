package com.example.moody;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
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

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().exists()){
                                DataSnapshot dataSnapshot = task.getResult();
                                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                                    User user = snapshot.getValue(User.class);
                                    String Uid;
                                    if(user.getEmail().equals(name)){
                                        Uid = user.getId();
                                        ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        DataSnapshot dataSnapshot = task.getResult();
                                                        String origin = String.valueOf(dataSnapshot.child("friend").getValue());
                                                        FirebaseHelper helper = new FirebaseHelper();
                                                        FirebaseUser cu = FirebaseAuth.getInstance().getCurrentUser();
                                                        if(helper.checkFriend(origin, cu.getEmail())){
                                                            Toast.makeText(v.getContext(), "Friend approve error", Toast.LENGTH_LONG).show();
                                                        }else{
                                                            String friend = helper.dataCleaner((origin +"/"+cu.getEmail()));
                                                            HashMap User = new HashMap();
                                                            User.put("friend", friend);
                                                            ref.child(Uid).updateChildren(User);
                                                        }
                                                    } else {
                                                        Toast.makeText(v.getContext(), "Can't add the user", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    Toast.makeText(v.getContext(), "Can't add the user", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    }
                                }
                            }
                        }else{
                            Toast.makeText(v.getContext(), "Can't add the user", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                String uid = u.getUid();

                ref.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                // add to the my friend list
                                String origin = String.valueOf(dataSnapshot.child("friend").getValue());
                                FirebaseHelper helper = new FirebaseHelper();
                                if(helper.checkFriend(origin, name)){
                                    System.out.println("This user is your friend already");
                                    Toast.makeText(v.getContext(), "This user is your friend already", Toast.LENGTH_LONG).show();
                                }else{
                                    String newfriend = helper.dataCleaner((origin +"/"+name));
                                    HashMap User = new HashMap();
                                    User.put("friend", newfriend);
                                    ref.child(uid).updateChildren(User);
                                }
                                // still need to implement the part to modify the name's friend list
                                String note = String.valueOf((dataSnapshot.child("notification").getValue()));
                                String newnote = helper.dataCleaner(note.replace(name, ""));
                                HashMap User = new HashMap();
                                User.put("notification", newnote);
                                ref.child(uid).updateChildren(User);
                                }
                        } else {
                            Toast.makeText(v.getContext(), "Can't accept the user", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        holder.block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                String Uid = user.getUid();
                ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                FirebaseHelper helper = new FirebaseHelper();
                                String note = String.valueOf((dataSnapshot.child("notification").getValue()));
                                String newnote = helper.dataCleaner(note.replace((name), ""));
                                HashMap User = new HashMap();
                                User.put("notification", newnote);
                                ref.child(Uid).updateChildren(User);
                            }
                        } else {
                            Toast.makeText(v.getContext(), "Can't accept the user", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.friend_request_pic);
            friendName = itemView.findViewById(R.id.friend_request_name);
            add = itemView.findViewById(R.id.friend_add);
            block = itemView.findViewById(R.id.friend_block);
        }

        public void setData(int resource, String name, int add_pic, int block_pic) {
            profilepic.setImageResource(resource);
            friendName.setText(name);
            add.setImageResource(add_pic);
            block.setImageResource(block_pic);
        }
    }
}
