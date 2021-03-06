package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.mymall.DBqueries.lists;
import static com.example.mymall.DBqueries.loadFragmentData;
import static com.example.mymall.DBqueries.loadedCategoriesNames;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;
    private HomePageAdapter adapter;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);


        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null","#000000"));
        sliderModelFakeList.add(new SliderModel("null","#000000"));
        sliderModelFakeList.add(new SliderModel("null","#000000"));
        sliderModelFakeList.add(new SliderModel("null","#000000"));
        sliderModelFakeList.add(new SliderModel("null","#000000"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();

        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("","","","",""));


        homePageModelFakeList.add(new HomePageModel(0,sliderModelFakeList));
        homePageModelFakeList.add(new HomePageModel(1,"","#ffffff"));
        homePageModelFakeList.add(new HomePageModel(2,"","#000000",horizontalProductScrollModelFakeList,new ArrayList<WishlistModel>()));
        homePageModelFakeList.add(new HomePageModel(3,"","#000000",horizontalProductScrollModelFakeList));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////
        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();
//
//        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.mipmap.cart_white,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.close_cross,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#077AE4"));
//
//        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.green_email,"#077AE4"));
//        sliderModelList.add(new SliderModel(R.mipmap.cart_white,"#077AE4"));

        /////////////////

        //////////////////////////////////Horizontal product layout
        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
//
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon,"Oppo","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.green_email,"Vivo","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.home_icon,"Poco","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Xiami","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"jio","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2,"Redmi","SD 200","Rs.10,000"));
//

        //////////////////////////////////Horizontal product layout



        /////////////////////grid View
        ///////////////////test
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(RecyclerView.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);
        adapter = new HomePageAdapter(homePageModelFakeList);

//        List<HomePageModel> homePageModelList = new ArrayList<>();
//        homePageModelList.add(new HomePageModel(0,sliderModelList));
//        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.logo,"#ffff00"));
//        homePageModelList.add(new HomePageModel(3,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(2,"Deals of the day",horizontalProductScrollModelList));
//        homePageModelList.add(new HomePageModel(1,R.mipmap.banner,"#000000"));
//        homePageModelList.add(new HomePageModel(0,sliderModelList));

        int listPosition = 0;

        for(int x = 0; x<loadedCategoriesNames.size();x++){
            if(loadedCategoriesNames.get(x).equals(title.toUpperCase())){
                listPosition = x;
            }
        }

        if(listPosition == 0){
            loadedCategoriesNames.add(title.toUpperCase());
            lists.add(new ArrayList<HomePageModel>());
            loadFragmentData(categoryRecyclerView,this,loadedCategoriesNames.size()-1,title);
        }
        else {
            adapter = new HomePageAdapter(lists.get(listPosition));
        }
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // ye h top right side ke 3 items ke liye, unme se koi ek bhi selecct hua to uska code yaha h......

        int id = item.getItemId();
        if(id == R.id.main_search_icon){

            return true;
        }
        else if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


















