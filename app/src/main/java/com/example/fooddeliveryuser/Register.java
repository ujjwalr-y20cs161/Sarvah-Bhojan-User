package com.example.fooddeliveryuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fooddeliveryuser.databinding.ActivityRegisterBinding;
import com.example.fooddeliveryuser.viewmodels.LoginViewModel;
import com.example.fooddeliveryuser.viewmodels.RegisterViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;

    RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        registerViewModel.getPassedRegister().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    startActivity(new Intent(getApplicationContext(),AddressPicker.class));
                    finish();
                }else if(!registerViewModel.getRegisterCheck().equals("")){
                    notifyHelpText(registerViewModel.getRegisterCheck());
                }
            }
        });



        // Set onClickListener for register button
        binding.buttonCreate.setOnClickListener(v->{
                registerViewModel.RegisterUser(binding.editTextUsername.getText().toString(),binding.editTextEmail.getText().toString(),binding.editTextPhone.getText().toString(),binding.editTextPassword.getText().toString(),binding.termAndCondCheck.isChecked());
        });

    }

    private void notifyHelpText(String Message){
        binding.helpText.setText(Message);
        binding.helpText.setVisibility(View.VISIBLE);
    }
}
