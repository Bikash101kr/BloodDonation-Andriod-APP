package com.example.servehumanity.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.servehumanity.R;

public class DonationCampsActivity extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_camps);
        WebView mywebview = (WebView) findViewById(R.id.webView);

        mywebview.loadUrl("https://nrcs.org/donate-blood/");

    }

}