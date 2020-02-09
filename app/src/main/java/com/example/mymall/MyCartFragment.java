package com.example.mymall;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    private RecyclerView cartItemsRecyclerView;
    private Button continueBtn;

    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);
        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();

        cartItemModelList.add(new CartItemModel(0,R.mipmap.image2,"Iphone 13",2,"Rs.68,9889/-","Rs.75,667/-",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.mipmap.image2,"Galaxy Grand 13",0,"Rs.68,9889/-","Rs.75,667/-",1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.mipmap.image2,"Jio 9",2,"Rs.68,9889/-","Rs.75,667/-",1,2,0));
        cartItemModelList.add(new CartItemModel(1,"Price (3 items)","Rs.90,000/-","Free","Rs.1,20,000/-","Rs.30,000/-"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),DeliveryActivity.class);
                getContext().startActivity(deliveryIntent);

            }
        });

        return view;
    }

}




















