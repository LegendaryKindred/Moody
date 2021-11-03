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

public class RegisterFragment extends Fragment {
    //field
    EditText firstname,lastname,username,email,phonenumber;
    Button register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        firstname=view.findViewById(R.id.firstname);
        lastname=view.findViewById(R.id.lastname);
        username=view.findViewById(R.id.username);
        email=view.findViewById(R.id.email);
        phonenumber=view.findViewById(R.id.phonenumber);
        register=view.findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //added new user
            }
        });
        return view;
    }
}
