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
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtAge;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtAge = findViewById(R.id.edtAge);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String ageStr = edtAge.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || ageStr.isEmpty()) {
                    Toast.makeText(UserRegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    int age = Integer.parseInt(ageStr);
                    // Save user information (You can use SharedPreferences or a database)
                    // For simplicity, let's use SharedPreferences
                    getSharedPreferences("UserPrefs", MODE_PRIVATE)
                            .edit()
                            .putString("Username", username)
                            .putString("Password", password)
                            .putInt("Age", age)
                            .apply();

                    Toast.makeText(UserRegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UserRegistrationActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }
}