package com.example.servehumanity.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;

public class ProximitySensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mgr;
    private Sensor proximity;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        proximity = mgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        text = findViewById(R.id.text);
    }

    @Override
    protected void onResume() {
        mgr.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause() {
        mgr.unregisterListener(this, proximity);
        super.onPause();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] == 0) text.setText("object is detected ");
        else text.setText("There is nothing near the sensor ...");
    }

}