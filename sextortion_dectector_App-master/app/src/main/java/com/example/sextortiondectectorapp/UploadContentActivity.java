package com.example.sextortiondectectorapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class UploadContentActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_PICK = 2;
    private ImageView imageView;
    private Button btnUploadCamera;
    private Button btnUploadGallery;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_content);

        imageView = findViewById(R.id.imageView);
        btnUploadCamera = findViewById(R.id.btnUploadCamera);
        btnUploadGallery = findViewById(R.id.btnUploadGallery);

        btnUploadCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        btnUploadGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPickPictureIntent();
            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchPickPictureIntent() {
        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (pickPictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pickPictureIntent, REQUEST_IMAGE_PICK);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
                checkExplicitContent();
            } else if (requestCode == REQUEST_IMAGE_PICK) {
                Uri imageUri = data.getData();
                try {
                    imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageView.setImageBitmap(imageBitmap);
                    checkExplicitContent();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void checkExplicitContent() {
        // Implement content analysis using machine learning model
        boolean isExplicit = detectExplicitContent(imageBitmap);
        int age = getSharedPreferences("UserPrefs", MODE_PRIVATE).getInt("Age", 0);

        if (age >= 18) {
            if (isExplicit) {
                // Prompt user with a warning
                new AlertDialog.Builder(this)
                        .setTitle("Warning")
                        .setMessage("Are you sure you want to upload this 'nude' picture?")
                        .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                            // Proceed with the upload
                            uploadContent();
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            } else {
                uploadContent();
            }
        } else {
            if (isExplicit) {
                // Block the upload for minors
                Toast.makeText(this, "Upload blocked. Minors are not allowed to upload nude pictures.", Toast.LENGTH_SHORT).show();
                // Increment report count and block account if necessary
                handleMinorViolation();
            } else {
                uploadContent();
            }
        }
    }

    private boolean detectExplicitContent(Bitmap image) {
        // Placeholder for actual content detection logic
        return true; // Assume content is explicit for demonstration purposes
    }

    private void uploadContent() {
        // Implement content upload logic
        Toast.makeText(this, "Content uploaded successfully", Toast.LENGTH_SHORT).show();
    }

    private void handleMinorViolation() {
        int violationCount = getSharedPreferences("UserPrefs", MODE_PRIVATE).getInt("ViolationCount", 0);
        violationCount++;
        if (violationCount >= 2) {
            // Block account
            Toast.makeText(this, "Account blocked due to repeated violations.", Toast.LENGTH_SHORT).show();
            // Implement account blocking logic
        } else {
            getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    .edit()
                    .putInt("ViolationCount", violationCount)
                    .apply();
        }
    }
}