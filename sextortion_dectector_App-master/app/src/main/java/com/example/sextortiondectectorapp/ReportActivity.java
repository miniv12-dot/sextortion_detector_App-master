package com.example.sextortiondectectorapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ReportActivity extends AppCompatActivity {
    private EditText edtReportDetails;
    private Button btnSubmitReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        edtReportDetails = findViewById(R.id.edtReportDetails);
        btnSubmitReport = findViewById(R.id.btnSubmitReport);

        btnSubmitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reportDetails = edtReportDetails.getText().toString().trim();

                if (reportDetails.isEmpty()) {
                    Toast.makeText(ReportActivity.this, "Please enter the report details", Toast.LENGTH_SHORT).show();
                } else {
                    // Here, implement the logic to securely send the report to law enforcement
                    Toast.makeText(ReportActivity.this, "Report submitted successfully", Toast.LENGTH_SHORT).show();
                    edtReportDetails.setText("");
                }
            }
        });
    }
}