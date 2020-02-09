package com.example.mymall;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.ImageView;


// top right side ke 3 items tune menu->main.xml me set kiya h (bell,notification,cart ke icons ).........

// nav_header_main.xml me tune place holder ka icon set kiya h, jab top left ke 3 dots wale icon ko click karega
// tab jo navigation bar khulta h uski baat ho rhi h yaha.........


// menu->activity_main_drawer.xml me jo left side ke navbar ke neeche dikhte h wo tune waha pr daale h,
// jaise amazon ke app jab left navbar kholte h to jo options dikhte h neeche wo waha pr h...........









public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;

    private NavigationView navigationView;
    private ImageView actionBarLogo;

    private FrameLayout frameLayout;
    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;

    private static int currentFragment=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
         navigationView.bringToFront();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = findViewById(R.id.main_framelayout);
        setFragment(new HomeFragment(),HOME_FRAGMENT);
    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            if(currentFragment == HOME_FRAGMENT) {
                super.onBackPressed();
            }else{
                actionBarLogo.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
                setFragment(new HomeFragment(),HOME_FRAGMENT);
                navigationView.getMenu().getItem(0).setChecked(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // ye if condition ye check krta h ki agar humara home page pr h user..
        // to sirf uss time usko upar ke icons dikhne chahiye,
        // nhi to baki sare pages me usko nhi dikhna chahiye
        if(currentFragment == HOME_FRAGMENT){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // ye h top right side ke 3 items ke liye, unme se koi ek bhi selecct hua to uska code yaha h......

        int id = item.getItemId();
        if(id == R.id.main_search_icon){

            return true;
        }
        else if(id == R.id.main_notification_icon){
            return true;
        }
        else if(id == R.id.main_cart_icon){
            gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(MenuItem item){

        int id = item.getItemId();
        item.setChecked(true);

        if(id == R.id.nav_my_mall){
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(),HOME_FRAGMENT);
        }
        else if(id == R.id.nav_my_orders){
            gotoFragment("My Orders",new MyOrdersFragment(),ORDERS_FRAGMENT);
        }
        else if(id == R.id.nav_my_rewards){

        }
        else if(id == R.id.nav_my_cart){
            gotoFragment("My Cart",new MyCartFragment(),CART_FRAGMENT);
        }
        else if(id == R.id.nav_my_wishlist){

            gotoFragment("My Wishlist",new MyWishlistFragment(),WISHLIST_FRAGMENT);
        }
        else if(id == R.id.nav_my_account){

        }
        else if(id == R.id.nav_sign_out){

        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo){
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        // isse kya hota h ki humara sare menu bar ke options hat jayenge...coz user ko abhi next page ke usme ye nhi dikhna chahiye
        // isse exactly onCreateOptionMenu wala function jo upar h wo fir se run hota and abhi humara waha if wala statement run hoga.....
        invalidateOptionsMenu();

        setFragment(fragment,fragmentNo);
        // delh abhi yaha pr humne neeche 3 kyu pass kiya na
        // coz jab app ke left me navbar open karega tab tujhe jo options dekhenge usme se 3 rd wala option my cart ka h
        // and user ko ye bhata rhe h apan ki agar usne 3rd wala option select kiya to ye sab hoga.....

        if(fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
        if(fragmentNo == WISHLIST_FRAGMENT) {
            navigationView.getMenu().getItem(4).setChecked(true);
        }


    }


    private void setFragment(Fragment fragment, int fragmentNo){

        if(fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}





















