package com.example.moody;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;

    private EditText editFirstName, editLastName, editUsername, editEmail, editPassword, editPhone;
    private Button register;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);

        auth = FirebaseAuth.getInstance();
        editFirstName = view.findViewById(R.id.registerFirstName);
        editLastName = view.findViewById(R.id.registerLastname);
        editUsername = view.findViewById(R.id.registerUsername);
        editEmail = view.findViewById(R.id.registerEmail);
        editPassword = view.findViewById(R.id.registerPassword);
        editPhone = view.findViewById(R.id.registerPhone);
        register = view.findViewById(R.id.registerButton);
        progressBar = view.findViewById(R.id.registerProgressBar);

        progressBar.setVisibility(view.INVISIBLE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        return view;
    }

    private void registerUser(){

        String firstName = editFirstName.getText().toString().trim();
        String lastName = editLastName.getText().toString().trim();
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String friend = "Moody-Supporter/";
        String birthday = "00/00/0000";
        String status = "Private";
        String mood = "1";
        String notification = "System-Notification/";

        if(firstName.isEmpty()){
            editFirstName.setError("First Name is required!");
            editFirstName.requestFocus();
            return;
        }

        if(lastName.isEmpty()){
            editLastName.setError("Last Name is required!");
            editLastName.requestFocus();
            return;
        }

        if(username.isEmpty()){
            editUsername.setError("Username is required!");
            editUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Email is invalid!");
            editEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }

        if(password.length() < 8){
            editPassword.setError("Password length should be at least 8 characters");
            editPassword.requestFocus();
            return;
        }

        if(phone.isEmpty()){
            editPhone.setError("Phone is required!");
            editPhone.requestFocus();
            return;
        }


        progressBar.setVisibility(getView().VISIBLE);
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = auth.getUid();
                    User user = new User(firstName, lastName, username, email, password, phone, friend, status, birthday, mood, notification, id);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                LoginFragment loginFragment = new LoginFragment();
                                FragmentManager manager = getActivity().getSupportFragmentManager();
                                manager.beginTransaction().replace(R.id.fragment_container, loginFragment , loginFragment.getTag()).commit();
                                progressBar.setVisibility(getView().GONE);
                            }else{
                                Toast.makeText(getActivity(), "Failed to register! Try again!1", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(getView().GONE);
                            }
                        }
                    });


                }else{
                    Toast.makeText(getActivity(), "Failed to register! Try again!2", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(getView().GONE);
                }
            }
        });

    }
}
