package com.example.fooddeliveryuser.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fooddeliveryuser.models.User;
import com.example.fooddeliveryuser.repositories.UserRepository;
import com.example.fooddeliveryuser.services.Tokens;

public class RegisterViewModel extends AndroidViewModel {

    private String registerCheck;
    private  MutableLiveData<Boolean> passedRegister;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private UserRepository userRepository;

    public RegisterViewModel(Application application) {
        super(application);
        userRepository = new UserRepository(application);

        sharedPreferences = application.getSharedPreferences(Tokens.getSharedPrefName(), Context.MODE_PRIVATE);

        this.passedRegister = new MutableLiveData<>(false);
        registerCheck = "";
    }

    private void setRegisterCheck(String registerCheck) {
        this.registerCheck = registerCheck;
    }
    public String getRegisterCheck() {
        return registerCheck;
    }

    public MutableLiveData<Boolean> getPassedRegister() {
        return passedRegister;
    }

    private void setPassedRegister(Boolean passedRegister) {
        this.passedRegister.setValue(passedRegister);
    }


    public void RegisterUser(String UserName,String EmailAddress,String PhoneNumber,String Password, Boolean checked){
//        Logic to check whether UserName or Email Address or Phone Number is repeated:
//        If yes : sent a warning string
        if(checked) {
            if (UserName.equals("User101") || EmailAddress.equals("user101@gmail.com") || PhoneNumber.equals("9876543210")) {
                setRegisterCheck("User Already Exists");
                setPassedRegister(false);
            }
//              if UserName doesn't meet requirement
            else if (UserName.length() < 4) {
                setRegisterCheck("UserName Should be More than 4 Characters");
                setPassedRegister(false);
            }
            //              if Email Address is not valid
            else if (!EmailAddress.contains("@") || !EmailAddress.contains(".")) {
//              replace with a proper email checker
                setRegisterCheck("Invalid Email Address");
                setPassedRegister(false);
            }
//              if Phone Number is not valid
            else if (PhoneNumber.length() < 10) {
                setRegisterCheck("Invalid Phone Number");
                setPassedRegister(false);
            }
//              if password does not meet requirements send warning
            else if (Password.length() < 8) {
                if (Password.equals("")) {
                    setRegisterCheck("Please enter the Password");
                    setPassedRegister(false);
                } else {
                    setRegisterCheck("Password Should be More than 8 Characters");
                    setPassedRegister(false);
                }
            } else {
                setPassedRegister(true);
                Log.i(getClass().getSimpleName(), "User Registration Complete");
                User user = new User(UserName, EmailAddress, PhoneNumber, Password);
                userRepository.insertUser(user);

                editor = sharedPreferences.edit();
                editor.putString(Tokens.getKeyUsername(), user.getUserName());
                editor.putString(Tokens.getKeyPassword(),user.getPasswordHash());
                editor.putBoolean(Tokens.getLogged(),true);
                editor.apply();
            }
        }else{
            setRegisterCheck("Terms and Conditions should be agreed");
            setPassedRegister(false);
        }
    }
}
