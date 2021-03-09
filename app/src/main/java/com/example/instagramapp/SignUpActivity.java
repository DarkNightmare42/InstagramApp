package com.example.instagramapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.signUpUsername);
        password = findViewById(R.id.signUpPassword);
        signup = findViewById(R.id.signUpNtm);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Whoops, looks like you forgot something", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveUser(user, pass);
            }
        });
    }

    public void saveUser(String username, String pass){
        // Create the ParseUser
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(pass);
        //user.setEmail("email@example.com");
// Set custom properties
        //user.put("phone", "650-253-0000");
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUpActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    startLogin();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(SignUpActivity.this, "whoops" + e, Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void startLogin(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}