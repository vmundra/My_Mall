package com.example.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import static com.example.mymall.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private FloatingActionButton addToWishListBtn;
    private Button coupenRedeemBtn;
    private static boolean ALREADY_ADDED_TO_WISHLIST = false;
    List<String> productImages;

    private FirebaseFirestore firebaseFirestore;

    private Button buyNowBtn;

    //////////////////////coupenDialog
    public static TextView coupenTitle, tvCodIndicator,rewardTitle, rewardBody;
    public static TextView coupenBody, productPrice, cuttedPrice;
    private ImageView codIndicator;
    public static TextView coupenExpiryDate, productTitle, averageRatingMiniview, totalRatingMiniView;
    private static RecyclerView coupensRecyclerView;
    private static LinearLayout selectedCoupen;
    //////////////////////coupenDialog


    ///////////////////////////product description
    private ConstraintLayout productDetailsOnlyContainer;
    private TextView productOnlyDescriptionBody;
    private ConstraintLayout productDetailsTabsContainer;
    private ViewPager productDetailsViewpager;
    private TabLayout productDetailsTabLayout;
    public static String productDescription,productOtherDetails;
    public static int tabPosition = -1;

    public static List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();

    ///////////////////////////product description


    /////////////////rating layout
    private LinearLayout rateNowContainer;
    private TextView totalRatings, totalRatingsFigure;
    private LinearLayout ratingsNoContainer;
    private LinearLayout ratingsPrgressBarContainer;
    /////////////////rating layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        addToWishListBtn = findViewById(R.id.add_to_wishlist_btn);
        buyNowBtn = findViewById(R.id.buy_now_btn);
        coupenRedeemBtn = findViewById(R.id.coupen_redemption_btn);
        productTitle = findViewById(R.id.product_title);
        averageRatingMiniview = findViewById(R.id.tv_product_rating_miniview);
        totalRatingMiniView = findViewById(R.id.total_ratings_miniview);
        productPrice = findViewById(R.id.product_price);
        cuttedPrice = findViewById(R.id.cutted_price);
        codIndicator = findViewById(R.id.cod_indicator_imageview);
        tvCodIndicator = findViewById(R.id.tv_cod_indicator);
        rewardTitle = findViewById(R.id.reward_title);
        rewardBody = findViewById(R.id.reward_body);

        productDetailsTabsContainer = findViewById(R.id.product_details_tabs_container);
        productDetailsOnlyContainer = findViewById(R.id.product_details_container);
        productOnlyDescriptionBody = findViewById(R.id.product_details_body);

        firebaseFirestore = FirebaseFirestore.getInstance();

        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTabLayout = findViewById(R.id.product_details_tablayout);
        totalRatings = findViewById(R.id.total_ratings);
        ratingsNoContainer = findViewById(R.id.ratings_numbers_container);
        totalRatingsFigure = findViewById(R.id.total_ratings_figure);
        ratingsPrgressBarContainer = findViewById(R.id.ratings_progressbar_container);

        productImages = new ArrayList<>();


        firebaseFirestore.collection("PRODUCTS").document("KzFEqfOLg8xqHeNMygrK")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    DocumentSnapshot documentSnapshot = task.getResult();

                    for(long x = 1; x< (long)documentSnapshot.get("no_of_product_images")+1;x++){

                        productImages.add(documentSnapshot.get("product_image_"+x).toString());
                    }
                    ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
                    productImagesViewPager.setAdapter(productImagesAdapter);
                    productTitle.setText(documentSnapshot.get("product_title").toString());
                    averageRatingMiniview.setText(documentSnapshot.get("average_rating").toString());
                    totalRatingMiniView.setText("("+(long)documentSnapshot.get("total_ratings")+") ratings");
                    productPrice.setText("Rs."+documentSnapshot.get("product_price").toString()+"/-");
                    cuttedPrice.setText("Rs."+documentSnapshot.get("cutted_price").toString()+"/-");
                    rewardTitle.setText(documentSnapshot.get("free_coupens")+" "+documentSnapshot.get("free_coupen_title").toString());
                    rewardBody.setText(documentSnapshot.get("free_coupen_body").toString());

                    if((boolean)documentSnapshot.get("use_tab_layout")){
                        productDetailsTabsContainer.setVisibility(View.VISIBLE);
                        productDetailsOnlyContainer.setVisibility(View.GONE);

                        productDescription = documentSnapshot.get("product_description").toString();
                        productOtherDetails = documentSnapshot.get("product_other_details").toString();

                        for (long x =1; x< (long)documentSnapshot.get("total_specification_titles")+1;x++){

                           productSpecificationModelList.add(new ProductSpecificationModel(0,
                                    documentSnapshot.get("specification_title_"+x).toString()));


                            for(long m = 1;m<(long)documentSnapshot.get("specification_title_"+x+"_total_fields")+1;m++){
                               productSpecificationModelList.add(new ProductSpecificationModel(1,
                                        documentSnapshot.get("specification_title_"+x+"_field_"+m+"_name").toString(),
                                        documentSnapshot.get("specification_title_"+x+"_field_"+m+"_value").toString()));
                            }
                        }
                    }
                    else{
                        productDetailsTabsContainer.setVisibility(View.GONE);
                        productDetailsOnlyContainer.setVisibility(View.VISIBLE);
                        productOnlyDescriptionBody.setText(documentSnapshot.get("product_description").toString());
                    }
                    totalRatings.setText((long)documentSnapshot.get("total_ratings")+" ratings");

                    for(int x = 0; x<5;x++){
                        TextView rating = (TextView) ratingsNoContainer.getChildAt(x);
                        rating.setText(String.valueOf((long)documentSnapshot.get((5-x)+"_star")));

                        ProgressBar progressBar = (ProgressBar) ratingsPrgressBarContainer.getChildAt(x);
                        int maxProgress = Integer.parseInt(String.valueOf((long)documentSnapshot.get("total_ratings")));
                        progressBar.setMax(maxProgress);
                        progressBar.setProgress(Integer.parseInt(String.valueOf((long)documentSnapshot.get((5-x)+"_star"))));

                    }
                    totalRatingsFigure.setText(String.valueOf((long)documentSnapshot.get("total_ratings")));


                    if((boolean)documentSnapshot.get("COD")){
                        codIndicator.setVisibility(View.VISIBLE);
                        tvCodIndicator.setVisibility(View.VISIBLE);
                    }
                    else{
                        codIndicator.setVisibility(View.INVISIBLE);
                        tvCodIndicator.setVisibility(View.INVISIBLE);
                    }
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(ProductDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
        });


        viewpagerIndicator.setupWithViewPager(productImagesViewPager,true);

        addToWishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ALREADY_ADDED_TO_WISHLIST == true){
                    ALREADY_ADDED_TO_WISHLIST = false;
                    addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }
                else{
                    ALREADY_ADDED_TO_WISHLIST = true;
                    addToWishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTabLayout.getTabCount()));
        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));

        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /////////////////rating layout
        rateNowContainer = findViewById(R.id.rate_now_container);

        for(int x =0; x<rateNowContainer.getChildCount(); x++){
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
        /////////////////rating layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(ProductDetailsActivity.this,DeliveryActivity.class);
                startActivity(deliveryIntent);
            }
        });

        ///////////////////////////////coupen dialog
        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_reedem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
        coupensRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerview);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);

        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);

        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.coupen_title);
        coupenExpiryDate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);


        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        coupensRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback","till 2nd,June 2020","GET 30% OFF on all the elctronic products through Paytm"));
        rewardModelList.add(new RewardModel("Discount","till 2nd,June 2020","GET 30% OFF on all the elctronic products through Paytm"));
        rewardModelList.add(new RewardModel("Buy One get one free","till 2nd,June 2020","GET 30% OFF on all the elctronic products through Paytm"));
        rewardModelList.add(new RewardModel("Casback","till 2nd,June 2020","GET 30% OFF on all the elctronic products through Paytm"));
        rewardModelList.add(new RewardModel("Casback","till 2nd,June 2020","GET 30% OFF on all the elctronic products through Paytm"));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList,true);
        coupensRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecyclerView();
            }
        });
        ////////////////////////////////////////////// coupen dialog

        coupenRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCoupenPriceDialog.show();
            }
        });

    }

    public static void showDialogRecyclerView(){

        if(coupensRecyclerView.getVisibility() == View.GONE){
            coupensRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        }
        else{
            coupensRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }
    }

    private void setRating(int starPosition){

        for(int x = 0; x<rateNowContainer.getChildCount();x++){

            ImageView starBtn = (ImageView)rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));

            if(x <= starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // ye h top right side ke 3 items ke liye, unme se koi ek bhi selecct hua to uska code yaha h......

        int id = item.getItemId();
        if(id == R.id.main_search_icon){
            return true;
        }
        else if(id == R.id.main_cart_icon){
            Intent cartIntent = new Intent(ProductDetailsActivity.this,MainActivity.class);
            showCart = true;
            startActivity(cartIntent);
            return true;
        }
        else if(id == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



















