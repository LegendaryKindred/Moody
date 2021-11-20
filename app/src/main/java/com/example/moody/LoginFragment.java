package com.example.moody;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginFragment extends Fragment {
    private Button login_button;
    private EditText login_email, login_password;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        login_button = view.findViewById(R.id.login_button);
        login_email = view.findViewById(R.id.login_username);
        login_password = view.findViewById(R.id.login_password);
        progressBar = view.findViewById(R.id.loginPprogressBar);
        auth = FirebaseAuth.getInstance();

        progressBar.setVisibility(view.INVISIBLE);

        TextView registerBtn  = view.findViewById(R.id.goToRegister);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterFragment registerFragment = new RegisterFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, registerFragment, registerFragment.getTag()).commit();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });



        return view;
    }

    private void login(){
        String email = login_email.getText().toString().trim();
        String password = login_password.getText().toString().trim();
        if(email.isEmpty()){
            login_email.setError("Email is required!");
            login_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            login_email.setError("Email is invalid!");
            login_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            login_password.setError("Password is required!");
            login_password.requestFocus();
            return;
        }

        if(password.length() < 8){
            login_password.setError("Password length should be at least 8 characters");
            login_password.requestFocus();
            return;
        }

        progressBar.setVisibility(getView().VISIBLE);

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to user profile
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container, homeFragment, homeFragment.getTag()).commit();
                }else{
                    Toast.makeText(getActivity(), "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(getView().GONE);
                }
            }
        });

    }
}
