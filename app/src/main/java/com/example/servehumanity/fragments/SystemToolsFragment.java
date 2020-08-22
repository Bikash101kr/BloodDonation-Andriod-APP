package com.example.servehumanity.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servehumanity.Activity.BloodBankActivity;
import com.example.servehumanity.Activity.LoginActivity;
import com.example.servehumanity.Activity.MainActivity;
import com.example.servehumanity.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class SystemToolsFragment extends Fragment {
    LinearLayout linearLayout;
    ListView lstView;
    TextView tvLogout;

    private SensorManager sensorManager;
    SharedPreferences sharedPreferences;

    List<String> list = new ArrayList<String>();
    final Integer FINGER_PRINT = 0;
    final Integer ACCELEROMETER = 1;
    final Integer GYROSCOPE = 2;
    final Integer PROXIMITY = 3;
    private Boolean isEyeProtectionModeOn = false;
    private Boolean isFingerPrintAuthOn = false;

    Float xAxis;

    public SystemToolsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            list.add("Finger print authentication:\tEnable");
            list.add("Eye protection mode:\tEnable");
        }
    }
    @SuppressLint("ResourceASColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_tools, container, false);
        linearLayout = view.findViewById(R.id.linearLayout);
        lstView = view.findViewById(R.id.lstView);
        tvLogout = view.findViewById(R.id.tvLogOut);

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        final ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1  , list);
        lstView.setAdapter(arrayAdapter);

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == ACCELEROMETER) {
                    if (isEyeProtectionModeOn) {
                        list.set(position, "Eye protection mode:\tEnable");
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        arrayAdapter.notifyDataSetChanged();
                        isEyeProtectionModeOn = false;
                    } else if (!isEyeProtectionModeOn) {
                        list.set(position, "Eye protection mode:\tDisable\t(Shake to see changes)");
                        arrayAdapter.notifyDataSetChanged();
                        isEyeProtectionModeOn = true;
                    }
                }

                if (position == FINGER_PRINT) {
                    sharedPreferences = getContext().getSharedPreferences("Auth", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    if (sharedPreferences.getString("fingerPrint", "false").equals("false")){
                        list.set(position, "Finger print authentication:\tDisable");
                        editor.putString("fingerPrint", "true");
                        editor.commit();
                    } else  if(sharedPreferences.getString("fingerPrint", "false").equals("true")) {
                        Log.i("Bool-else", isEyeProtectionModeOn.toString());
                        list.set(position, "Finger print authentication:\tEnable");
                        editor.putString("fingerPrint", "false");
                        editor.commit();
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            }
        });
        activateAccelerometer();
        return view;
    }

    private void activateAccelerometer() {
        sensorManager = (SensorManager) getSystemService(getContext(), SensorManager.class);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sel = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values = event.values;
                xAxis = values[0];
                if (xAxis < -35 || xAxis > 35) {
                    isEyeProtectionModeOn = !isEyeProtectionModeOn;
                    if (isEyeProtectionModeOn) {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
                        Toast.makeText(getContext(), "Eye protection off", Toast.LENGTH_SHORT).show();
                    } else {
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.warm));
                        Toast.makeText(getContext(), "Eye protection on", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sel, sensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
        }
    }
}