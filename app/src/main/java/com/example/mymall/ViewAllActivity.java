package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;
    public static List<WishlistModel> wishlistModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);


        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if(layout_code == 0) {

            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);


            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else if(layout_code == 1) {
            gridView.setVisibility(View.VISIBLE);



//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.app_icon, "Oppo", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.green_email, "Vivo", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.home_icon, "Poco", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Xiami", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "jio", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));
//            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.image2, "Redmi", "SD 200", "Rs.10,000"));


            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}






























