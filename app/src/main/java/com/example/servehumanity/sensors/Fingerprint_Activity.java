package com.example.servehumanity.sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.servehumanity.Activity.DashboardActivity;
import com.example.servehumanity.Activity.Login;
import com.example.servehumanity.R;

import java.util.concurrent.Executor;

public class Fingerprint_Activity extends AppCompatActivity {
    TextView tvMsg;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_);

        tvMsg = findViewById(R.id.tvMsg);
        btnLogin = findViewById(R.id.btnLogin);

         androidx.biometric.BiometricManager biometricManager =  androidx.biometric.BiometricManager.from(this);
         switch (biometricManager.canAuthenticate()){
             case BiometricManager.BIOMETRIC_SUCCESS:
                 tvMsg.setText("You can you finger print to login!");
                 tvMsg.setTextColor(Color.parseColor("#fafafa"));
                 break;
             case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                 tvMsg.setText("The device does not have fingerprint sensor");
             case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                 tvMsg.setText("The biometric sensor is currently not available.");

             case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                 tvMsg.setText("Your device do not have any finger prints saved!");
         }

        Executor executor = ContextCompat.getMainExecutor(this);
        final androidx.biometric.BiometricPrompt biometricPrompt = new androidx.biometric.BiometricPrompt(Fingerprint_Activity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });
        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login")
                .setDescription("Use your fingerprint to login to your app")
                .setNegativeButtonText("Cancel")
                .build();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                biometricPrompt.authenticate(promptInfo);
            }
        });
    }
}