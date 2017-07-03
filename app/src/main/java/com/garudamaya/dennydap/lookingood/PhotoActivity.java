package com.garudamaya.dennydap.lookingood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Mengambil informasi dari Intent
        Bundle extras = getIntent().getExtras();

        // Memberi title pada action bar sesuai dengan tag Judul
        setTitle(extras.getString("Judul"));

        // Mendeklarasikan photoView
        // Dan memberi foto pada photoView dengan tag Gambar
        PhotoView photoView = (PhotoView) findViewById(R.id.photoView);
        Picasso.with(getApplicationContext())
                .load(extras.getString("Gambar"))
                .into(photoView);
    }
}
