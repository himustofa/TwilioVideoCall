package com.quickstart.twiliovideocall.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quickstart.twiliovideocall.models.User;
import com.quickstart.twiliovideocall.repositories.remote.UserRepository;

public class UserViewModel extends ViewModel {

    private UserRepository mUserRepository;

    public UserViewModel() {
        this.mUserRepository = UserRepository.getInstance();
    }

    public LiveData<User> getUser(String mAuthId) {
        MutableLiveData<User> mUser = mUserRepository.getUser(mAuthId);
        return mUser;
    }

    public LiveData<String> storeUser(User user) {
        return mUserRepository.storeUser(user);
    }

}
