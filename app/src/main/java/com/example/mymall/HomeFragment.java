package com.example.mymall;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private RecyclerView homePageRecyclerView;
    private List<CategoryModel> categoryModelList;
    private HomePageAdapter adapter;

    private FirebaseFirestore firebaseFirestore;


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
        categoryModelList = new ArrayList<CategoryModel>();


        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                // jitne documents ya data h utne time ye loop run hoga........
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        ///////////////// Banner Slider
//        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
//
//
//        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.cart_white,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.close_cross,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
//

        /////////////////Banner Slider ends

        //////////////////////////////////Horizontal product layout
        ///////////////////////////////// ye wo upar ke icons h category ke liye jo tere app ke hom epage me dikhte h
        ///////////////////////////////// jaise appliances, shoes, wall arts, etc.
//        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon,"Oppo","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.green_email,"Vivo","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.home_icon,"Poco","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Xiami","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"jio","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//

        //////////////////////////////////Horizontal product layout


        /////////////////////grid View
        ///////////////////test

        homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);

        final List<HomePageModel> homePageModelList = new ArrayList<>();
//        homePageModelList.add(new HomePageModel(0,sliderModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.logo,"#ffff00"));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.banner,"#000000"));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.logo,"#ffff00"));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.banner,"#000000"));

        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);

        firebaseFirestore.collection("CATEGORIES")
                .document("HOME")
                .collection("TOP_DEALS").orderBy("index")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if((long) documentSnapshot.get("view_type") == 0){
                                    // case 0 matlab jo data h (jo images h ) wo banner_slider ke h......
                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long)documentSnapshot.get("no_of_banners");

                                    for(long x = 1;x < no_of_banners+1; x++){

                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString(),
                                                documentSnapshot.get("banner_"+x+"_background").toString()));
                                    }
                                    homePageModelList.add(new HomePageModel(0,sliderModelList));
                                }

                                else if((long) documentSnapshot.get("view_type") == 1){
                                    homePageModelList.add(new HomePageModel(1,documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("background").toString()));
                                }
                                else if((long) documentSnapshot.get("view_type") == 2){

                                }
                                else if((long) documentSnapshot.get("view_type") == 3){

                                }

                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        ///////////////////test


        return view;
    }
    ///////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////
}














