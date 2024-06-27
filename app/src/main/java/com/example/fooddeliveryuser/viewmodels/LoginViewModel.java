package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliveryuser.models.User;
import com.example.fooddeliveryuser.repositories.UserRepository;
import com.example.fooddeliveryuser.services.Tokens;

import java.util.List;


public class LoginViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Application app;
    public LoginViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);
        app = application;
        sharedPreferences = application.getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);
    }

    public LiveData<User> getUserByName(String userCredential) {
        return userRepository.getUserByName(userCredential);
    }

    public LiveData<User> getUserByEmail(String userCredential) {
        return userRepository.getUserByEmail(userCredential);
    }

    public LiveData<User> getUserByPhoneNumber(String userCredential) {
        return userRepository.getUserByPhoneNumber(userCredential);
    }

    public void setSharedPreferences(String userName, String passwordHash){
        editor = sharedPreferences.edit();
        editor.putString(Tokens.getKeyUsername(),userName);
        editor.putString(Tokens.getKeyPassword(),passwordHash);
        editor.putBoolean(Tokens.getLogged(),true);
        editor.apply();
    }

}

