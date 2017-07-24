  package com.garudamaya.dennydap.lookingood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        // Membuat toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Membuat adapter untuk menampilkan fragment-fragment
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Mendeklarasikan ViewPager dengan adapter mSectionsPagerAdapter
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Mendeklarasikan TabLayout dengan menggunakan ViewPager mViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Memanggil MenuInflater dengan nama inflater
        // Untuk membuat menu dengan pilihan dari menu_tabbed
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Mengambil ID dari item menu yang dipilih
        int id = item.getItemId();

        // Jika ID sama dengan menu pilihan...
        if (id == R.id.action_settings) {
            // Pindah activity ke SettingsActivity
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        // SectionsPagerAdapter digunakan untuk menampung Fragment lain
        // di dalam konten Tab

        private SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Mengambil fragment sesuai dengan posisi tab
            // Jika posisi tab berada di posisi...
            switch (position) {
                // ...pertama, maka tampilkan fragment ListFragment
                case 0:
                    return new ListFragment();
                // ...kedua, maka tampilkan fragment MapFragment
                case 1:
                    return new MapFragment();
            }
            return null;

        }

        @Override
        public int getCount() {
            // Mengambil jumlah Tab
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Mengambil judul untuk tab
            // Jika posisi tab beradai di posisi...
            switch (position) {
                // ...pertama, maka tampilkan:
                case 0:
                    return "Daftar Tempat";
                // ...kedua, maka tampilkan:
                case 1:
                    return "Peta Lokasi";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        // Jika tombol back ditekan,
        // Muncul konfirmasi untuk keluar aplikasi atau tidak
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah anda ingin keluar dari aplikasi?")
                .setCancelable(false)
                .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setPositiveButton("Tidak", null)
                .show();
    }
}
