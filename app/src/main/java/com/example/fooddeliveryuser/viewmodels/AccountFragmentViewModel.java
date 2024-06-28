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

public class AccountFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private SharedPreferences sharedPreferences;

    public AccountFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        sharedPreferences = application.getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);
    }

    public LiveData<User> getUser(){
        return userRepository.getUserByName(sharedPreferences.getString(Tokens.getKeyUsername(),"None"));
    }

    public void deleteUser(String userId,String userName){
        userRepository.deleteUser(userId,userName);
    }

    public void deleteUser(User user){
        userRepository.deleteUser(user);
    }


}
