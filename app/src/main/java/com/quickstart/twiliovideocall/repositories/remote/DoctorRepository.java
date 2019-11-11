package com.quickstart.twiliovideocall.repositories.remote;

import androidx.lifecycle.MutableLiveData;

import com.quickstart.twiliovideocall.models.Doctor;
import com.quickstart.twiliovideocall.repositories.firebase.FirebaseRepository;

public class DoctorRepository {

    private static DoctorRepository mInstance;

    public static DoctorRepository getInstance() {
        if (mInstance == null) {
            mInstance = new DoctorRepository();
        }
        return mInstance;
    }

    //Pretend to get data from a webservice or online source
    public MutableLiveData<Doctor> getDoctor(String mAuthId) {
        MutableLiveData<Doctor> data = new MutableLiveData<>();
        new FirebaseRepository().getDoctorData(new FirebaseRepository.DoctorCallback() {
            @Override
            public void onCallback(Doctor model) {
                //Log.d(TAG, ""+new Gson().toJson(model));
                data.setValue(model);
            }
        }, mAuthId);
        return data;
    }

    public MutableLiveData<String> storeDoctor(Doctor doctor) {
        MutableLiveData<String> data = new MutableLiveData<>();
        new FirebaseRepository().storeDoctorData(new FirebaseRepository.DoctorStoreCallback() {
            @Override
            public void onCallback(String result) {
                if (result != null) {
                    data.setValue(result);
                }
            }
        }, doctor);
        return data;
    }

}
