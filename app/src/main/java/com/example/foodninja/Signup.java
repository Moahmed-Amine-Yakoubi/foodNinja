package com.example.foodninja;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity {
    private Button btncrtAccount, Signin;
    private EditText inputEmail, inputusername, inputPassword, inputPhone;
    FirebaseDatabase database;
    DatabaseReference reference;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        navgtoSignUp();
        Createacount();
    }

    private void init() {
        btncrtAccount = findViewById(R.id.btncrtAccount);
        Signin = findViewById(R.id.Signin);
        inputEmail = findViewById(R.id.inputEmail);
        inputusername = findViewById(R.id.inputusername);
        inputPassword = findViewById(R.id.inputPassword);
        inputPhone = findViewById(R.id.inputPhone);

        sp = getSharedPreferences("users", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private void navgtoSignUp() {
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, Athentification.class);
                startActivity(intent);
            }
        });
    }


    private void Createacount() {
        btncrtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String username = inputusername.getText().toString();
                String phone = inputPhone.getText().toString();



                if (username.isEmpty() || username.length() < 4) {
                    inputusername.setError("enter proper username");
                } else if (password.isEmpty() || password.length() < 6) {
                    inputPassword.setError("enter proper password");
                } else if (phone.isEmpty() || phone.length() != 8) {
                    inputPhone.setError("enter proper number phone");
                } else {
                    reference.child(username).child("email").setValue(email);
                    reference.child(username).child("username").setValue(username);
                    reference.child(username).child("password").setValue(password);
                    reference.child(username).child("phone").setValue(phone);

                    editor.putString("save_username", username);
                    editor.putString("save_password", password);
                    editor.putString("save_email", email);
                    editor.putString("save_phone", phone);
                    editor.commit();
                    Toast.makeText(Signup.this, "user registered successfuly ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Signup.this, Home.class);
                    startActivity(intent);
                }
            }
        });
    }


}