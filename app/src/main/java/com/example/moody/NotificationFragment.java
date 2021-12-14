package com.example.moody;

import android.os.Bundle;
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

public class NotificationFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager layoutManager;
    List<ModelClassFriendRequest> noteList;
    FirebaseUser cu;
    DatabaseReference ref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_fragment, container, false);

        cu = FirebaseAuth.getInstance().getCurrentUser();
        noteList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.notificationList);

        ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(cu.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(task.isSuccessful()){
                    DataSnapshot dataSnapshot = task.getResult();
                    String friends  = String.valueOf(dataSnapshot.child("notification").getValue());
                    FirebaseHelper helper = new FirebaseHelper();
                    ArrayList<String> friendList = helper.friendStringToList(friends);
                    System.out.println(friendList.toString());
                    for ( String email: friendList) {

                        if(email.contains("System-Notification") || email.equals("")){
                            continue;
                        }else{
                            noteList.add(new ModelClassFriendRequest(R.drawable.img,email, R.drawable.add, R.drawable.block ));
                        }

                    }
                    layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    // load the list items to the recycler view
                    adapter = new FriendRequestAdapter(noteList);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    noteList = new ArrayList<>();


                    Toast.makeText(getActivity(), "friend data read successful", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "friend data read unsuccessful", Toast.LENGTH_LONG).show();

                }
            }
        });
        return view;
    }
}
