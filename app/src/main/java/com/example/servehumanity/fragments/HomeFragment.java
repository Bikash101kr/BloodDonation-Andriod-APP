package com.example.servehumanity.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.servehumanity.Activity.ContactUsActivity;
import com.example.servehumanity.Activity.DonateBloodActivity;
import com.example.servehumanity.Activity.DonationCampsActivity;
import com.example.servehumanity.Activity.EmergencyActivity;
import com.example.servehumanity.Activity.RequestBloodActivity;
import com.example.servehumanity.Map.MapsActivity;
import com.example.servehumanity.R;

public class HomeFragment extends Fragment {
    Button btnCamps, btnEmergency, btnContact, btnDonate, btnRequest, btnBloodBank;
    public static Boolean isUpdated = false;
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
        btnDonate = view.findViewById(R.id.btnDonate);
        btnRequest = view.findViewById(R.id.btnRequest);
        btnCamps = view.findViewById(R.id.btnCamps);
        btnEmergency = view.findViewById(R.id.btnEmergency);
        btnContact = view.findViewById(R.id.btnContact);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonateBloodActivity.class);
                startActivity(intent);
            }
        });
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RequestBloodActivity.class);
                startActivity(intent);
            }
        });
        btnBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

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

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}