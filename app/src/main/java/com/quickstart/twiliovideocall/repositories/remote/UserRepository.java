package com.quickstart.twiliovideocall.repositories.remote;

import androidx.lifecycle.MutableLiveData;

import com.quickstart.twiliovideocall.models.User;
import com.quickstart.twiliovideocall.repositories.firebase.FirebaseRepository;

public class UserRepository {

    private static UserRepository mInstance;

    public static UserRepository getInstance() {
        if (mInstance == null) {
            mInstance = new UserRepository();
        }
        return mInstance;
    }

    //Pretend to get data from a webservice or online source
    public MutableLiveData<User> getUser(String mAuthId) {
        MutableLiveData<User> data = new MutableLiveData<>();
        new FirebaseRepository().getUserData(new FirebaseRepository.UserCallback() {
            @Override
            public void onCallback(User model) {
                //Log.d(TAG, ""+new Gson().toJson(model));
                data.setValue(model);
            }
        }, mAuthId);
        return data;
    }

    public MutableLiveData<String> storeUser(User user) {
        MutableLiveData<String> data = new MutableLiveData<>();
        new FirebaseRepository().storeUserData(new FirebaseRepository.UserStoreCallback() {
            @Override
            public void onCallback(String result) {
                if (result != null) {
                    data.setValue(result);
                }
            }
        }, user);
        return data;
    }

}
