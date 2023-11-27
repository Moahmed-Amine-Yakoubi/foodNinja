package com.example.foodninja;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import nl.joery.animatedbottombar.AnimatedBottomBar;


public class AccountFragment extends Fragment {

    private DatabaseReference reference;
    private String userID;
    private TextView Username, Email;
    private Button btnlogout, btnaccount, btnlocation;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;


    public AccountFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        Email = view.findViewById(R.id.email);
        Username = view.findViewById(R.id.username);
        btnaccount = view.findViewById(R.id.btnAccount);
        btnlocation=view.findViewById(R.id.btnLocation);
        btnlogout = view.findViewById(R.id.btnLogout);
        navigate();
        getdata();
        Logout();
        return view;
    }


    public void getdata() {
        String email = requireActivity().getIntent().getStringExtra("email");
        String username = requireActivity().getIntent().getStringExtra("username");


        Username.setText("username " + username);
        Email.setText("email " + email);


        sp = requireActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        username = sp.getString("save_username", null);
        email = sp.getString("save_email", null);


        Username.setText(username);
        Email.setText(email);
    }

    public void navigate() {
        btnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Fragment fragmentprofile=new Updateprofile();
                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,fragmentprofile).commit();


            }
        });
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentlocation=new Location();
                FragmentTransaction transactionlocation=getActivity().getSupportFragmentManager().beginTransaction();
                transactionlocation.replace(R.id.fragment_container,fragmentlocation).commit();
            }
        });
    }


    public void Logout() {
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = sp.edit();
                edit.clear();
                edit.apply(); // Don't forget to apply the changes
                Intent intent = new Intent(requireActivity(), Athentification.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });
    }
}