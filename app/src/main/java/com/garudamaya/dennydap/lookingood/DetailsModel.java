package com.garudamaya.dennydap.lookingood;

public class DetailsModel{

    // Deklarasi variable untuk model
    private double latitude;
    private double longitude;
    private String nama;
    private String kategori;
    private String alamat;
    private Integer harga;
    private String jam;
    private String nomor_telepon;
    private String gambar0, gambar1, gambar2;

    // Dibutuhkan constructor kosong
    public DetailsModel() {
        return;
    }

    // Constructor yang dibutuhkan untuk membuat objek Model
    public DetailsModel(double latitude, double longitude, String nama, String kategori,
                        String alamat, Integer harga, String jam, String nomor_telepon,
                        String gambar0, String gambar1, String gambar2) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nama = nama;
        this.kategori = kategori;
        this.alamat = alamat;
        this.harga = harga;
        this.jam = jam;
        this.nomor_telepon = nomor_telepon;
        this.gambar0 = gambar0;
        this.gambar1 = gambar1;
        this.gambar2 = gambar2;
    }

    // Method getter dari semua variabel
    // Tidak diperlukan method setter karena aplikasi tidak mempunyai hak write
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public String getNama() {
        return nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public Integer getHarga() {
        return harga;
    }
    public String getJam() {
        return jam;
    }
    public String getNomor_telepon() {
        return nomor_telepon;
    }
    public String getGambar0() { return gambar0; }
    public String getGambar1() { return gambar1; }
    public String getGambar2() { return gambar2; }
}