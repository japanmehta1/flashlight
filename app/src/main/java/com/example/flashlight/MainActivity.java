package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;

    boolean flash = false; 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick();


    }

    private void addListenerOnButtonClick() {
        toggleButton = findViewById(R.id.toggleButton);

        toggleButton.setOnClickListener(view -> {
            CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null; // Usually front camera is at 0 position.
            try {
                cameraId = camManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }
            try {
                camManager.setTorchMode(cameraId, flash);
            } catch (CameraAccessException e) {
                throw new RuntimeException(e);
            }

            flash = !flash;
        });
    }
}