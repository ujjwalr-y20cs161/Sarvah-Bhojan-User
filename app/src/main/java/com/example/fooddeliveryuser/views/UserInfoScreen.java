package com.example.fooddeliveryuser.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.fooddeliveryuser.R;
import com.example.fooddeliveryuser.databinding.ActivityUserInfoScreenBinding;
import com.example.fooddeliveryuser.models.User;
import com.example.fooddeliveryuser.viewmodels.UserInfoViewModel;

public class UserInfoScreen extends AppCompatActivity {

    private UserInfoViewModel viewModel;
    private ActivityUserInfoScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(UserInfoViewModel.class);


        viewModel.getUser().observe(this,user -> {
            if(user!= null){
                User user1 = user;
                binding.userName.setText(user.getUserName());
                binding.phoneNumber.setText(user.getPhoneNumber());
                binding.emailAddress.setText(user.getEmailAddress());

                binding.userInfoSave.setOnClickListener(v->{
                    if(binding.phoneNumber.getText().toString().isEmpty() || binding.userName.getText().toString().isEmpty()
                            ||  binding.phoneNumber.getText().toString().length() < 10 || binding.userName.getText().toString().length() < 4
                            || binding.emailAddress.getText().toString().isEmpty() || !(binding.emailAddress.getText().toString().contains(".") && binding.emailAddress.getText().toString().contains("@"))){
                        notifyHelpText("Fill valid details");
                    }else{
                        user1.setUserName(binding.userName.getText().toString());
                        user1.setPhoneNumber(binding.phoneNumber.getText().toString());
                        user1.setEmailAddress(binding.emailAddress.getText().toString());

                        viewModel.updateUser(user1);
                        finish();

                    }
                });
            }
        });

        binding.BackButton.setOnClickListener( v-> getOnBackPressedDispatcher().onBackPressed());


    }

    private void notifyHelpText(String msg){
        binding.helpText.setText(msg);
        binding.helpText.setVisibility(View.VISIBLE);
    }

}