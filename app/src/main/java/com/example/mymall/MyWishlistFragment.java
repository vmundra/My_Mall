package com.example.mymall;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {

    private RecyclerView wishlistRecyclerView;

    public MyWishlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Pixel 3(Mate Black)",3,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Poco 3(Mate Black)",0,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Xoamoi 3(Mate Black)",5,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Pixel 3(Mate Black)",1,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Pixel 3(Mate Black)",0,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.mipmap.image2,"Pixel 3(Mate Black)",3,"2",256,"Rs.49,999/-","Rs.59,999/-","Cash On Delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList,true);
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return view;
    }

}
















