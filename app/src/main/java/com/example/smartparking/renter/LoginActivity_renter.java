package com.example.smartparking.renter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.tenant.LoginActivity_tenant;

public class LoginActivity_renter extends AppCompatActivity {

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

    }
}