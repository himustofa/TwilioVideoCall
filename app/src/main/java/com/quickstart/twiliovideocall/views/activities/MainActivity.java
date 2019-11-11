package com.quickstart.twiliovideocall.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.quickstart.twiliovideocall.R;
import com.quickstart.twiliovideocall.models.Doctor;
import com.quickstart.twiliovideocall.models.User;
import com.quickstart.twiliovideocall.session.SharedPrefManager;
import com.quickstart.twiliovideocall.utils.ConstantKey;
import com.quickstart.twiliovideocall.utils.Utility;
import com.quickstart.twiliovideocall.viewmodels.DoctorViewModel;
import com.quickstart.twiliovideocall.viewmodels.UserViewModel;
import com.twilio.video.CameraCapturer;
import com.twilio.video.LocalVideoTrack;
import com.twilio.video.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView primaryVideoView;
    private CameraCapturer cameraCapturer;

    private ProgressDialog mProgress = null;
    private UserViewModel mUserViewModel;
    private DoctorViewModel mDoctorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, VideoActivity.class));

        // Render camera to a view
        //primaryVideoView = (VideoView) findViewById(R.id.local_video);
        //shareVideoTrack();
        //audioOutput();

        //===============================================| Initialize ViewModel | Receive the data and observe the data from ViewModel
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mDoctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);


        ((Button) findViewById(R.id.user_id)).setOnClickListener(new ActionHandler());
        ((Button) findViewById(R.id.doctor_id)).setOnClickListener(new ActionHandler());

    }

    //===============================================| Click Events
    private class ActionHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.user_id:
                    mProgress = Utility.showProgressDialog(MainActivity.this, getResources().getString(R.string.progress), false);
                    User user = new User(ConstantKey.USER_UID, "User Name", "+8801914141707", "true", SharedPrefManager.getInstance(MainActivity.this).getDeviceToken());
                    storeUser(user);
                    break;
                case R.id.doctor_id:
                    mProgress = Utility.showProgressDialog(MainActivity.this, getResources().getString(R.string.progress), false);
                    Doctor doctor = new Doctor(ConstantKey.DOCTOR_UID, "Doctor Name", "+8801614141707", "true", SharedPrefManager.getInstance(MainActivity.this).getDeviceToken());
                    storeDoctor(doctor);
                    break;
                default:
                    break;
            }
        }
    }


    //===============================================| Insert into Firebase Database
    private void storeUser(User obj) {
        SharedPrefManager.getInstance(MainActivity.this).saveUser(obj);
        mUserViewModel.storeUser(obj).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                if (result.equals("success")) {
                    startActivity(new Intent(MainActivity.this, VideoActivity.class));
                    finish();
                    Utility.dismissProgressDialog(mProgress);
                } else {
                    Utility.dismissProgressDialog(mProgress);
                }
            }
        });
    }

    private void storeDoctor(Doctor obj) {
        SharedPrefManager.getInstance(MainActivity.this).saveDoctor(obj);
        mDoctorViewModel.storeDoctor(obj).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                if (result.equals("success")) {
                    startActivity(new Intent(MainActivity.this, VideoActivity.class));
                    finish();
                    Utility.dismissProgressDialog(mProgress);
                } else {
                    Utility.dismissProgressDialog(mProgress);
                }
            }
        });
    }











    //===============================================| Delete methods
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
