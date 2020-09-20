package com.example.servehumanity.Map;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.example.servehumanity.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ServeHumanityBloodBank = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(ServeHumanityBloodBank).title("Serve Humanity Blood Bank"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ServeHumanityBloodBank));
    }
}