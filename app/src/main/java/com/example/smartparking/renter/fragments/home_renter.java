package com.example.smartparking.renter.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartparking.R;

public class home_renter extends Fragment {

    ImageView imageView;
    public home_renter() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_home_renter, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imageView = getActivity().findViewById(R.id.qrimage);
        String imageinstring = "iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQCAYAAACAvzbMAAAAAXNSR0IArs4c6QAAAARzQklUCAgICHwIZIgAAAb0SURBVHic7dxBjttAEARB0dD/v0z/wIDy0GwPI+6CZkktEnOp677v+wMAP/rz9AEA+D8JCACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICADJ9+kDPOm6rqeP8Br3ff/8mfJ+yvcUp52NZuqdbuUGAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACSvHlMs3j6e9vnMjfUZYJw7m9+1EcrCDQSAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIjCkO2DzSZkRv9zjkZn7XuIEAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiTFFjlJG9DaPAsJmbiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJN+nD/AG930/fQT+Yer9XNf182c2/3Y2n40ZbiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAYkzxR2UQj2ZqfPC0kcPC75rCDQSAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIrvu0VTiOcdrAn381TuMGAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACTfpw/wpKmxvjKid9rZyvd4bru/5zSe2+/cQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIDk1WOKxeZxu/I9pw3InTbAuNnUc5ty2vuZ4AYCQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJNdtQewnm4cRTxsSPG3ocfM79dzOe24T3EAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASA5Pv0AZ40NSS42dQY3OZnbVBy9/thLzcQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIHn1mKIhwWbz+OCUzWcrNg8wbj7b27mBAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAMmrxxSnGN5rDOKd99uZ4rnNcAMBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAklePKRrrm1PG7cpnyjvdPA552ijg1DstTnvWE9xAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgOTVY4qFwbXzRig3jxx61s1pQ5xbuYEAkAgIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiTHFAZsH8TaPwW0e3tv8PVOMD+IGAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACTGFCEwJNj+HsOiZ3EDASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABJjiqy1eaxv6my+pyln2/z3bOUGAkAiIAAkAgJAIiAAJAICQCIgACQCAkAiIAAkAgJAIiAAJAICQCIgACTGFAe8fXCt2jwKeJqpZ+BZn8UNBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEiMKf7IGByfz9xA5tTvzeAnhRsIAImAAJAICACJgACQCAgAiYAAkAgIAImAAJAICACJgACQCAgAiYAAkFy3FTUAAjcQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREAASAQEgERAAEgEBIBEQABIBASAREACSv1/DBitFOHh5AAAAAElFTkSuQmCC";
        Bitmap bitmap = StringToBitMap(imageinstring);


        imageView.setImageBitmap(bitmap);


    }

    //bitmap to string
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}