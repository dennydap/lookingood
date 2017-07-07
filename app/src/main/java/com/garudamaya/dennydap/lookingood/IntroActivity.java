package com.garudamaya.dennydap.lookingood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro {

    // Mendeklarasikan objeck prefManager untuk menentukan...
    // ...apakah aplikasi sudah dijalankan sebelumnya atau tidak
    PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Jika aplikasi sudah pernah dijalankan sebelumnya...
        // ...maka activity ini akan di skip...
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            // ...dan langsung pindah ke MainActivity
            Intent mainIntent = new Intent(IntroActivity.this,MainActivity.class);
            IntroActivity.this.startActivity(mainIntent);
            IntroActivity.this.finish();
        }

        // Membuat semua slide untuk tutorial
        // Dengan parameter newInstance secara berurutan
        // Judul, Deskripsi, Logo, dan warna Background.
        addSlide(AppIntroFragment.newInstance
                ("Selamat datang di LookinGood!",
                        "LookinGood merupakan aplikasi yang menyediakan berbagai jenis informasi mengenai pencarian tempat Skin Care.",
                        R.drawable.logo,
                        getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance
                ("List",
                        "Anda dapat melihat informasi tempat Skin Care dalam bentuk list, dan dapat melihat secara rinci dengan memilih tempat yang ada.",
                        R.drawable.card,
                        getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance
                ("Peta",
                        "Anda juga dapat menggunakan mode peta yang disediakan untuk melihat tempat-tempat Skin Care di sekitar anda.",
                        R.drawable.map,
                        getResources().getColor(R.color.colorPrimary)));
        addSlide(AppIntroFragment.newInstance
                ("Selesai",
                        "Anda sudah siap untuk menjalankan aplikasi. Terima kasih telah menggunakan aplikasi LookingGood!",
                        R.drawable.thumbs,
                        getResources().getColor(R.color.colorPrimary)));

        // Memberi teks untuk Skip dan Done
        setSkipText("Lewati");
        setDoneText("Lanjut");
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Jika tombol skip ditekan...
        // ...pindah activity ke MainActivity
        // ...dan set supaya activity ini tidak muncul lagi
        Intent mainIntent = new Intent(IntroActivity.this,MainActivity.class);
        IntroActivity.this.startActivity(mainIntent);
        IntroActivity.this.finish();
        prefManager.setFirstTimeLaunch(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        // Jika tombol done ditekan...
        // ...pindah activity ke MainActivity
        // ...dan set supaya activity ini tidak muncul lagi
        super.onDonePressed(currentFragment);
        Intent mainIntent = new Intent(IntroActivity.this,MainActivity.class);
        IntroActivity.this.startActivity(mainIntent);
        IntroActivity.this.finish();
        prefManager.setFirstTimeLaunch(false);
    }

}
