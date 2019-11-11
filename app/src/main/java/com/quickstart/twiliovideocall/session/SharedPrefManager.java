package com.quickstart.twiliovideocall.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quickstart.twiliovideocall.models.Doctor;
import com.quickstart.twiliovideocall.models.User;

import java.util.ArrayList;
import java.util.Objects;

public class SharedPrefManager {

    private static final String USER_PREF = "shared_pref_user";
    private static final String DOCTOR_PREF = "shared_pref_doctor";

    private static final String SHARED_PREF_TOKEN = "shared_pref_app";
    private static final String TAG_TOKEN = "token_key";

    //Singleton Design Pattern
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //===============================================| Token
    public void saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_TOKEN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        editor.commit();
    }

    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_TOKEN, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }

    //===============================================| User
    public void saveUser(User model){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserModel", new Gson().toJson(model));
        editor.apply();
        editor.commit(); //for old version
    }

    public User getUser(){
        SharedPreferences pref = mCtx.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return new Gson().fromJson(pref.getString("UserModel", null), User.class);
    }

    //===============================================| Doctor
    public void saveDoctor(Doctor model){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("DoctorModel", new Gson().toJson(model));
        editor.apply();
        editor.commit(); //for old version
    }

    public User getDoctor(){
        SharedPreferences pref = mCtx.getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        return new Gson().fromJson(pref.getString("DoctorModel", null), User.class);
    }

}
