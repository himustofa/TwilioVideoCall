package com.quickstart.twiliovideocall.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quickstart.twiliovideocall.models.Doctor;
import com.quickstart.twiliovideocall.repositories.remote.DoctorRepository;

public class DoctorViewModel extends ViewModel {

    private DoctorRepository mDoctorRepository;

    public DoctorViewModel() {
        this.mDoctorRepository = DoctorRepository.getInstance();
    }

    public LiveData<Doctor> getDoctor(String mAuthId) {
        MutableLiveData<Doctor> mDoctor = mDoctorRepository.getDoctor(mAuthId);
        return mDoctor;
    }

    public LiveData<String> storeDoctor(Doctor doctor) {
        return mDoctorRepository.storeDoctor(doctor);
    }

}
