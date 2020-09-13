package com.example.smartparking.renter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.R;
import com.example.smartparking.tenant.SignupActivity_tenant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class SignupActivity_renter extends AppCompatActivity {

    EditText email_txt;
    EditText password_txt;
    private FirebaseAuth mauth;
    private FirebaseFirestore db;

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
        final String email = email_txt.getText().toString().trim();
        String password = password_txt.getText().toString().trim();

        mauth = FirebaseAuth.getInstance();

        mauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = mauth.getCurrentUser();
                    AddRenterToCollection(email);
                    Toast.makeText(SignupActivity_renter.this, "Authentication Successful.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignupActivity_renter.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void AddRenterToCollection(String email) {
        db = FirebaseFirestore.getInstance();
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("email", email);


        db.collection("renter").document(email)
                .set(userdata)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(SignupActivity_renter.this, "DocumentSnapshot successfully written!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignupActivity_renter.this, "Task Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    private String GeneratQR()
//    {
//        String getQr = null;
//        Bitmap bitmap;
//        QRGEncoder qrgEncoder = new QRGEncoder("25,some@mail.com", null, QRGContents.Type.TEXT, 400);
//        try {
//            // Getting QR-Code as Bitmap
//            bitmap = qrgEncoder.encodeAsBitmap();
////            // Setting Bitmap to ImageView
////            qrImage.setImageBitmap(bitmap);
//            getQr = BitMapToString(bitmap);
//        } catch (WriterException e) {
//            Log.d("TAG", e.toString());
//        }
//
//        return getQr;
//    }

//    //String to bitmap
//    public String BitMapToString(Bitmap bitmap){
//        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
//        byte [] b=baos.toByteArray();
//        String temp= Base64.encodeToString(b, Base64.DEFAULT);
//        return temp;
//    }



}