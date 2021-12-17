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
import java.util.Map;

public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.ViewHolder>  {

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
                                    Map<String, Object> u = (Map<String,Object>)snapshot.getValue();
                                    String email = u.get("email").toString();
                                    String Uid = u.get("id").toString();
                                    if(email.equals(name)){
                                        ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        DataSnapshot dataSnapshot = task.getResult();
                                                        String origin = String.valueOf(dataSnapshot.child("notification").getValue());
                                                        FirebaseHelper helper = new FirebaseHelper();
                                                        FirebaseUser cu = FirebaseAuth.getInstance().getCurrentUser();
                                                        if(helper.checkFriend(origin, cu.getEmail())){
                                                            Toast.makeText(v.getContext(), "The other user didn't approve your friend request yet", Toast.LENGTH_LONG).show();
                                                        }else{
                                                            String note = helper.dataCleaner((origin +"/"+cu.getEmail()));
                                                            HashMap User = new HashMap();
                                                            User.put("notification", note);
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
            }
        });


    }

    @Override
    public int getItemCount() {
        return newFriendList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {
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
