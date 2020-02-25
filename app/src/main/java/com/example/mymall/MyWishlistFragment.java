package com.example.mymall;


import android.app.Dialog;
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
    private Dialog loadingDialog;
    public static WishlistAdapter wishlistAdapter;

    public MyWishlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        wishlistRecyclerView = view.findViewById(R.id.my_wishlist_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        if(DBqueries.wishlistModelList.size() == 0){

            DBqueries.wishList.clear();
            DBqueries.loadWishList(getContext(),loadingDialog,true);
        }
        else{
            loadingDialog.dismiss();
        }

        wishlistAdapter = new WishlistAdapter(DBqueries.wishlistModelList,true);
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return view;
    }

    //////////////tune mostly DBqueries me ya kahi code me kuch gadbad ki h, jiski wajah se
    ////////////jab bhi tu add to wish list ke heart wale icon pr click krta h to pehle to wo red ho jata h
    ///////////// but badme agar app ko cache se hata kr run khola
    ////////////// to usme jakr agar wapas usko apne wish list se hataya,
    /////////////// matlab agar heart ko red se grey kiya na to databse me 1 se 0 hona chahiye
    ////////////// but wo 1 ka 1 hi rehta h
    /////////////// and agar wapas red kiya to wo databse me 2 ho jata h

}
















