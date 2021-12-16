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
import com.google.android.gms.maps.MapFragment;
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

public class HomeFragment extends Fragment {

    FusedLocationProviderClient client;
    SupportMapFragment supportMapFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        ImageButton btnShowDialog = view.findViewById(R.id.showDialog);

        //Google Map
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
                                //Initialize lat lng
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                                //create maker options
                                MarkerOptions options = new MarkerOptions().position(latLng).title("You are here");

                                //zoom map
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                                // add marker on map
                                googleMap.addMarker(options);
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
                                                                    ref.child(Uid).updateChildren(User);
                                                                }else{
                                                                    List<Report> el = (List<Report>)dataSnapshot.child("emotion").getValue();
                                                                    HashMap User = new HashMap();
                                                                    el.add(rating);
                                                                    User.put("emotion", el);
                                                                    ref.child(Uid).updateChildren(User);
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