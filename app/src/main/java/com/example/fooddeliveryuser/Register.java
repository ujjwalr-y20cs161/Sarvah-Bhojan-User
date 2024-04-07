package com.example.fooddeliveryuser;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {

    private ImageView registerImage;
    private TextView registerTitle;
    private TextInputLayout usernameInputLayout, emailInputLayout, phoneInputLayout, passwordInputLayout;
    private TextInputEditText editTextUsername, editTextEmail, editTextPhone, editTextPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        registerImage = findViewById(R.id.RegisterImage);
        registerTitle = findViewById(R.id.RegisterTitle);
        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        emailInputLayout = findViewById(R.id.emailInputLayout);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonLogin);

        // Set onClickListener for register button
        buttonRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Add your registration logic here
        // For example, you can perform validation and then send registration request to server

        // For demonstration, simply print the entered details
        String message = "Username: " + username + "\nEmail: " + email + "\nPhone: " + phone + "\nPassword: " + password;
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
    }
}
