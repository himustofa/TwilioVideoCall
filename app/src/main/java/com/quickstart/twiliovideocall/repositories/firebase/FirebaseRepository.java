package com.quickstart.twiliovideocall.repositories.firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.quickstart.twiliovideocall.models.Doctor;
import com.quickstart.twiliovideocall.models.User;
import com.quickstart.twiliovideocall.utils.ConstantKey;

public class FirebaseRepository {

    private static final String TAG = "FirebaseRepository";
    private DatabaseReference mDatabaseRef;

    //===============================================| User
    //https://stackoverflow.com/questions/30564735/android-firebase-simply-get-one-child-objects-data
    public void getUserData(final UserCallback mCallback, String mUserAuthId) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ConstantKey.USER_NODE).child(mUserAuthId);
        mDatabaseRef.keepSynced(true); //firebase load offline data
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    //String value = (String) snapshot.getValue(); //Get all child data
                    //OR
                    //String singleValue = (String) snapshot.child("userFullName").getValue(); //Get single child data

                    User model = snapshot.getValue(User.class);
                    String key = snapshot.getKey();
                    mCallback.onCallback(model);
                } else {
                    mCallback.onCallback(null);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "" + databaseError.getMessage());
                mCallback.onCallback(null);
            }
        });
    }
    public interface UserCallback {
        void onCallback(User model);
    }

    public void storeUserData(final UserStoreCallback mCallback, User mUser) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ConstantKey.USER_NODE);
        //String mDbId = mDatabaseRef.push().getKey() : Get database UUID after inserting data
        mDatabaseRef.child(mUser.getUid()).setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mCallback.onCallback("success");
                    Log.d(TAG, "Data inserted successfully");
                } else {
                    mCallback.onCallback("failure");
                    Log.e(TAG, "" + task.getException().getMessage());
                }
            }
        });
    }
    public interface UserStoreCallback {
        void onCallback(String result);
    }

    //===============================================| Doctor
    public void getDoctorData(final DoctorCallback mCallback, String mDoctorAuthId) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ConstantKey.DOCTOR_NODE).child(mDoctorAuthId);
        mDatabaseRef.keepSynced(true); //firebase load offline data
        mDatabaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount() > 0) {
                    Doctor model = snapshot.getValue(Doctor.class);
                    String key = snapshot.getKey();
                    mCallback.onCallback(model);
                } else {
                    mCallback.onCallback(null);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "" + databaseError.getMessage());
                mCallback.onCallback(null);
            }
        });
    }
    public interface DoctorCallback {
        void onCallback(Doctor model);
    }

    public void storeDoctorData(final DoctorStoreCallback mCallback, Doctor mDoctor) {
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(ConstantKey.DOCTOR_NODE);
        //String mDbId = mDatabaseRef.push().getKey() : Get database UUID after inserting data
        mDatabaseRef.child(mDoctor.getUid()).setValue(mDoctor).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    mCallback.onCallback("success");
                    Log.d(TAG, "Data inserted successfully");
                } else {
                    mCallback.onCallback("failure");
                    Log.e(TAG, "" + task.getException().getMessage());
                }
            }
        });
    }
    public interface DoctorStoreCallback {
        void onCallback(String result);
    }

}
