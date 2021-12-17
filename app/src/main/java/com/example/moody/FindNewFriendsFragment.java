
package com.example.moody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.List;

public class FindNewFriendsFragment extends Fragment {
    EditText friendSearchET;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassNewFriends> newFriendList;
    Button searchButton;
    String searchString;
    FirebaseUser cu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_new_friends_fragment, container, false);
        cu = FirebaseAuth.getInstance().getCurrentUser();

        newFriendList = new ArrayList<>();
        friendSearchET = view.findViewById(R.id.friendSearchEditText);
        searchButton = view.findViewById(R.id.friendSearchButton);
        recyclerView = view.findViewById(R.id.newFriendList);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                searchString = friendSearchET.getText().toString().trim();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                ref.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            DataSnapshot snapshot = task.getResult();
                            Iterable<DataSnapshot> users = snapshot.getChildren();
                            for (DataSnapshot user: users) {
                                User u = (User)user.getValue();
                                if(u.getEmail().contains(searchString)){
                                    newFriendList.add(new ModelClassNewFriends(R.drawable.img, u.getEmail(), R.drawable.add));
                                }
                            }
                        }
                    }
                });

                layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                // load the list items to the recycler view
                adapter = new NewFriendAdapter(newFriendList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                newFriendList = new ArrayList<>();
            }
        });


        return view;
    }
}
