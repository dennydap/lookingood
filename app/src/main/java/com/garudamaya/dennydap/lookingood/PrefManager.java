package com.garudamaya.dennydap.lookingood;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    // Mendeklarasikan SharedPreferences untuk menampung boolean
    // yang digunakan untuk menentukan apakah aplikasi sudah pernah dijalankan atau tidak
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    // Mendeklarasikan nama SharedPreferences
    private static final String PREF_NAME = "intro";
    // Mendeklarasikan variabel penampung Boolean
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PrefManager(Context context) {
        // Constructor yang berguna untuk mengambil nilai dari PREF_NAME
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, 0);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        // Memberi nilai pada boolean isFirstTime
        // dengan nilai dari variabel IS_FIRST_TIME_LAUNCH
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        // Memberi nilai IS_FIRST_TIME_LAUNCH dengan nilai true
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}