package com.example.servehumanity.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;

public class AccelerometerActivity extends AppCompatActivity {
  private SensorManager sensorManager;
  private TextView tvSensor, tvValue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_accelerometer);
    tvSensor = findViewById(R.id.tvSensor);
    tvValue = findViewById(R.id.tvValue);
    tvSensor.setText("Accelerometer:");

    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    SensorEventListener sel = new SensorEventListener() {
      @Override
      public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        String xAxis = "x : " + values[0];
        String yAxis = "y : " + values[1];
        String zAxis = "z : " + values[2];

        tvValue.setText(xAxis + " " + yAxis + " " + zAxis);

        Log.i("Read X-Axis", xAxis);
      }

      @Override
      public void onAccuracyChanged(Sensor sensor, int accuracy) {

      }
    };

    if (sensor != null) {
      sensorManager.registerListener(sel, sensor, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    } else {
      tvSensor.setText("Accelerometer does not exist in your phone.");
      tvValue.setVisibility(View.GONE)    ;
    }
  }
}