package com.example.mymall;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBqueries {

    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<CategoryModel> categoryModelList = new ArrayList<>();

    // dekh abhi yaha pr list of list banaya h, jitni bhi categories h ,
    // unn sabki list ko hum yaha pr dalenge
    public static List<List<HomePageModel>> lists = new ArrayList<>();

    // ye list me jo bhi upar ki list hogi unka naam yaha store karenge
    // fir ye naam ka index kya h
    // then wo index ka use krke , wo particular catefory ki list apan ko milegi
    public static List<String> loadedCategoriesNames = new ArrayList<>();

    public static List<String> wishList = new ArrayList<>();
    public static List<WishlistModel> wishlistModelList = new ArrayList<>();


    public static void loadCategories(final RecyclerView categoryRecyclerView, final Context context){

//        categoryModelList.clear();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                // jitne documents ya data h utne time ye loop run hoga........
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModelList);
                            categoryRecyclerView.setAdapter(categoryAdapter);
                            categoryAdapter.notifyDataSetChanged();
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public static void loadFragmentData(final RecyclerView homePageRecyclerView, final Context context, final int index, String categoryName){

        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName.toUpperCase())
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
                                    lists.get(index).add(new HomePageModel(0,sliderModelList));
                                }

                                else if((long) documentSnapshot.get("view_type") == 1){
                                    lists.get(index).add(new HomePageModel(1,documentSnapshot.get("strip_ad_banner").toString(),
                                            documentSnapshot.get("background").toString()));
                                }
                                else if((long) documentSnapshot.get("view_type") == 2){

                                    List<WishlistModel> viewAllProductList  = new ArrayList<>();
                                    List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");

                                    for(long x = 1;x < no_of_products +1; x++){

                                        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));

///////////////////////////////////////////// ye jo list h wo jab horizontal wale images ke section me upar right View All Btn
                                        //// pr click kiya to ek recycler view show hona chahiye products ka
                                        //   uske liye h

                                        viewAllProductList.add(new WishlistModel(documentSnapshot.get("product_ID_"+x).toString(),documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_full_title_"+x).toString(),
                                                (long)documentSnapshot.get("free_coupens_"+x),
                                                documentSnapshot.get("average_rating_"+x).toString(),
                                                (long)documentSnapshot.get("total_ratings_"+x),
                                                documentSnapshot.get("product_price_"+x).toString(),
                                                documentSnapshot.get("cutted_price_"+x).toString(),
                                                (boolean)documentSnapshot.get("COD_"+x)));

                                    }
                                    lists.get(index).add(new HomePageModel(2,documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_background").toString(),horizontalProductScrollModelList,viewAllProductList));
                                }
                                else if((long) documentSnapshot.get("view_type") == 3){
                                    List<HorizontalProductScrollModel> GridLayoutModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");

                                    for(long x = 1;x < no_of_products +1; x++){

                                        GridLayoutModelList.add(new HorizontalProductScrollModel(documentSnapshot.get("product_ID_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));
                                    }
                                    lists.get(index).add(new HomePageModel(3,documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_background").toString(),GridLayoutModelList));
                                }

                            }
                            HomePageAdapter homePageAdapter = new HomePageAdapter(lists.get(index));
                            homePageRecyclerView.setAdapter(homePageAdapter);
                            homePageAdapter.notifyDataSetChanged();
                            HomeFragment.swipeRefreshLayout.setRefreshing(false);
                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public static void loadWishList(final Context context, final Dialog dialog, final boolean loadProductData){

//        wishList.clear();
        firebaseFirestore.collection("USERS")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA")
                .document("MY_WISHLIST")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){

                    for(long x=0;x<(long)task.getResult().get("list_size");x++){
                        wishList.add(task.getResult().get("product_ID_"+x).toString());

                        if(DBqueries.wishList.contains(ProductDetailsActivity.productID)){
                            ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST = true;
                            if (ProductDetailsActivity.addToWishListBtn != null) {
                                ProductDetailsActivity.addToWishListBtn.setSupportImageTintList(context.getResources().getColorStateList(R.color.colorPrimary));
                            }
                        }
                        else{
                            if (ProductDetailsActivity.addToWishListBtn != null) {
                                ProductDetailsActivity.addToWishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                            }
                            ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST = false;
                        }

                        if(loadProductData) {

                            wishlistModelList.clear();
                            final String productId = task.getResult().get("product_ID_" + x).toString();
                            firebaseFirestore.collection("PRODUCTS").document(productId)
                                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {

                                        wishlistModelList.add(new WishlistModel(productId,task.getResult().get("product_image_1").toString(),
                                                task.getResult().get("product_title").toString(),
                                                (long) task.getResult().get("free_coupens"),
                                                task.getResult().get("average_rating").toString(),
                                                (long) task.getResult().get("total_ratings"),
                                                task.getResult().get("product_price").toString(),
                                                task.getResult().get("cutted_price").toString(),
                                                (boolean) task.getResult().get("COD")));

                                        MyWishlistFragment.wishlistAdapter.notifyDataSetChanged();

                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
    }

    public static void removeFromWishlist(final int index, final Context context){

        wishList.remove(index);

        Map<String,Object> updateWishlist = new HashMap<>();

        for(int x=0; x<wishList.size();x++){
            updateWishlist.put("product_ID_"+x,wishList.get(x));
        }
        updateWishlist.put("list_size",(long) wishList.size());

        firebaseFirestore.collection("USERS")
                .document(FirebaseAuth.getInstance().getUid())
                .collection("USER_DATA")
                .document("MY_WISHLIST")
                .set(updateWishlist).addOnCompleteListener(new OnCompleteListener<Void>() {// set krne se new document create hota h.......
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    if(wishlistModelList.size() !=0 ){
                        wishlistModelList.remove(index);
                        MyWishlistFragment.wishlistAdapter.notifyDataSetChanged();
                    }
                    ProductDetailsActivity.ALREADY_ADDED_TO_WISHLIST = false;
                    Toast.makeText(context, "removed successfully!!", Toast.LENGTH_SHORT).show();

                }
                else{
                    if (ProductDetailsActivity.addToWishListBtn != null) {
                        ProductDetailsActivity.addToWishListBtn.setSupportImageTintList(context.getResources().getColorStateList(R.color.colorPrimary));
                    }
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }
//                if (ProductDetailsActivity.addToWishListBtn != null) {
//                    ProductDetailsActivity.addToWishListBtn.setEnabled(true);
//                }

                ProductDetailsActivity.running_wishlist_query = false;
            }
        });

    }

    public static void clearData(){

        categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();
        wishList.clear();
        wishlistModelList.clear();
    }

}





























