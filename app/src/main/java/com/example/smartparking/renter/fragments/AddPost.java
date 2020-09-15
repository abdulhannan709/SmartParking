package com.example.smartparking.renter.fragments;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.smartparking.R;
import com.example.smartparking.renter.SignupActivity_renter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddPost extends Fragment implements CompoundButton.OnCheckedChangeListener, LocationListener{


    ToggleButton tM, tT, tW, tTh, tF, tSat, tSun;
    String markedButtons = "";
    TextView starttime,endtime,addLong,addLat;
    EditText addTitle, addAddress, addRate;
    Button getlocation, addbtn;
    FusedLocationProviderClient mFusedLocationClient;
    LocationManager locationManager;
    ProgressDialog loading;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    public AddPost() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toggles
        tM = getActivity().findViewById(R.id.tM);
        tT = getActivity().findViewById(R.id.tT);
        tW = getActivity().findViewById(R.id.tW);
        tTh = getActivity().findViewById(R.id.tTh);
        tF = getActivity().findViewById(R.id.tF);
        tSat = getActivity().findViewById(R.id.tSat);
        tSun = getActivity().findViewById(R.id.tSun);

        //Buttons
        getlocation = getActivity().findViewById(R.id.btngetloc);
        addbtn = getActivity().findViewById(R.id.btnaddParking);

        //Textviews
        starttime = getActivity().findViewById(R.id.addstartTime);
        endtime = getActivity().findViewById(R.id.addEndTime);
        addLat = getActivity().findViewById(R.id.addLat);
        addLong = getActivity().findViewById(R.id.addLong);

        //EditText
        addTitle = getActivity().findViewById(R.id.addtitle);
        addAddress = getActivity().findViewById(R.id.addaddress);
        addRate = getActivity().findViewById(R.id.addRate);

        //Toggle Click listener
        tM.setOnCheckedChangeListener(this);
        tT.setOnCheckedChangeListener(this);
        tW.setOnCheckedChangeListener(this);
        tTh.setOnCheckedChangeListener(this);
        tF.setOnCheckedChangeListener(this);
        tSat.setOnCheckedChangeListener(this);
        tSun.setOnCheckedChangeListener(this);

        //Set Start Time
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        starttime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        //Set End Time
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endtime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        //Get User Current Location
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        getlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(getContext(), "Fetching location data", "Ruko jara, sabar kro");
                loading.setCancelable(false);
                getLocation();
            }
        });

        //Adding All data to Cloud Firestore


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //fetching aut email and intitliazing cloud instance
                db = FirebaseFirestore.getInstance();
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                String mail = currentUser.getEmail();

                String Title = addTitle.getText().toString().trim();
                String Address = addAddress.getText().toString().trim();
                String Rate = addRate.getText().toString().trim();
                String activedays = markedButtons;
                String startTime = starttime.getText().toString().trim();
                String endTime = endtime.getText().toString().trim();
                String Lat = addLat.getText().toString().trim();
                String Lon = addLong.getText().toString();



                Map<String, Object> userdata = new HashMap<>();
                userdata.put("email", mail);
                userdata.put("title", Title);
                userdata.put("address", Address);
                userdata.put("rate", Rate);
                userdata.put("activedays", activedays);
                userdata.put("starttime", startTime);
                userdata.put("endtime", endTime);
                userdata.put("Latitude", Lat);
                userdata.put("Longitude", Lon);


                db.collection("parking").document(mail)
                        .set(userdata)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getContext(), "DocumentSnapshot successfully written!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "Task Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });



    }



    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


        switch (compoundButton.getId()) {
            case R.id.tM:
                if (b) {
                    markedButtons += "Monday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Monday,","");
                }
                break;
            case R.id.tT:
                if (b) {
                    markedButtons += "Tuesday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Tuesday,","");
                }
                break;
            case R.id.tW:
                if (b) {
                    markedButtons += "Wednesday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Wednesday,","");
                }
                break;
            case R.id.tTh:
                if (b) {
                    markedButtons += "Thursday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Thursday,","");
                }
                break;
            case R.id.tF:
                if (b) {
                    markedButtons += "Friday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Friday,","");
                }
                break;
            case R.id.tSat:
                if (b) {
                    markedButtons += "Saturday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Saturday,","");
                }
                break;
            case R.id.tSun:
                if (b) {
                    markedButtons += "Sunday,";
                }
                else
                {
                    markedButtons = markedButtons.replace("Sunday,","");
                }
                break;
        }

        Toast.makeText(getContext(), markedButtons, Toast.LENGTH_SHORT).show();

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        Double lat = location.getLatitude();
        Double lon = location.getLongitude();
        addLat.setText(lat.toString());
        addLong.setText(lon.toString());
        loading.dismiss();
    }
}