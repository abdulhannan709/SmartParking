package com.example.smartparking.renter.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.smartparking.R;


public class AddPost extends Fragment {


    ToggleButton tM;
    ToggleButton tT;
    ToggleButton tW;
    ToggleButton tTh;
    ToggleButton tF;
    ToggleButton tSat;
    ToggleButton tSun;
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
        tM =   getActivity().findViewById(R.id.tM);
        tT =   getActivity().findViewById(R.id.tT);
        tW =   getActivity().findViewById(R.id.tW);
        tTh =  getActivity().findViewById(R.id.tTh);
        tF =   getActivity().findViewById(R.id.tF );
        tSat = getActivity().findViewById(R.id.tSat);
        tSun = getActivity().findViewById(R.id.tSun);

        tM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    Toast.makeText(getContext(), "Monday", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                }
            }
        });

        String markedButtons= "";
        //Check individual items.
        if(tM.isChecked()){
            markedButtons +="Monday,";
        }
        if(tT.isChecked()){
            markedButtons +="Tuesday,";
        }
        if(tW.isChecked()){
            markedButtons +="Wednesday,";
        }
        if(tTh.isChecked()){
            markedButtons +="Thursday,";
        }
        if(tF.isChecked()){
            markedButtons +="Friday,";
        }
        if(tSat.isChecked()){
            markedButtons +="Saturday,";
        }
        if(tSun.isChecked()){
            markedButtons +="Sunday";
        }
        Toast.makeText(getContext(), markedButtons, Toast.LENGTH_SHORT).show();

        getMarkedDays();
    }

    void getMarkedDays()
    {

    }
}