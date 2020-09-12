package com.example.smartparking.renter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.tenant.LoginActivity_tenant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity_renter extends AppCompatActivity {

    EditText email_txt;
    EditText password_txt;
    Button signin_btn;
    private FirebaseAuth mauth;
    TextView signupscreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupscreen = findViewById(R.id.txtsignupscreen);
        signupscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginActivity_renter.this,SignupActivity_renter.class);
                startActivity(i);

            }
        });
        email_txt = findViewById(R.id.txtemail_login);
        password_txt = findViewById(R.id.txtpassword_login);
        signin_btn = findViewById(R.id.btnlogin);


        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInrenter();
            }
        });

    }
    private void LogInrenter() {

        String email = email_txt.getText().toString().trim();
        String password = password_txt.getText().toString().trim();

        mauth = FirebaseAuth.getInstance();

        mauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser user = mauth.getCurrentUser();
                    Toast.makeText(LoginActivity_renter.this, "SignIn Succesfull.", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(LoginActivity_renter.this, "SignIn failed.", Toast.LENGTH_SHORT).show();

                }

            }
        });


    }

}