package com.garudamaya.dennydap.lookingood;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class IntentPass {

    public IntentPass(Activity activity, DetailsModel model) {
        // Deklarasi Intent untuk pindah ke DetailsActivity
        Intent mainIntent = new Intent(activity,DetailsActivity.class);

        // Membungkus semua informasi dari database dalam bentuk Bundle
        Bundle bundle = new Bundle();
        bundle.putString("Judul", model.getNama());
        bundle.putString("Alamat", model.getAlamat());
        bundle.putInt("Harga", model.getHarga());
        bundle.putString("Jam", model.getJam());
        bundle.putString("NomorTelepon", model.getNomor_telepon());
        bundle.putDouble("Latitude", model.getLatitude());
        bundle.putDouble("Longitude", model.getLongitude());
        bundle.putString("Gambar0", model.getGambar0());
        bundle.putString("Gambar1", model.getGambar1());
        bundle.putString("Gambar2", model.getGambar2());

        // Memasukkan bundle ke dalam Intent...
        // ...lalu menjalankan Intent
        mainIntent.putExtras(bundle);
        activity.startActivity(mainIntent);
    }

}
