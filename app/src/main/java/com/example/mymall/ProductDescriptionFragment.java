package com.example.mymall;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDescriptionFragment extends Fragment {

    private TextView descriptionBody;
    public String body;

    public ProductDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product_description, container, false);
        descriptionBody = view.findViewById(R.id.tv_product_description);
        descriptionBody.setText(body);

//        if(tabPosition == 0) {
//
//            // matlab jab koi bhi product ka description bhatate h tab
//            // agar first Description tab selected hoga to ye text daalna
//            // else agar 3rd tab selected hoga toh else wala text daalna
//
//            descriptionBody.setText(productDescription);
//        }
//        else{
//            descriptionBody.setText(productOtherDetails);
//        }
        return view;
    }

}





















