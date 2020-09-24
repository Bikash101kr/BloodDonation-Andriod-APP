package com.example.servehumanity.Map;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.servehumanity.R;
import com.example.servehumanity.model.Map;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AutoCompleteTextView acView;
    Button btnSearch;

    List<Map> list=new ArrayList<>();

    CameraUpdate center,zoom;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        acView=findViewById(R.id.acView);
        btnSearch=findViewById(R.id.btnSearch);

        setArrayandAdapter();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(acView.getText().toString())){
                    acView.setError("please enter place name");
                    return;
                }
                int position=search(acView.getText().toString());
                if(position>-1){
                    loadMap(position);

                }
                else{
                    Toast.makeText(MapsActivity.this, "Place not found by that name", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    private void setArrayandAdapter(){

        list.add(new Map(27.7134373,85.3221442,"Naag Pokhari"));
        list.add(new Map(27.7138699,85.3157503,"Naryanhiti palace"));
        list.add(new Map(27.6980136,85.3217048,"Singha Durbar"));

        String[] data=new String[list.size()];

        for(int i=0;i<list.size();i++){
            data[i]=list.get(i).getMarker();
        }

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);
        acView.setAdapter(arrayAdapter);
        acView.setThreshold(1);



    }
    private int search(String item){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getMarker().contains(item)){
                return i;
            }
        }
        return -1;
    }
    private void loadMap(int position){

        if(marker!=null){
            marker.remove();
        }
        double latitude=list.get(position).getLat();
        double longitude=list.get(position).getLon();
        String mymarker=list.get(position).getMarker();

        center=CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude));
        zoom=CameraUpdateFactory.zoomTo(17);

        marker= mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(mymarker));
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);




    }
}