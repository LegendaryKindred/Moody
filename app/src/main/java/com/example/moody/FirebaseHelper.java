package com.example.moody;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FirebaseHelper {

    public FirebaseHelper(){
    }


    public ArrayList<String> friendStringToList(String friends){
        ArrayList<String> result = new ArrayList<>();
        String[] r = friends.split("/");
        for (String s:r) {
            result.add(s);
        }
        return result;
    }

    public boolean checkFriend(String friend, String email){
        if(friend.matches("(.*)" + email + "(.*)")){
            return true;
        }else{
            return  false;
        }
    }

    public String dataCleaner(String list){
        String result = "";
        String[] r = list.split("/");
        for (String email: r) {
            if(email.equals("")){
                continue;
            }else{
                result = result + "/" + email;
            }
        }
        return result;
    }

}
