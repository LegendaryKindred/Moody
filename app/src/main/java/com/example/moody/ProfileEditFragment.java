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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

public class ProfileEditFragment extends Fragment{
    DatePickerDialog.OnDateSetListener listener;
    TextView birthday_date;
    TextView done;
    EditText firstname,lastname,username,password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_edit_fragment, container, false);
        birthday_date = (TextView) view.findViewById(R.id.birthday_date);
        firstname=view.findViewById(R.id.firstname);
        lastname=view.findViewById(R.id.lastname);
        username=view.findViewById(R.id.username);
        password=view.findViewById(R.id.password);
        done=view.findViewById(R.id.done);

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
            public void onClick(View v) {
                //update new information
                ProfileFragment ProfileFragment = new ProfileFragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.fragment_container, ProfileFragment, ProfileFragment.getTag()).commit();
            }
        });


        return view;
    }



}
