package com.example.foodninja;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_edit extends Fragment {
    private EditText EditUsername, EditEmail, EditPhone, EditPassword;
    private String Username, Email, Password, Phone;
    private Button editprofile;
    private DatabaseReference reference;
    private ValueEventListener valueEventListener;

    public Account_edit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_edit, container, false);
        reference = FirebaseDatabase.getInstance().getReference("users");

        EditEmail = view.findViewById(R.id.inputEmail);
        EditUsername = view.findViewById(R.id.inputUsername);
        EditPassword = view.findViewById(R.id.inputPassword);
        EditPhone = view.findViewById(R.id.inputPhone);
        editprofile = view.findViewById(R.id.editprofile);

        getdata();
        Ischanger();

        SavedEdit();

        return view;
    }

    public void getdata() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            Username = bundle.getString("username", "");
            Email = bundle.getString("email", "");
            Password = bundle.getString("password", "");
            Phone = bundle.getString("phone", "");

            EditUsername.setText("username " + Username);
            EditEmail.setText("email " + Email);
            EditPassword.setText("password " + Password);
            EditPhone.setText("phone " + Phone);
        }

        // Retrieve data from SharedPreferences (if needed)
        SharedPreferences sp = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        Username = sp.getString("save_username", null);
        Email = sp.getString("save_email", null);
        Password = sp.getString("save_password", null);
        Phone = sp.getString("save_phone", null);

        EditUsername.setText(Username);
        EditEmail.setText(Email);
        EditPassword.setText(Password);
        EditPhone.setText(Phone);
    }


    public boolean Ischanger() {
        String newEmail = EditEmail.getText().toString();
        String newUsername = EditUsername.getText().toString();
        String newPhone = EditPhone.getText().toString();
        String newPassword = EditPassword.getText().toString();
        if (!newUsername.equals(Username)) {
            reference.child(Username).child("username").setValue(newUsername);
            Username = newUsername;
            return true;
        } else if (!newEmail.equals(Email)) {
            reference.child(Username).child("email").setValue(newEmail);
            Email = newEmail;
            return true;
        } else if (!newPassword.equals(Password)) {
            reference.child(Username).child("password").setValue(newPassword);
            Password = newPassword;
            return true;
        } else if (!newPhone.equals(Phone)) {
            reference.child(Username).child("phone").setValue(newPhone);
            Phone = newPhone;
            return true;
        } else {
            return false;
        }
    }

    private void SavedEdit() {
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Ischanger()) {
                    Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "No changes made", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}