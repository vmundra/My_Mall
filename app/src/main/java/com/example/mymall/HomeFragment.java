package com.example.mymall;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private  RecyclerView testing;


    public HomeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","ECT"));
        categoryModelList.add(new CategoryModel("link","jjhjhjh"));
        categoryModelList.add(new CategoryModel("link","m m m m m "));
        categoryModelList.add(new CategoryModel("link","jjjjj"));
        categoryModelList.add(new CategoryModel("link","ergeh6666666"));
        categoryModelList.add(new CategoryModel("link","sesebthrt"));
        categoryModelList.add(new CategoryModel("link","dfgd"));
        categoryModelList.add(new CategoryModel("link","jjjjj"));
        categoryModelList.add(new CategoryModel("link","jjjjj"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        /////////////////
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.cart_white,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.close_cross,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));

        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
        sliderModelList.add(new SliderModel(R.mipmap.cart_white,"#077AE4"));

        /////////////////

        //////////////////////////////////Horizontal product layout
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon,"Oppo","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.green_email,"Vivo","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.home_icon,"Poco","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Xiami","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"jio","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));


        //////////////////////////////////Horizontal product layout



        /////////////////////grid View
        ///////////////////test

        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.logo,"#ffff00"));
        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.mipmap.banner,"#000000"));
        homePageModelList.add(new HomePageModel(0,sliderModelList));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ///////////////////test




        return view;
    }
  ///////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
}














