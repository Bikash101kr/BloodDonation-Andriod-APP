package com.example.servehumanity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servehumanity.R;
import com.example.servehumanity.URL.URL;
import com.example.servehumanity.api.UserAPI;
import com.example.servehumanity.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword= findViewById(R.id.edtPassword);
        tvRegister= findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    private  void  login() {

        UserAPI userAPI = URL.getInstance().create(UserAPI.class);

        Call<UserResponse> call = userAPI.checkUser(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Username or Password is incorrect!", Toast.LENGTH_LONG).show();
                    return;
                }
               URL.token = response.body().getToken();
                startActivity(new Intent (Login.this, DashboardActivity.class));
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_LONG).show();
            }
        });

    }
}