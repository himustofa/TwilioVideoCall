package com.quickstart.twiliovideocall.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import com.quickstart.twiliovideocall.R;
import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView primaryVideoView;
    private CameraCapturer cameraCapturer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, VideoActivity.class));

        // Render camera to a view
        primaryVideoView = (VideoView) findViewById(R.id.local_video);
        //shareVideoTrack();
        //audioOutput();


    }

    private void shareVideoTrack() {
        // Share your camera
        cameraCapturer = new CameraCapturer(this, CameraCapturer.CameraSource.FRONT_CAMERA);
        LocalVideoTrack localVideoTrack = LocalVideoTrack.create(this, true, cameraCapturer);
        // Mirror front camera
        primaryVideoView.setMirror(true);
        // Render camera to view
        localVideoTrack.addRenderer(primaryVideoView);
    }

    private void switchCamera() {
        // Switch the camera source
        CameraCapturer.CameraSource cameraSource = cameraCapturer.getCameraSource();
        cameraCapturer.switchCamera();
        primaryVideoView.setMirror(cameraSource == CameraCapturer.CameraSource.BACK_CAMERA);
    }

    private void audioOutput() {
        // Get AudioManager
        AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
        // Route audio through speaker
        audioManager.setSpeakerphoneOn(true);
        // Route audio through headset
        audioManager.setSpeakerphoneOn(false);
    }
}
