package com.garudamaya.dennydap.lookingood;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Meminta request permission untuk menggunakan akses lokasi
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        // Menggunakan Handler().postDelayed untuk menjalankan...
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                // ...perpindahan Activity dari SplashActivity ke IntroActivity
                // dengan jeda 3000ms (3 detik)
                Intent mainIntent = new Intent(SplashActivity.this,IntroActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 3000);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        // Setelah request location dilakukan, jika...
        switch (requestCode) {
            // ...diberikan permission, maka lanjutkan program
            case 1: {
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED)
                        // ...tidak diberikan permission, matikan aplikasi
                        System.exit(0);
                    }
            }
        }
}
