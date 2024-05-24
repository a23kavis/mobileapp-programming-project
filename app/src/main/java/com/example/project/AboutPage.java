package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class AboutPage  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page);

        WebView myWebView = findViewById(R.id.my_webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript
        myWebView.addJavascriptInterface(new WebAppInterface(), "Android");
        myWebView.setWebViewClient(new WebViewClient()); // Do not open in Chrome!
        myWebView.loadUrl("file:///android_asset/index.html"); // Load local HTML file

    }
    public class WebAppInterface {
        @JavascriptInterface
        public void goToMainActivity() {
            // Navigate back to MainActivity
            Intent intent = new Intent(AboutPage.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
