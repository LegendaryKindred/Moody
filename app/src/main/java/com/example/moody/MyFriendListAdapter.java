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
        int remove_pic = myFriendList.get(position).getFriendRemove();
        String mood = myFriendList.get(position).getTextViewMood();

        holder.setData(resource, name, remove_pic, mood);

        holder.remove.setOnClickListener(new View.OnClickListener() {
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
                                String origin = String.valueOf(dataSnapshot.child("friend").getValue());
                                FirebaseHelper helper = new FirebaseHelper();
                                String newfriend = helper.dataCleaner(origin.replace((name), ""));
                                HashMap User = new HashMap();
                                User.put("friend", newfriend);
                                ref.child(Uid).updateChildren(User);

                            } else {
                                Toast.makeText(v.getContext(), "Can't add the user", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(v.getContext(), "Can't add the user", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return myFriendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profilepic;
        TextView friendName;
        ImageButton remove;
        TextView friendmood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilepic = itemView.findViewById(R.id.myfriendpic);
            friendName = itemView.findViewById(R.id.myfriendlistname);
            friendmood = itemView.findViewById(R.id.myfriendmood);
            remove = itemView.findViewById(R.id.my_friend_block);
        }

        public void setData(int resource, String name, int remove_pic, String mood) {
            profilepic.setImageResource(resource);
            friendName.setText(name);
            remove.setImageResource(remove_pic);
            friendmood.setText(mood);
        }
    }
}
