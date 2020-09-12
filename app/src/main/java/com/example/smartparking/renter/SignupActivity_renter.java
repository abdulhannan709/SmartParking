package com.example.smartparking.renter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.tenant.SignupActivity_tenant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity_renter extends AppCompatActivity {

    EditText email_txt;
    EditText password_txt;
    private FirebaseAuth mauth;

    Button signup_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email_txt = findViewById(R.id.txtemail_signup);
        password_txt = findViewById(R.id.txtpassword_signup);
        signup_btn = findViewById(R.id.btnsignup);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignupRenter();
            }
        });
    }


    void SignupRenter()
    {
        String email = email_txt.getText().toString().trim();
        String password = password_txt.getText().toString().trim();

        mauth = FirebaseAuth.getInstance();

        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = mauth.getCurrentUser();
                    Toast.makeText(SignupActivity_renter.this, "Authentication Successfull.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignupActivity_renter.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}