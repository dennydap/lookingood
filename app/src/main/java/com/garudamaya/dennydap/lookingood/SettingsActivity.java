package com.garudamaya.dennydap.lookingood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Memberi layout activity_settings
        setContentView(R.layout.activity_settings);
        // Mendeklarasikan komponen ListView
        ListView listView = (ListView) findViewById(R.id.settingsList);

        // Membuat ArrayAdapter yang bernama adapter
        // Untuk diberikan pada ListView
        // Menggunakan layout simple_list_item_1 dan array settings_list
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.settings_list));
        listView.setAdapter(adapter);

        // Jika item pada listView di click...
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    // ...pilihan pertama...
                    case 0:
                        // ...menampilkan data pembuat aplikasi
                        Toast.makeText(getApplicationContext(), "Dibuat oleh:", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Denny Aditya Pradipta", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Kelas 3IA01", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "NPM 52414708", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        // ...mengirimkan email ke email pembuat aplikasi
                        Intent send = new Intent(Intent.ACTION_SENDTO);
                        String uriText = "mailto:" + Uri.encode("denny.aditya.p@gmail.com");
                        Uri uri = Uri.parse(uriText);
                        send.setData(uri);
                        startActivity(Intent.createChooser(send, "Kirim email melalui..."));
                        break;
                }
            }
        });
    }
}
