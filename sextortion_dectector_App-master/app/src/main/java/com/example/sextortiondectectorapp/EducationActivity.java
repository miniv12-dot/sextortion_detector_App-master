package com.example.sextortiondectectorapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.webkit.WebView;

public class EducationActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        webView = findViewById(R.id.webView);
        webView.loadUrl("https://nmdoj.gov/wp-content/uploads/NMDOJ-Guide-Sextortion-2024.pdf");
        // You can also load the content from a remote URL or use other methods to display educational content
    }
}