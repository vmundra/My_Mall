package com.example.mymall;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment {

    private RecyclerView myOrdersRecyclerView;

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrdersRecyclerView = view.findViewById(R.id.my_orders_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.mipmap.image2,2,"Galaxy 3(Black)","Delivered on Sunday-9th Feb-2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.mipmap.image2,5,"Xiaomi 3(Black)","Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.mipmap.logo,3,"Poco 3(Black)","Delivered on Sunday-9th Feb-2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.mipmap.image2,1,"Galaxy 3(Black)","Delivered on Sunday-9th Feb-2020"));
        myOrderItemModelList.add(new MyOrderItemModel(R.mipmap.image2,4,"Galaxy 3(Black)","Cancelled"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }

}
























