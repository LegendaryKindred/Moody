package com.example.moody;
import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ImageButton btnShowDialog = view.findViewById(R.id.showDialog);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(getActivity());

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            Task<Location> task = client.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {

                                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                                    @Override
                                    public void onMapClick(@NonNull LatLng latLng) {
                                        //Initialize lat lng
                                        LatLng latLng2 = new LatLng(location.getLatitude(), location.getLongitude());
                                        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng2));
                                        //create maker options
                                        MarkerOptions options = new MarkerOptions().position(latLng2).title("You are here");
                                        //zoom map
                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 12));
                                        // add marker on map
                                        googleMap.addMarker(options);

                                    }
                                });


                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String Uid = user.getUid();
                                ref.child(Uid).child("friend").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                        if(task.isSuccessful()){
                                            if(task.getResult().exists()){
                                                DataSnapshot dataSnapshot = task.getResult();
                                                String friends = String.valueOf(dataSnapshot.getValue());
                                                FirebaseHelper helper = new FirebaseHelper();
                                                ArrayList<String> friendList = helper.friendStringToList(friends);

                                                ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                        if(task.isSuccessful()){

                                                            DataSnapshot snapshot = task.getResult();
                                                            Iterable<DataSnapshot> users = snapshot.getChildren();
                                                            for (DataSnapshot user: users) {
                                                                Map<String, Object> u = (Map<String,Object>)user.getValue();
                                                                String femail = u.get("email").toString();
                                                                if(friendList.contains(femail)){
                                                                    List<HashMap<String,Object>> reports = (List<HashMap<String,Object>>) u.get("emotion");
                                                                    System.out.println(reports.toString());

                                                                    for (HashMap<String, Object> i: reports) {
                                                                        LatLng latLng = new LatLng((Double) i.get("lat"), (Double)i.get("lng"));

                                                                        System.out.println(latLng.toString());
                                                                        MarkerOptions options = new MarkerOptions().position(latLng).title((String)i.get("description"));
                                                                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
                                                                        googleMap.addMarker(options);
                                                                    }
                                                                }else{
                                                                    continue;
                                                                }

                                                            }
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });


                            }
                        });
                    }
                }
            });
        }else{
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Dialog dialog = new Dialog(getContext());


        btnShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setContentView(R.layout.pop_up_rating);
                dialog.show();
                EditText ratingTitle = dialog.findViewById(R.id.ratingTitle);
                RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
                Button submitRating = dialog.findViewById(R.id.submitRating);
                ImageView btncloseDialog = dialog.findViewById(R.id.closeDialog);

                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    Task<Location> task = client.getLastLocation();
                    task.addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null){
                                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                    @Override
                                    public void onMapReady(@NonNull GoogleMap googleMap) {
                                        //Initialize lat lng
                                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());



                                        submitRating.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                String Uid = user.getUid();
                                                ref.child(Uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                        if(task.isSuccessful()){
                                                            if(task.getResult().exists()){
                                                                DataSnapshot dataSnapshot = task.getResult();
                                                                double lng = latLng.longitude;
                                                                double lat = latLng.latitude;
                                                                int mood = (int)ratingBar.getRating();
                                                                String title = ratingTitle.getText().toString();
                                                                Report rating = new Report(mood, lat, lng, title);
                                                                if(dataSnapshot.child("emotion").getValue() == null){
                                                                    HashMap User = new HashMap();
                                                                    List<Report> el = new ArrayList<Report>();
                                                                    el.add(rating);
                                                                    User.put("emotion", el);
                                                                    User.put("mood",mood);
                                                                    ref.child(Uid).updateChildren(User);
                                                                }else{
                                                                    List<Report> el = new ArrayList<>();
                                                                    ref.child(Uid).child("emotion").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                                                                            DataSnapshot dataSnapshot1 = task.getResult();
                                                                            for(DataSnapshot data : dataSnapshot1.getChildren()){
                                                                                Report report = data.getValue(Report.class);
                                                                                el.add(report);
                                                                            }
                                                                            el.add(rating);

                                                                            int total = 0;
                                                                            for (Report r: el) {
                                                                                total += r.getMood();
                                                                            }
                                                                            int newMood = total/el.size();
                                                                            HashMap User = new HashMap();
                                                                            User.put("emotion", el);
                                                                            User.put("mood",newMood);
                                                                            ref.child(Uid).updateChildren(User);
                                                                        }
                                                                    });
                                                                }


                                                            }
                                                        }
                                                    }
                                                });
                                                dialog.dismiss();
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }else{
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
                btncloseDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }

}