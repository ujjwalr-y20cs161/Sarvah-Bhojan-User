package com.example.fooddeliveryuser;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout usernameInputLayout;
    private TextInputLayout passwordInputLayout;
    private TextInputEditText editTextUsername;
    private TextInputEditText editTextPassword;
    private MaterialButton buttonLogin;
    private Button buttonForgotPassword;
    private MaterialButton buttonRegister;
    private ImageView userImageView;
    private TextView textViewLoginMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        usernameInputLayout = findViewById(R.id.usernameInputLayout);
        passwordInputLayout = findViewById(R.id.passwordInputLayout);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonForgotPassword = findViewById(R.id.buttonForgotPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        userImageView = findViewById(R.id.loginImageView);
        textViewLoginMessage = findViewById(R.id.loginText);

        // Set onClickListener for buttons
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        buttonForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPassword();
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    // Method to handle login action
    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Add your login logic here


    }

    // Method to handle forgot password action
    private void forgotPassword() {
        // Add your forgot password logic here
    }

    // Method to handle register action
    private void register() {
        // Add your register logic here
        startActivity(new Intent(this, Register.class));
    }
}
