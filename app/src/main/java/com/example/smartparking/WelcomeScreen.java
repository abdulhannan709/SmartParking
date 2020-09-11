package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartparking.renter.LoginActivity_renter;
import com.example.smartparking.tenant.LoginActivity_tenant;

public class WelcomeScreen extends AppCompatActivity {

    CardView tenantcard;
    CardView rentarcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        tenantcard = findViewById(R.id.tenantcard);
        rentarcard = findViewById(R.id.rentercard);

        //TENANT APP ROUTE
        tenantcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeScreen.this,LoginActivity_tenant.class);
                startActivity(i);
            }
        });

        //RENTER APP ROUTE
        rentarcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WelcomeScreen.this, LoginActivity_renter.class);
                startActivity(i);
            }
        });

    }
}