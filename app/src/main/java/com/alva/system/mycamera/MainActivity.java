package com.alva.system.mycamera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wonderkiln.camerakit.Camera1;
import com.wonderkiln.camerakit.CameraView;

public class MainActivity extends AppCompatActivity {
    CameraView cameraView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = findViewById(R.id.camera);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }
}
