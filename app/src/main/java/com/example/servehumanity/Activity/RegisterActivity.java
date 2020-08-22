package com.example.servehumanity.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.servehumanity.R;
import com.example.servehumanity.Url.URL;
import com.example.servehumanity.api.UserAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText  edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    Button btnRegister;
    String  username, email, password, confirmPassword, profileId;
    Boolean validationError;

    private boolean validateUser(String username, String email, String password, String confirmPassword) {
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
        if (email.length() <= 0) {
            edtEmail.setError("Please enter email");
            edtEmail.requestFocus();
            return true;
        }

        edtEmail.clearFocus();
        edtEmail.setError(null);
        if (password.length() <= 0) {
            edtPassword.setError("Please enter password");
            edtPassword.requestFocus();
            return true;
        }

        edtPassword.clearFocus();
        edtPassword.setError(null);

        if (confirmPassword.length() <= 0) {
            edtConfirmPassword.setError("Please confirm your password");
            edtConfirmPassword.requestFocus();
            return true;
        }
        edtConfirmPassword.clearFocus();
        edtConfirmPassword.setError(null);

        if (!password.equals(confirmPassword)){
            edtPassword.setError("Password does not match.");
            edtPassword.requestFocus();
            return true;
        }
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileId = getIntent().getStringExtra("profileId");
                username = edtUsername.getText().toString();
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();
                validationError = validateUser(username, email, password, confirmPassword);

                if (validationError) return;

                UserAPI userAPI = URL.getInstance().create(UserAPI.class);

                Call<Void> call = userAPI.register(email, username, password, profileId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Log.i("response", "unsuccessful");
                            return;
                        }
                        Log.i("response", "success");
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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
                    }
                });
            }
        });
    }
}