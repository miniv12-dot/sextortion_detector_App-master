package com.example.sextortiondectectorapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btnReport;
    private Button btnDetect;
    private Button btnEducation;
    private Button btnUploadContent;
    private View btnblur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReport = findViewById(R.id.btnReport);
        btnDetect = findViewById(R.id.btnDetect);
        btnEducation = findViewById(R.id.btnEducation);
        btnUploadContent = findViewById(R.id.btnUploadContent);
        btnblur = findViewById(R.id.blurbtn);


        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        btnDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectActivity.class);
                startActivity(intent);
            }
        });

        btnEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EducationActivity.class);
                startActivity(intent);
            }
        });

        btnUploadContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadContentActivity.class);
                startActivity(intent);
            }
        });
        btnblur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, blurclass.class);
                startActivity(intent);
            }
        });



    }
}