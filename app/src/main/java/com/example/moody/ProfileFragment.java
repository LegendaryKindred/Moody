package com.example.moody;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {
    TextView firstname,lastname,username,email,birthday,password,status;
    private DatabaseReference data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        TextView editBtn = view.findViewById(R.id.profileEdit);
        firstname=view.findViewById(R.id.firstname);
        lastname=view.findViewById(R.id.lastname);
        username=view.findViewById(R.id.username);
        email=view.findViewById(R.id.email);
        birthday=view.findViewById(R.id.birthday);
        password=view.findViewById(R.id.password);
        status=view.findViewById(R.id.status);

        data= FirebaseDatabase.getInstance().getReference();

        //firstname.setText("rui");
        //lastname.setText("");
        //username.setText("");
        //email.setText("");
        //birthday.setText("");
        //password.setText("");
        //status.setText("");


        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileEditFragment profileEditFragment = new ProfileEditFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, profileEditFragment, profileEditFragment.getTag()).commit();
            }
        });

        return view;
    }
}
