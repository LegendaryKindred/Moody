package com.example.moody;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
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

import java.util.Calendar;
import java.util.HashMap;

public class ProfileEditFragment extends Fragment{
    DatePickerDialog.OnDateSetListener listener;
    TextView birthday_date, done, status;
    Switch statusSwitch;

    EditText firstName, lastName, username, password;
    private final static String DEFAULT_PUBLIC = "Public";
    private final static String DEFAULT_PRIVATE = "Private";
    private FirebaseUser user;
    private DatabaseReference ref;
    private String Uid;
    private String birthday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_edit_fragment, container, false);
        birthday_date = (TextView) view.findViewById(R.id.birthday_date);

        done = view.findViewById(R.id.editDone);
        firstName = view.findViewById(R.id.editFirstName);
        lastName = view.findViewById(R.id.editLastName);
        username = view.findViewById(R.id.editUsername);
        password = view.findViewById(R.id.editPassword);
        status = view.findViewById(R.id.editStatus);
        statusSwitch = view.findViewById(R.id.statusSwitch);

        //read data from firebase so user dont need to type the data they dont want to change
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            ref = FirebaseDatabase.getInstance().getReference("Users");
            Uid = user.getUid();


            ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().exists()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            String fn = String.valueOf(dataSnapshot.child("firstName").getValue());
                            String ln = String.valueOf(dataSnapshot.child("lastName").getValue());
                            String un = String.valueOf(dataSnapshot.child("username").getValue());
                            String pw = String.valueOf(dataSnapshot.child("password").getValue());
                            String bd = String.valueOf(dataSnapshot.child("birthday").getValue());

                            firstName.setText(fn);
                            lastName.setText(ln);
                            username.setText(un);
                            password.setText(pw);
                            birthday_date.setText(bd);

                        } else {
                            Toast.makeText(getActivity(), "Fail to read user data2", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Fail to read user data1", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        //status switch
        statusSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    status.setText(DEFAULT_PUBLIC);
                }else{
                    status.setText(DEFAULT_PRIVATE);
                }
            }
        });

        //birthday date picker
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        birthday_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        listener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                birthday = month + "/" + day + "/" + year;
                birthday_date.setText(birthday);
            }
        };

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = firstName.getText().toString().trim();
                String ln = lastName.getText().toString().trim();
                String un = username.getText().toString().trim();
                String pw = password.getText().toString().trim();
                String st = status.getText().toString().trim();

                if(firstName.length() > 40){
                    firstName.setError("firstName should be no more than 40 characters");
                    firstName.requestFocus();
                    return;
                }

                if(lastName.length() > 40){
                    lastName.setError("lastName should be no more than 40 characters");
                    lastName.requestFocus();
                    return;
                }
                
                if(username.length() > 40){
                    username.setError("firstName should be no more than 40 characters");
                    username.requestFocus();
                    return;
                }

                if(password.length() < 8){
                    password.setError("Password length should be at least 8 characters");
                    password.requestFocus();
                    return;
                }

                if(birthday==null){birthday="Nothing yet";}
                String bd = birthday;
                updateProfile(fn, ln, un, pw, bd, st);

            }
        });


        return view;
    }

    private void updateProfile(String fn, String ln, String un, String pw, String bd, String st){

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        Uid = user.getUid();

        HashMap User = new HashMap();
        User.put("firstName", fn);
        User.put("lastName", ln);
        User.put("username", un);
        User.put("password", pw);
        User.put("birthday", bd);
        User.put("status", st);

        user.updatePassword(pw).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Password update successfully", Toast.LENGTH_LONG).show();
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container, profileFragment, profileFragment.getTag()).commit();
                }else{
                    Toast.makeText(getActivity(), "Password update failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        ref.child(Uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(getActivity(), "Profile Update successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_LONG).show();

                }
            }
        });



    }



}
