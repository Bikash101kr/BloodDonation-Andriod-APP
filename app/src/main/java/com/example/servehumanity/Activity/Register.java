package com.example.servehumanity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText edtFirstName, edtLastName, edtUsername, edtPassword, edtPhone, edtAddress;
    Button btnRegister;

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            Toast.makeText(Register.this, "code: " + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(Register.this, "Unsuccessful" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}