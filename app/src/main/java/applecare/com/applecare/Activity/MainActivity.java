package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import applecare.com.applecare.Fragment.CameraFragment;
import applecare.com.applecare.Fragment.FAQFragment;
import applecare.com.applecare.Fragment.HistoryFragment;
import applecare.com.applecare.R;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    SharedPreferences userTypeSharedPreferences;
    public String MyPREFERENCES = "UserPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setDrawerLayout();
        fragmentManager = getSupportFragmentManager();
        //load initial fragment
        loadFragment();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());

        userTypeSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(userTypeSharedPreferences.getString("type","").equalsIgnoreCase(getResources().getString(R.string.farmer))){
            bottomNavigation.inflateMenu(R.menu.menu_bottom_naigation_farmer);
        }else if(userTypeSharedPreferences.getString("type","").equalsIgnoreCase(getResources().getString(R.string.expert))) {
            bottomNavigation.inflateMenu(R.menu.menu_bottom_naigation_expert);
        }
        bottomNavigation.getMenu().findItem(R.id.action_faq).setChecked(true);
        //set the bottom navigation item click listener
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_faq:
                        fragment = new FAQFragment();
                        break;
                    case R.id.action_camera:
                        fragment = new CameraFragment();
                        break;
                    case R.id.action_history:
                        fragment = new HistoryFragment();
                        break;
                }
                transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.container, fragment).commit();
                return true;
            }
        });

        //set the navigation item click listener
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // Close the drawer
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "home clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "setting clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.trash:
                        Toast.makeText(MainActivity.this, "trash clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:

                        final SharedPreferences.Editor editor = userTypeSharedPreferences.edit();
                        editor.putBoolean("login",false);
                        editor.commit();
                        Intent loginIntent=new Intent(getBaseContext(),LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                        break;
                    case R.id.allmail:
                        Toast.makeText(MainActivity.this, "mail clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.spam:
                        Toast.makeText(MainActivity.this, "spam clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void loadFragment() {
        fragment = new FAQFragment();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment).commit();
    }


    private void setDrawerLayout() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        // Set the actionbarToggle to drawer layout.
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        // Calling sync state is necessary or else your hamburger icon wont show up.
        actionBarDrawerToggle.syncState();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
}
