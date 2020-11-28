package applecare.com.applecare.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import applecare.com.applecare.Fragment.CameraFragment;
import applecare.com.applecare.Fragment.FAQFragment;
import applecare.com.applecare.Fragment.HistoryFragment;
import applecare.com.applecare.Model.SignUpUser;
import applecare.com.applecare.R;
import applecare.com.applecare.Utils.Constants;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private TextView userNameTv;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    SharedPreferences userTypeSharedPreferences;
    public String MyPREFERENCES = "UserPrefs" ;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference("Users");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");


        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        setDrawerLayout();
        setUserName();





        fragmentManager = getSupportFragmentManager();
        //load initial fragment
        loadFragment();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder(); StrictMode.setVmPolicy(builder.build());

        userTypeSharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if(userTypeSharedPreferences.getString("type","farmer").equalsIgnoreCase(getResources().getString(R.string.farmer))){
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

//                        final SharedPreferences.Editor editor = userTypeSharedPreferences.edit();
//                        editor.putBoolean("login",false);
//                        editor.commit();
                        mAuth.signOut();
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

    private void setUserName() {
        View headerView = navigationView.getHeaderView(0);
        userNameTv = headerView.findViewById(R.id.user_name_drawer);
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Query query = userRef.orderByChild("email").equalTo(firebaseUser.getEmail());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data:snapshot.getChildren() ) {
                    Constants.currentUser = data.getValue(SignUpUser.class);
                }
                userNameTv.setText(Constants.currentUser.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
