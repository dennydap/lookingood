package com.garudamaya.dennydap.lookingood;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {

    // Deklarasi variabel penampung
    // Variabel-variabel berikut digunakan untuk menampung data dari Firebase
    Double latitude_, longitude_;
    String nama_tempat, kategori, alamat, nomor_telepon, harga, jam, gambar0, gambar1, gambar2;

    // Deklarasi variabel utama
    // GoogleMap mMap untuk keperluan Google Maps API
    // DatabaseReference myRef untuk referensi Database Firebase
    // HashMap detailMarkerMap untuk menyimpan data sebagai objek...
    // yang akan dimasukkan ke dalam satu marker
    GoogleMap mMap;
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    HashMap<Marker, DetailsModel> detailMarkerMap = new HashMap<>();

    public MapFragment() {
        // Dibutuhkan constructor kosong
    }

    @Override
    public void onResume() {
        // Jika aplikasi dilanjutkan
        super.onResume();
        // Setup peta jika dibutuhkan
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Jika peta tidak ada
        if (mMap == null) {
            // Buat peta secara asynchronous
            getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Setup peta
        mMap = googleMap;
        setUpMap(mMap);
    }

    private void setUpMap(GoogleMap googleMap) {

        // Meminta permission menggunakan GPS di device pengguna
        // Harus dicheck, karena menggunakan beberapa fitur seperti MyLocationEnabled
        // Jika tidak diberi izin maka peta tidak akan keluar
        if (ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Memberi style pada peta. Style didapat dari https://mapstyle.withgoogle.com/
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.style_json));
        // Memberi tanda lokasi pengguna
        mMap.setMyLocationEnabled(true);

        // Mengambil data dari Database
        // Database bersumber dari myRef
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Untuk setiap child di dalam child places...
                for(DataSnapshot child : dataSnapshot.child("places").getChildren())
                {
                    // Mengambil semua data yang ada pada child places
                    latitude_ = child.child("latitude").getValue(Double.class);
                    longitude_ = child.child("longitude").getValue(Double.class);
                    nama_tempat = child.child("nama").getValue(String.class);
                    alamat = child.child("alamat").getValue(String.class);
                    harga = child.child("harga").getValue(String.class);
                    nomor_telepon = child.child("nomor_telepon").getValue(String.class);
                    jam = child.child("jam").getValue(String.class);
                    gambar0 = child.child("gambar0").getValue(String.class);
                    gambar1 = child.child("gambar1").getValue(String.class);
                    gambar2 = child.child("gambar2").getValue(String.class);

                    // Memasukkan data-data ke dalam model DetailsModel
                    DetailsModel detail = new DetailsModel(latitude_, longitude_,nama_tempat,
                            kategori,alamat,harga, jam,nomor_telepon,gambar0, gambar1, gambar2);

                    // Mendefinisikan LatLng untuk penentuan posisi marker
                    // Berdasarkan variabel latitude_ dan longitude_
                    // Membuat marker pada peta pada posisi latlng, title nama_tempat...
                    // ...dan memberi icon derma_marker untuk markernya.
                    // Kemudian marker diletakkan pada peta
                    LatLng latlng = new LatLng(latitude_, longitude_);
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(latlng)
                            .title(nama_tempat)
                            .snippet("Klik Disini")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.derma_marker)));
                    detailMarkerMap.put(marker, detail);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // Menggunakan LocationManager untuk menentukan posisi kamera
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Criteria digunakan untuk mengambil Location Mode pada device pengguna
        // Yang akan digunakan untuk mengambil lokasi terakhir pengguna
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        // Jika lokasi terakhir pengguna tidak ada...
        if (location != null)
        {
            // Geser posisi kamera pada peta sesuai dengan posisi pengguna
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))
                    .zoom(17)
                    .build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        // Memberi aksi jika InfoWindow diclick
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // Mengambil data dari Model
                marker.getTitle();
                DetailsModel detail = detailMarkerMap.get(marker);
                // Memanggil class IntentPass
                new IntentPass(getActivity(), detail);
            }
        });
    }
}