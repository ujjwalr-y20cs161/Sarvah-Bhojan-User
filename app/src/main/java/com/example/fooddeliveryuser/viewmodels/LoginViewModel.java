package com.example.fooddeliveryuser.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    String loginLabel,loginText,userCredentialText,userPassword;

    private MutableLiveData<String> loginCheck;

    public LoginViewModel() {

        loginCheck = new MutableLiveData<>();
        loginCheck.setValue("Not Yet");

// API call maybe to fetch to change screen Label and guide text
        setLoginText("Login to find out amazing cuisines !");

    }

//    API call to authenticate the credential
    public void checkLogin(){
//        if(getUserCredentialText().equals("u101") && getUserPassword().equals("p123")){
            loginCheck.setValue("Pass");
//        }else{
//            loginCheck.setValue("Fail");
//        }
    }

    public String getLoginLabel() {
        return loginLabel;
    }

    public void setLoginLabel(String loginLabel) {
        this.loginLabel = loginLabel;
    }

    public String getLoginText() {
        return loginText;
    }

    public void setLoginText(String loginText) {
        this.loginText = loginText;
    }

    public String getUserCredentialText() {
        return userCredentialText;
    }

    public void setUserCredentialText(String userCredentialText) {
        this.userCredentialText = userCredentialText;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public MutableLiveData<String> getLoginCheck() {
        return loginCheck;
    }

}
