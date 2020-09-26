package com.google.luckydrawerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.luckydrawerapp.Fragments.FragmentAbout;
import com.google.luckydrawerapp.Fragments.FragmentContact;
import com.google.luckydrawerapp.Fragments.FragmentHome;
import com.google.luckydrawerapp.Fragments.FragmentLuckyDraw;
import com.google.luckydrawerapp.Fragments.FragmentLuckyDrawItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.b_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        dl = (DrawerLayout) findViewById(R.id.drawer_layout);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open_navigation_drawer, R.string.Close_navigation_drawer);
        nv = (NavigationView) findViewById(R.id.nav_view);

        dl.addDrawerListener(t);
        t.syncState();

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Fragment selectedFragment = null;

                switch (id) {
                    case R.id.home:
                        selectedFragment = new FragmentHome();
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.lucky_drawut:
                        selectedFragment = new FragmentLuckyDraw();
                        Toast.makeText(MainActivity.this, "Lucky Draw", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.about:
                        selectedFragment = new FragmentAbout();
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.all_lucky_draw_item:
                        selectedFragment = new FragmentLuckyDrawItem();
                        Toast.makeText(MainActivity.this, "All Aucky Draw", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact:
                        selectedFragment = new FragmentContact();
                        Toast.makeText(MainActivity.this, "Contact", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                dl.closeDrawers();
                return true;
            }
        });

        FragmentHome frag_name = new FragmentHome();
        FragmentManager manager = this.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, frag_name, frag_name.getTag()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.b_home:
                            //showInterstitial(MainActivity.this);
                            selectedFragment = new FragmentHome();
                            break;
                        case R.id.b_about:
                            selectedFragment = new FragmentAbout();
                            break;
                        case R.id.b_lucky_draw:
                            selectedFragment = new FragmentLuckyDraw();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (this.dl.isDrawerOpen(GravityCompat.START)) {
            this.dl.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}