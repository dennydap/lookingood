package com.garudamaya.dennydap.lookingood;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ListFragment extends Fragment {

    // Mendeklarasikan variabel untuk keperluan Firebase
    Query sort;
    FirebaseListAdapter<DetailsModel> mAdapter;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("places");

    // Mendeklarasikan variabel untuk sortir
    String modeSortir = "nama";

    // Variable komponen
    ListView mListView;
    ProgressBar progressBar;

    public ListFragment() {
        // Membutuhkan constructor kosong
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Menginflate layout fragment_list
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        // Mendeklarasikan variabel untuk komponen layout
        mListView = (ListView) v.findViewById(R.id.listView);
        progressBar = (ProgressBar) v.findViewById(R.id.listProgress);

        // Membuat FirebaseListAdapter untuk Listview
        sort = myRef.orderByChild("nama");
        setUpListAdapter();
        // Meberi adapter mAdapter untuk ListView
        mListView.setAdapter(mAdapter);

        // Mendeklarasikan tombol untuk sortir
        // Ketika tombol dalam mode...
        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (modeSortir) {
                    // ...nama, maka...
                    case "nama":
                        // ...sortir berdasarkan harga
                        sort = myRef.orderByChild("harga");
                        setUpListAdapter();
                        // Meberi adapter mAdapter untuk ListView
                        mListView.setAdapter(mAdapter);
                        fab.setImageResource(R.drawable.ic_attach_money_white_24px);
                        modeSortir = "harga";
                        break;
                    // ...harga, maka...
                    case "harga":
                        // ...sortir berdasarkan nama
                        sort = myRef.orderByChild("nama");
                        setUpListAdapter();
                        // Meberi adapter mAdapter untuk ListView
                        mListView.setAdapter(mAdapter);
                        fab.setImageResource(R.drawable.ic_sort_by_alpha_white_24px);
                        modeSortir = "nama";
                        break;
                }
            }
        });
        return v;
    }

    public void setUpListAdapter() {
        // Membuat FirebaseListAdapter untuk Listview
        mAdapter = new FirebaseListAdapter<DetailsModel>(getActivity(),
                DetailsModel.class, R.layout.list_item, sort) {
            @Override
            protected void populateView(View v, final DetailsModel model, int position) {
                // Memberi komponen-komponen list_item nilai
                ((TextView) v.findViewById(R.id.itemText)).setText(model.getNama());
                ((TextView) v.findViewById(R.id.itemAlamat)).setText(model.getAlamat());
                ((TextView) v.findViewById(R.id.itemHarga)).setText(
                        "Harga mulai dari Rp. " + model.getHarga());
                Picasso.with(getContext())
                        .load(model.getGambar0())
                        .into((ImageView) v.findViewById(R.id.itemGambar));
                progressBar.setVisibility(View.GONE);
                // Jika item diclick...
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // ...informasi dilempar ke activity DetailsActivity
                        new IntentPass(getActivity(), model);
                    }
                });
            }
        };
    }
}
