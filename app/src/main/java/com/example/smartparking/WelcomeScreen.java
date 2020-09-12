package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.smartparking.renter.LoginActivity_renter;
import com.example.smartparking.tenant.LoginActivity_tenant;
import com.example.smartparking.tenant.TenantMain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeScreen extends AppCompatActivity {

    CardView tenantcard;
    CardView rentarcard;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser == null)
//        {
//            Log.d("TAGU","No User");
//        }
//        else
//        {
//            Log.d("TAGU",currentUser.getEmail().toString());
//
//            Intent i = new Intent(WelcomeScreen.this, TenantMain.class);
//            startActivity(i);
//            finish();
//        }



    }

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