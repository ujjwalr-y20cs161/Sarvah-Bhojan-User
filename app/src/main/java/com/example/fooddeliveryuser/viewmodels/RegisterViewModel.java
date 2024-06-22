package com.example.fooddeliveryuser.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegisterViewModel extends ViewModel {

    private String registerCheck;
    MutableLiveData<Boolean> passedRegister;

    public RegisterViewModel() {
        this.passedRegister = new MutableLiveData<>(false);
        registerCheck = "";
    }

    private void setRegisterCheck(String registerCheck) {
        this.registerCheck = registerCheck;
    }

    public MutableLiveData<Boolean> getPassedRegister() {
        return passedRegister;
    }

    private void setPassedRegister(Boolean passedRegister) {
        this.passedRegister.setValue(passedRegister);
    }

    public String getRegisterCheck() {
        return registerCheck;
    }

    public void RegisterUser(String UserName,String EmailAddress,String PhoneNumber,String Password, Boolean checked){
//        Logic to check whether UserName or Email Address or Phone Number is repeated:
//        If yes : sent a warning string
        if(UserName.equals("User101") || EmailAddress.equals("user101@gmail.com")|| PhoneNumber.equals("9876543210")){
            setRegisterCheck("User Already Exists");
            setPassedRegister(false);
            Log.e("Register Log","User Already Exists");
        }
//              if UserName doesn't meet requirement
        else if (UserName.length() < 4) {
            setRegisterCheck("UserName Should be More than 4 Characters");
            setPassedRegister(false);
            Log.e("Register Log","UserName Should be More than 4 Characters");
        }

        //              if Email Address is not valid
        else if(!EmailAddress.contains("@") || !EmailAddress.contains(".")){
//              replace with a proper email checker
            setRegisterCheck("Invalid Email Address");
            setPassedRegister(false);
            Log.e("Register Log","Invalid Email Address");
        }

//              if Phone Number is not valid
        else if(PhoneNumber.length() < 10){
            setRegisterCheck("Invalid Phone Number");
            setPassedRegister(false);
            Log.e("Register Log","Invalid Phone Number");
        }

//              if password does not meet requirements send warning
        else if(Password.length() < 8){
            if(Password.equals("")){
                setRegisterCheck("Please enter the Password");
                setPassedRegister(false);
                Log.e("Register Log", "Please enter the Password");}
            else {
                setRegisterCheck("Password Should be More than 8 Characters");
                setPassedRegister(false);
                Log.e("Register Log", "Password Should be More than 8 Characters");
            }
        }
        else{
            setPassedRegister(true);
            Log.i("Register Log", "Registration Complete");
        }
    }
}
