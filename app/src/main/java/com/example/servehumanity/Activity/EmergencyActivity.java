package com.example.servehumanity.Activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servehumanity.R;

public class EmergencyActivity extends AppCompatActivity {
  WebView webView;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_emergency);

    WebView mywebview = (WebView) findViewById(R.id.webView);

    mywebview.loadUrl("https://www.nepalpolice.gov.np/index.php/safety-security-tips/emergency-numbers");
  }
}