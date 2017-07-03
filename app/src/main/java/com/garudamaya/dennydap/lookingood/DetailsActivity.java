package com.garudamaya.dennydap.lookingood;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import static android.provider.CalendarContract.CalendarCache.URI;

public class DetailsActivity extends AppCompatActivity {

    TextView placeTitle;
    TextView placeAddress;
    TextView placePrice;
    TextView placeHours;
    Double latitude_;
    Double longitude_;
    ImageView gambar0, gambar1, gambar2, gambar3;

    String nomor_telepon;
    String namaTempat;
    String alamatTempat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Deklarasi komponen-komponen pada layout
        FloatingActionButton fabShare = (FloatingActionButton) findViewById(R.id.shareButton);
        FloatingActionButton fabCall = (FloatingActionButton) findViewById(R.id.callButton);
        FloatingActionButton fabNav = (FloatingActionButton) findViewById(R.id.navigationButton);
        placeTitle = (TextView) findViewById(R.id.placeTitle);
        placeAddress = (TextView) findViewById(R.id.placeAddress);
        placePrice = (TextView) findViewById(R.id.placePrice);
        placeHours = (TextView) findViewById(R.id.placeHours);
        gambar0 = (ImageView) findViewById(R.id.placePicture0);
        gambar1 = (ImageView) findViewById(R.id.placePicture1);
        gambar2 = (ImageView) findViewById(R.id.placePicture2);
        gambar3 = (ImageView) findViewById(R.id.placePicture3);


        // Mengambil data dari Intent
        final Bundle bundle = getIntent().getExtras();
        latitude_= bundle.getDouble("Latitude");
        longitude_ = bundle.getDouble("Longitude");
        nomor_telepon = bundle.getString("NomorTelepon");
        namaTempat = bundle.getString("Judul");
        alamatTempat = bundle.getString("Alamat");


        // Memberi text dari Intent ke komponen yang bersangkutan
        setTitle(namaTempat);
        placeTitle.setText(namaTempat);
        placeAddress.setText(alamatTempat);
        placePrice.setText("Harga mulai dari Rp. " + bundle.getString("Harga"));
        placeHours.setText("Buka jam " + bundle.getString("Jam"));

        // Memberi gambar ke ImageView
        Picasso.with(getApplicationContext())
                .load(bundle.getString("Gambar0"))
                .into(gambar0);
        Picasso.with(getApplicationContext())
                .load(bundle.getString("Gambar0"))
                .into(gambar1);
        Picasso.with(getApplicationContext())
                .load(bundle.getString("Gambar1"))
                .into(gambar2);
        Picasso.with(getApplicationContext())
                .load(bundle.getString("Gambar2"))
                .into(gambar3);

        //Memberi aksi ketika gambar diklik
        gambar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, PhotoActivity.class);
                i.putExtra("Gambar", bundle.getString("Gambar0"));
                i.putExtra("Judul", bundle.getString("Judul"));
                startActivity(i);
            }
        });
        gambar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, PhotoActivity.class);
                i.putExtra("Gambar", bundle.getString("Gambar1"));
                i.putExtra("Judul", bundle.getString("Judul"));
                startActivity(i);
            }
        });
        gambar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, PhotoActivity.class);
                i.putExtra("Gambar", bundle.getString("Gambar2"));
                i.putExtra("Judul", bundle.getString("Judul"));
                startActivity(i);
            }
        });

        // Menambahkan aksi untuk tombol Share
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                String shareSubject = String.valueOf(placeTitle.getText());
                String shareBody = String.valueOf(placeAddress.getText());
                i.putExtra(Intent.EXTRA_SUBJECT, "LookinGood - " + shareSubject);
                i.putExtra(Intent.EXTRA_TEXT, shareSubject + "\n" + shareBody + "\n\n" +
                        "http://maps.google.com/?q=" + alamatTempat.toLowerCase().replaceAll(" ", "+"));
                startActivity(Intent.createChooser(i, "Share via "));

            }
        });

        // Menambahkan aksi untuk tombol Telpon
        fabCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memanggil Intent ACTION_DIAL
                // Untuk menampilkan aplikasi Dialer
                // Dengan nomor telpon yang didapat dari variabel nomor_telepon
                Intent callIntent = new Intent(Intent.ACTION_DIAL, URI.fromParts("tel", nomor_telepon, null));
                startActivity(callIntent);
            }
        });

        // Menambahkan aksi untuk tombol Navigasi
        fabNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menggunakan Intent ACTION_VIEW
                // Untuk membuka aplikasi 
                Uri gmmIntentUri = Uri.parse("google.navigation:q="
                        + alamatTempat.toLowerCase().replaceAll(" ", "+"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                startActivity(mapIntent);
            }
        });

    }
}
