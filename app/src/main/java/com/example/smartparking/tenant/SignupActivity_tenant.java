package com.example.smartparking.tenant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity_tenant extends AppCompatActivity {

    EditText emailedittxt;
    EditText passwordedittxt;
    private FirebaseAuth mauth;
    private FirebaseFirestore db;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailedittxt = findViewById(R.id.txtemail_signup);
        passwordedittxt = findViewById(R.id.txtpassword_signup);
        signup = findViewById(R.id.btnsignup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupTenant();
            }
        });



    }

    void SignupTenant()
    {

        final String email = emailedittxt.getText().toString().trim();
        String password = passwordedittxt.getText().toString().trim();

        mauth = FirebaseAuth.getInstance();

        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = mauth.getCurrentUser();
                    AddTenantToCollection(email);
                    Toast.makeText(SignupActivity_tenant.this, "Authentication Successfull.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignupActivity_tenant.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }

    private void AddTenantToCollection(String email)
    {
        db = FirebaseFirestore.getInstance();

        Map<String, Object> userdata = new HashMap<>();
        userdata.put("email", email);

        db.collection("tenant").document(email)
                .set(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(SignupActivity_tenant.this, "DocumentSnapshot successfully written!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity_tenant.this, "Task Failed", Toast.LENGTH_SHORT).show();
                    }
                });
        
        
        
    }

}

