package com.example.moody;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {
    GoogleMap map;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //Google Map
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);

        //Async Map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //when map is loaded
                LatLng madison = new LatLng(43.0730517,-89.4012302);
                map = googleMap;
                map.addMarker(new MarkerOptions().position(madison).title("madison"));
                map.moveCamera(CameraUpdateFactory.newLatLng(madison));
                map.setMinZoomPreference(12);

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        //Marker
                        MarkerOptions markerOptions = new MarkerOptions();
                        //set position of marker
                        markerOptions.position(latLng);
                        googleMap.addMarker(markerOptions);
                    }

                });

            }
        });

        return view;
    }
}
