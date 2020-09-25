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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng ServeHumanity = new LatLng(27.7134373,85.3221442);
        mMap.addMarker(new MarkerOptions().position(ServeHumanity).title("Head Office serve Humanity"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ServeHumanity));
    }
    private void setArrayandAdapter(){

        list.add(new Map(27.7134373,85.3221442,"Kathmandu Serve Humanity"));
        list.add(new Map(25.444253, 77.659933,"Chitwan Serve Humanity"));
        list.add(new Map(26.460199, 87.280342," Biratnagar Serve Humanity"));
        list.add(new Map(28.237988, 83.995590,"Pokhara Serve Humanity"));
        list.add(new Map(28.063919, 81.619583,"Nepalganj Humanity"));
        list.add(new Map(28.692397, 80.620806,"Dhangadi Serve Humanity"));

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
        String myMarker=list.get(position).getMarker();

        center=CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude));
        zoom=CameraUpdateFactory.zoomTo(15);

        marker= mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(myMarker));
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
        mMap.getUiSettings().setZoomControlsEnabled(true);




    }
}