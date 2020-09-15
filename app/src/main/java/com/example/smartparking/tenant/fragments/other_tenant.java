package com.example.smartparking.tenant.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.MapsActivity;
import com.example.smartparking.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class other_tenant extends Fragment implements LocationListener {

    Button button;
    Location mLastLocation;
    TextView textView_lat;
    TextView textView_long;
    FusedLocationProviderClient mFusedLocationClient;
    SupportMapFragment supportMapFragment;
    LocationManager locationManager;
    ProgressDialog loading;

    public other_tenant() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_maps, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        button = getActivity().findViewById(R.id.map_button);
        textView_lat = getActivity().findViewById(R.id.lat);
        textView_long = getActivity().findViewById(R.id.lon);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading = ProgressDialog.show(getContext(), "Fetching location data", "Ruko jara, sabar kro");
                loading.setCancelable(false);
                getLocation();
            }
        });
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
    public void onLocationChanged(Location location) {


        textView_lat.setText("Latitude: " + location.getLatitude());
        textView_long.setText("Longitude: " + location.getLongitude());
        loading.dismiss();

    }

}