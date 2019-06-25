package com.example.perpustakaanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.perpustakaanapp.fragment.AboutFragment;
import com.example.perpustakaanapp.fragment.AnggotaFragment;
import com.example.perpustakaanapp.fragment.BukuFragment;
import com.example.perpustakaanapp.fragment.PeminjamanFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private int currFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currFragment == 0) {
                    Intent intent = new Intent(MainActivity.this, AddBukuActivity.class);
                    startActivity(intent);
                } else if(currFragment == 1){
                    Intent intent = new Intent(MainActivity.this, AddAnggotaActivity.class);
                    startActivity(intent);
                } else if (currFragment == 2) {
                    Intent intent = new Intent(MainActivity.this, PeminjamanActivity.class);
                    startActivity(intent);
                }
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        loadFragment(new BukuFragment());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment;
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (id == R.id.nav_buku) {
            if (!(currentFragment instanceof BukuFragment)) {
                fragment = new BukuFragment();
                loadFragment(fragment);
                currFragment = 0;
                fab.show();
            }
        } else if (id == R.id.nav_anggota) {
            if (!(currentFragment instanceof AnggotaFragment)) {
                fragment = new AnggotaFragment();
                loadFragment(fragment);
                currFragment = 1;
                fab.show();
            }
        } else if (id == R.id.nav_peminjaman) {
            if (!(currentFragment instanceof PeminjamanFragment)) {
                fragment = new PeminjamanFragment();
                loadFragment(fragment);
                currFragment = 2;
                fab.show();
            }
        } else if (id == R.id.nav_pengembalian) {

        } else if (id == R.id.nav_about) {
            if (!(currentFragment instanceof AboutFragment)) {
                fragment = new AboutFragment();
                loadFragment(fragment);
                currFragment = 4;
                fab.hide();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}
