package com.tripointaxis.imgursample2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private ImageView ivInternetImage;
    private Button btnPickFile;
    private Button btnUploadPhoto;
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapViews();
        setupEventListeners();
        populateInitialData();
        registerLaunchers();
    }

    private void setupEventListeners() {
        btnUploadPhoto.setOnClickListener(v -> {
            pickMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE).build());
        });

        btnPickFile.setOnClickListener(v -> {

        });
    }

    private void registerLaunchers() {
        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    Glide.with(this)
                            .load(uri)
                            .into(ivInternetImage);

                });
    }


    private void mapViews() {
        ivInternetImage = findViewById(R.id.ivInternetImage);
        btnPickFile = findViewById(R.id.btnPickFile);
        btnUploadPhoto = findViewById(R.id.btnUploadFile);
    }

    private void populateInitialData() {
        Glide.with(this)
                .load("https://preview.redd.it/zs1d2ucvnuba1.jpg?width=216&crop=smart&auto=webp&v=control_1&s=d0d7ff51fc8cc167a87e78990702e38fc5e07be4")
                .into(ivInternetImage);
    }
}