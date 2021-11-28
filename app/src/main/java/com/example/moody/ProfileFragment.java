package com.example.moody;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    private TextView firstName, lastName, username, password, email, birthday, status;
    private FirebaseUser user;
    private DatabaseReference ref;
    private String Uid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        TextView editBtn = view.findViewById(R.id.profileEdit);
        TextView logoutBtn = view.findViewById(R.id.profileLogout);

        firstName = view.findViewById(R.id.profileFirstName);
        lastName = view.findViewById(R.id.profileLastName);
        username = view.findViewById(R.id.profileUsername);
        password = view.findViewById(R.id.profilePassword);
        email = view.findViewById(R.id.profileEmail);
        birthday = view.findViewById(R.id.profileBirthday);
        status = view.findViewById(R.id.profileStatus);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            ref = FirebaseDatabase.getInstance().getReference("Users");
            Uid = user.getUid();


            ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        if(task.getResult().exists()){
                            DataSnapshot dataSnapshot = task.getResult();
                            String fn = String.valueOf(dataSnapshot.child("firstName").getValue());
                            String ln = String.valueOf(dataSnapshot.child("lastName").getValue());
                            String un = String.valueOf(dataSnapshot.child("username").getValue());
                            String pw = String.valueOf(dataSnapshot.child("password").getValue());
                            String em = String.valueOf(dataSnapshot.child("email").getValue());
                            String bd = String.valueOf(dataSnapshot.child("birthday").getValue());
                            String st = String.valueOf(dataSnapshot.child("status").getValue());

                            firstName.setText(fn);
                            lastName.setText(ln);
                            username.setText(un);
                            password.setText(pw);
                            email.setText(em);
                            birthday.setText(bd);
                            status.setText(st);

                            Toast.makeText(getActivity(), "Successfully read data", Toast.LENGTH_LONG).show();


                        }else{
                            Toast.makeText(getActivity(), "Fail to read user data2", Toast.LENGTH_LONG).show();

                        }
                    }else{
                        Toast.makeText(getActivity(), "Fail to read user data1", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }else{
            firstName.setText("Please log in first");
            lastName.setText("Please log in first");
            username.setText("Please log in first");
            password.setText("Please log in first");
            email.setText("Please log in first");
        }



        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, profileEditFragment, profileEditFragment.getTag()).commit();

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                LoginFragment loginFragment = new LoginFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, loginFragment, loginFragment.getTag()).commit();

            }
        });


        return view;
    }
}
