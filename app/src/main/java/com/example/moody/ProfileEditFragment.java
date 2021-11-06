package com.example.moody;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class ProfileEditFragment extends Fragment{
    DatePickerDialog.OnDateSetListener listener;
    TextView birthday_date, done;


    EditText firstName, lastName, username, password;

    private FirebaseUser user;
    private DatabaseReference ref;
    private String Uid;

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
                String date = month + "/" + day + "/" + year;
                birthday_date.setText(date);
            }
        };

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn = firstName.getText().toString().trim();
                String ln = lastName.getText().toString().trim();
                String un = username.getText().toString().trim();
                String pw = password.getText().toString().trim();

                updateProfile(fn, ln, un, pw);
            }
        });


        return view;
    }

    public void updateProfile(String fn, String ln, String un, String pw){

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Users");
        Uid = user.getUid();

        HashMap User = new HashMap();
        User.put("firstName", fn);
        User.put("lastName", ln);
        User.put("username", un);
        User.put("password", pw);

        ref.child(Uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.fragment_container, profileFragment, profileFragment.getTag()).commit();
                    Toast.makeText(getActivity(), "Update successfully", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Update failed", Toast.LENGTH_LONG).show();

                }
            }
        });



    }



}
