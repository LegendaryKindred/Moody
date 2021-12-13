package com.example.moody;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> c253ad07de99ed0183d479c509353203cc357f4e

public class MyFriendsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassNewFriends> newFriendList;
    FirebaseUser cu;
    DatabaseReference ref;
<<<<<<< HEAD
=======
    ArrayList <String> friendList;
>>>>>>> c253ad07de99ed0183d479c509353203cc357f4e

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_friends_fragment, container, false);

        cu = FirebaseAuth.getInstance().getCurrentUser();
        newFriendList = new ArrayList<>();
<<<<<<< HEAD
        recyclerView = view.findViewById(R.id.myFriendList);


=======

        recyclerView = view.findViewById(R.id.myFriendList);

        String test;
>>>>>>> c253ad07de99ed0183d479c509353203cc357f4e
        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(cu.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
<<<<<<< HEAD
                if(task.isSuccessful()){
                    DataSnapshot dataSnapshot = task.getResult();
                    String friends  = String.valueOf(dataSnapshot.child("friend").getValue());
                    FirebaseHelper helper = new FirebaseHelper();
                    ArrayList<String> friendList = helper.friendStringToList(friends);
                    System.out.println(friendList.toString());
                    for ( String email: friendList) {
                        newFriendList.add(new ModelClassNewFriends(R.drawable.img, email, R.drawable.add));
                    }
                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    // load the list items to the recycler view
                    adapter = new NewFriendAdapter(newFriendList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    newFriendList = new ArrayList<>();

                    Toast.makeText(getActivity(), "friend data read successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "friend data read unsuccessful", Toast.LENGTH_LONG).show();
=======
                if (!task.isSuccessful()) {
                    DataSnapshot dataSnapshot = task.getResult();
                    String test = String.valueOf(dataSnapshot.child("friend").getValue());
                }
                else {
                    Log.d("firebase friend error", String.valueOf(task.getResult().getValue()));
>>>>>>> c253ad07de99ed0183d479c509353203cc357f4e
                }
            }
        });



<<<<<<< HEAD
=======
        //System.out.println("This is test for grab friend from backend"+test);


//        friendList = helper.getFriendList(helper.getFriendString(cu));

//        for (String email: friendList) {
//            newFriendList.add(new ModelClassNewFriends(R.drawable.img, email, R.drawable.add));
//        }

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        // load the list items to the recycler view
        adapter = new NewFriendAdapter(newFriendList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


>>>>>>> c253ad07de99ed0183d479c509353203cc357f4e
        return view;


    }
}
