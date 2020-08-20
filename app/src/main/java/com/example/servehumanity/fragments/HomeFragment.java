package com.example.servehumanity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.servehumanity.Activity.DonationCampsActivity;
import com.example.servehumanity.Activity.EmergencyActivity;
import com.example.servehumanity.R;

public class HomeFragment extends Fragment {
    Button btnCamps, btnEmergency;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        btnCamps = view.findViewById(R.id.btnCamps);
        btnEmergency = view.findViewById(R.id.btnEmergency);

        btnCamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonationCampsActivity.class);
                startActivity(intent);
            }
        });

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EmergencyActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}