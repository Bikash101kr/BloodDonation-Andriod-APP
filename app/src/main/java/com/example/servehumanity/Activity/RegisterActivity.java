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
import android.widget.Toast;

import com.example.servehumanity.R;
import com.example.servehumanity.URL.URL;
import com.example.servehumanity.api.UserAPI;
import com.example.servehumanity.model.User;
import com.example.servehumanity.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtFirstName, edtLastName, edtUsername, edtPassword, edtConfirmPassword, edtPhone, edtAddress;
    Button btnRegister;
    String FirstName, lastName, username, password, confirmPassword, phone, address, profileId;
    Boolean validationError;

    private boolean validateUser(String FirstName, String lastName, String username, String password, String confirmPassword, String phone, String address) {
       if (FirstName.length() > 30) {
           edtFirstName.setError("First Name cannot be longer than 30 characters");
           edtFirstName.requestFocus();
           return true;

       }
        if (FirstName.length() <= 0) {
            edtFirstName.setError("Please enter First Name");
            edtFirstName.requestFocus();
            return true;
        }
        edtFirstName.clearFocus();
        edtLastName.setError(null);

        if (lastName.length() > 30) {
            edtLastName.setError("Last Name cannot be longer than 30 characters");
            edtLastName.requestFocus();
            return true;
        }
        if (lastName.length() <= 0) {
            edtLastName.setError("Please enter Last Name");
            edtLastName.requestFocus();
            return true;
        }
        edtLastName.clearFocus();
        edtLastName.setError(null);
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
        if (phone.length() <= 0) {
            edtPhone.setError("please Enter your phone number");
            edtPhone.requestFocus();
            return true;
        }
        edtPhone.clearFocus();
        edtPhone.setError(null);
        if (address.length() <= 0) {
            edtAddress.setError("Please enter your address");
            edtAddress.requestFocus();
            return true;
        }
        edtAddress.clearFocus();
        edtAddress.setError(null);
        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName = edtFirstName.getText().toString();
                lastName = edtLastName.getText().toString();
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();
                phone = edtPhone.getText().toString();
                address = edtAddress.getText().toString();
                validationError = validateUser(FirstName, lastName, username, password, confirmPassword, phone, address);

                if (validationError) return;
                        UserAPI userAPI = URL.getInstance().create(UserAPI.class);

                User user = new User(edtFirstName.getText().toString(), edtLastName.getText().toString(),
                        edtAddress.getText().toString(),edtPhone.getText().toString(),
                        edtUsername.getText().toString(),edtPassword.getText().toString()
                        );

                Call<UserResponse> call = userAPI.registerUser(user);

                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (!response.isSuccessful()) {
                            Log.i("response", "unsuccessful");
                            Toast.makeText(RegisterActivity.this, "code: " + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        Log.i("response", "success");
                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));

                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
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
                        Toast.makeText(RegisterActivity.this, "Unsuccessful" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}