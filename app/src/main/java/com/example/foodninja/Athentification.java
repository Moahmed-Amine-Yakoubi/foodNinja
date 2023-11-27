package com.example.foodninja;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Athentification extends AppCompatActivity {
    private Button btnLogin, SignUp;

    private EditText inputUsername, inputPassword;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");


    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_athentification);
        init();
        SignUp();
        if (sp.contains("save_username")) {
            Intent intent = new Intent(Athentification.this, Home.class);
            startActivity(intent);
        } else {
            login();
        }


    }

    private void init() {
        btnLogin = findViewById(R.id.btnLogin);
        SignUp = findViewById(R.id.SignUp);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        sp = getSharedPreferences("users", Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    private void SignUp() {
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Athentification.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin();
            }
        });
    }


    private void UserLogin() {
        String Username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        if (Username.isEmpty()) {
            inputUsername.setError("enter connect email");
        } else if (password.isEmpty()) {
            inputPassword.setError("enter proper password");
        } else {
            Query checkUserDatabase = databaseReference.orderByChild("username").equalTo(Username);
            checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        String passwordFromDB = snapshot.child(Username).child("password").getValue(String.class);
                        if (passwordFromDB.equals(password)) {
                            String emailFromDB = snapshot.child(Username).child("email").getValue(String.class);
                            String usernameFromDB = snapshot.child(Username).child("username").getValue(String.class);
                            String phoneFromDB = snapshot.child(Username).child("phone").getValue(String.class);
                            if (Username.equals("admin") && password.equals("admin")) {
                                Intent intent = new Intent(Athentification.this, Dashboard_admin.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(Athentification.this, Home.class);

                                intent.putExtra("email", emailFromDB);
                                intent.putExtra("username", usernameFromDB);
                                intent.putExtra("password", passwordFromDB);
                                intent.putExtra("phone", phoneFromDB);
                                edit.putString("save_username", Username);
                                edit.putString("save_password", password);
                                edit.putString("save_email", emailFromDB);
                                edit.putString("save_phone", phoneFromDB);
                                edit.commit();
                                startActivity(intent);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }

    }
}