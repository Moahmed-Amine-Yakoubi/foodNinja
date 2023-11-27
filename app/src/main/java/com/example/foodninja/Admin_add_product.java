package com.example.foodninja;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Admin_add_product extends Fragment {

    private Button btnaddproduct;
    private EditText inputproductname, inputcategorie, inputprice;
    FirebaseDatabase database;
    DatabaseReference reference;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_add_product, container, false);

        btnaddproduct = view.findViewById(R.id.btn_add_product);

        inputproductname = view.findViewById(R.id.inputproductTtile);
        inputcategorie = view.findViewById(R.id.inputproductcategorie);
        inputprice = view.findViewById(R.id.inputproductprice);

        sp = requireActivity().getSharedPreferences("products", Context.MODE_PRIVATE);
        editor = sp.edit();

        Addproduct(); // Assuming you have this method implemented

        return view;
    }




    private void Addproduct() {
        btnaddproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("products");
                String productname = inputproductname.getText().toString();
                String categorie = inputcategorie.getText().toString();
                String price = inputprice.getText().toString();


                if (productname.isEmpty() || productname.length() < 4) {
                    inputproductname.setError("enter proper username");
                } else if (categorie.isEmpty() || categorie.length() < 4) {
                    inputcategorie.setError("enter proper password");
                } else if (price.isEmpty()) {
                    inputprice.setError("enter proper number phone");
                } else {
                    reference.child(productname).child("productname").setValue(productname);
                    reference.child(productname).child("categorie").setValue(categorie);
                    reference.child(productname).child("pprice").setValue(price);


                    editor.putString("save_username", productname);
                    editor.putString("save_password", categorie);
                    editor.putString("save_email", price);

                    editor.commit();
                    Toast.makeText(requireContext(), "user registered successfuly ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(requireContext(), Dashboard_admin.class);
                    startActivity(intent);
                }
            }
        });
    }
}