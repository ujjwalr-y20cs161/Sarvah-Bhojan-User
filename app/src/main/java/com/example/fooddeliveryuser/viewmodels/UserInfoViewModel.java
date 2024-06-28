package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fooddeliveryuser.models.User;
import com.example.fooddeliveryuser.repositories.UserRepository;
import com.example.fooddeliveryuser.services.Tokens;

public class UserInfoViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public UserInfoViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        sharedPreferences = application.getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);
    }

    public LiveData<User> getUser(){
        return userRepository.getUserByName(sharedPreferences.getString(Tokens.getKeyUsername(),"None"));
    }

    public void updateUser(User user){
        userRepository.updateUser(user);
        editor = sharedPreferences.edit();
        editor.putString(Tokens.getKeyUsername(),user.getUserName());
        editor.apply();

    }

}
