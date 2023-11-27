package com.example.foodninja;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Updateprofile extends Fragment {

    private TextView Username, Email, Phone, Password;
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    private Button editprofile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_updateprofile, container, false);
        Email = view.findViewById(R.id.email);
        Username = view.findViewById(R.id.username);
        Password = view.findViewById(R.id.password);
        Phone = view.findViewById(R.id.phone);
        editprofile = view.findViewById(R.id.editprofile);
        getdata();
        navigate();
        return view;
    }


    public void getdata() {
        String email = requireActivity().getIntent().getStringExtra("email");
        String username = requireActivity().getIntent().getStringExtra("username");
        String password = requireActivity().getIntent().getStringExtra("password");
        String phone = requireActivity().getIntent().getStringExtra("phone");

        Username.setText("username " + username);
        Email.setText("email " + email);
        Password.setText("password " + password);
        Password.setText("phone" + phone);


        sp = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        username = sp.getString("save_username", null);
        email = sp.getString("save_email", null);
        password = sp.getString("save_password", null);
        phone = sp.getString("save_phone", null);

        Username.setText(username);
        Email.setText(email);
        Password.setText(password);
        Phone.setText(phone);
    }

    public void navigate() {
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Fragment fragment=new Account_edit();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragment).commit();
            }
        });
    }
}