package com.example.servehumanity.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.UserAPI;
import com.example.servehumanity.channel.CreateChannel;
import com.example.servehumanity.response.UserResponse;
import com.example.servehumanity.sensors.Fingerprint_Activity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvRegister;
    CheckBox checkbox1;
    boolean validationError;
    AlertDialog.Builder builder;
    String username;
    String password;
    NotificationManagerCompat notificationManagerCompat;


    private boolean validateUser(String username, String password) {
        if (username.length() > 30) {
            edtUsername.setError("Username cannot be longer than 30 characters");
            edtUsername.requestFocus();
            return true;
        }

        if (username.length() <= 0) {
            edtUsername.setError("Please enter username");
            edtUsername.requestFocus();
            return true;
        }

        edtUsername.clearFocus();
        edtUsername.setError(null);

        if (password.length() <= 0) {
            edtPassword.setError("Please enter password");
            edtPassword.requestFocus();
            return true;
        }

        edtPassword.clearFocus();
        edtPassword.setError(null);
        return false;
    }


    private void displayNotificationSuccess() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Login")
                .setContentText("Login Successful")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        notificationManagerCompat.notify(1, builder.build());
    }

    private void displayNotificationFail() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CreateChannel.CHANNEL_2)
                .setSmallIcon(R.drawable.ic_baseline_message_24)
                .setContentTitle("Login")
                .setContentText("Login Fail. Please check username and password.")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);
        notificationManagerCompat.notify(2, builder.build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        checkbox1 = findViewById(R.id.checkbox1);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        notificationManagerCompat = NotificationManagerCompat.from(this);
        CreateChannel createChannel = new CreateChannel(this);
        createChannel.createChannel();

        SharedPreferences savedData = getSharedPreferences("User", Context.MODE_PRIVATE);
        Log.i("savedData", savedData.getString("username", "No Data"));

        if (savedData.getString("username", "").isEmpty()) {
            checkbox1.setChecked(false);
        } else {
            checkbox1.setChecked(true);
            edtUsername.setText(savedData.getString("username", ""));
            edtPassword.setText(savedData.getString("password", ""));
   }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtUsername.getText().toString().trim();
                password = edtPassword.getText().toString().trim();

               validationError = validateUser(username, password);
                if (validationError) return;

                UserAPI userAPI = URL.getInstance().create(UserAPI.class);
                Call<UserResponse> call = userAPI.checkUser(username, password);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (!response.isSuccessful()) {
                            edtUsername.setError("Username or password is incorrect");
                            edtUsername.requestFocus();
                            displayNotificationFail();
                            return;
                        }
                        URL.token = response.body().getToken();
                        URL.userID = username;

                        SharedPreferences isFingerPrintAuthOn = getSharedPreferences("Auth", MODE_PRIVATE);

                        if (isFingerPrintAuthOn.getString("fingerPrint", "false").equals("true")) {
                            startActivity(new Intent(LoginActivity.this, Fingerprint_Activity.class));
                        } else {
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                        }
                        displayNotificationSuccess();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.i("error", Objects.requireNonNull(t.getLocalizedMessage()));
                        builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Unable to connect to server at the time. Please try again later.")
                                .setPositiveButton(
                                        "Ok",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }

                                        })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setTitle("Error");

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( LoginActivity.this, ProfileActivity.class));
            }
        });

    }
}



