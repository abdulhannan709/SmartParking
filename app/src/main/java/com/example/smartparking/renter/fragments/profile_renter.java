package com.example.smartparking.renter.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartparking.R;
import com.example.smartparking.WelcomeScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class profile_renter extends Fragment {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    String mail;
    EditText editText_fname;
    EditText editText_lname;
    EditText editText_phone;
    EditText editText_address;
    TextView textView_email;
    TextView textView_logout;
    Button button;

    public profile_renter() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_profile_renter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editText_fname=getActivity().findViewById(R.id.renter_fname);
        editText_lname=getActivity().findViewById(R.id.renter_lname);
        editText_phone=getActivity().findViewById(R.id.renter_phone);
        editText_address=getActivity().findViewById(R.id.renter_address);
        textView_email=getActivity().findViewById(R.id.renter_email);
        textView_logout=getActivity().findViewById(R.id.renter_logout);
        button=getActivity().findViewById(R.id.renter_update);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateData();
            }
        });

        textView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth.signOut();

                Intent intent=new Intent(getContext(), WelcomeScreen.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mail=currentUser.getEmail().toString();


        DocumentReference docRef = db.collection("renter").document(mail);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        editText_fname.setText(document.getData().get("fname").toString().trim());
                        editText_lname.setText(document.getData().get("lname").toString().trim());
                        editText_address.setText(document.getData().get("address").toString().trim());
                        editText_phone.setText(document.getData().get("phone").toString().trim());
                        textView_email.setText(document.getData().get("email").toString().trim());


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


    }

    void UpdateData(){
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("fname", editText_fname.getText().toString());
        userdata.put("lname", editText_lname.getText().toString());
        userdata.put("phone", editText_phone.getText().toString());
        userdata.put("address", editText_address.getText().toString());

        DocumentReference docRef = db.collection("renter").document(mail);
        docRef
                .update(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }
}