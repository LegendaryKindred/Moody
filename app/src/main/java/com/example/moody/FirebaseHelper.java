package com.example.moody;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FirebaseHelper {
    private FirebaseUser user;


    public FirebaseHelper(FirebaseUser user){
        this.user = user;
    }


    public boolean addFriend(FirebaseUser user, String email){
        final boolean[] result = new boolean[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        String Uid = user.getUid();
        String friendString = getFriendString(user);
        friendString += email + "/";
        HashMap User = new HashMap();
        User.put("friend", friendString);
        ref.child(Uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    result[0] = true;
                }else{
                    result[0] = false;
                }
            }
        });
        return result[0];
    }

    public boolean deleteFriend(FirebaseUser user , String email){
        ArrayList<String> friendList = getFriendList(getFriendString(user));
        String r = "";
        for(String e: friendList){
            if(e.equals(email)){
                continue;
            }else{
                r = r + e+ "/";
            }
        }

        final boolean[] result = new boolean[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        String Uid = user.getUid();
        HashMap User = new HashMap();
        User.put("friend", r);
        ref.child(Uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    result[0] = true;
                }else{
                    result[0] = false;
                }
            }
        });
        return result[0];
    }

//    public ArrayList<String> searchFriend(FirebaseUser user, String email){
//
//    }

    private String getFriendString(FirebaseUser user){
        final String[] friend = new String[1];
        String Uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        friend[0] = String.valueOf(dataSnapshot.child("friend").getValue());
                    }else{
                    }
                }else{
                }
            }
        });
        return friend[0];
    }

    public ArrayList<String> getFriendList(String friendString){
        return new ArrayList<String>(Arrays.asList(friendString.split("/")));
    }
}
