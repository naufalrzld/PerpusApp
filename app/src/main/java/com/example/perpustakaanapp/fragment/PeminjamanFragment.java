package com.example.perpustakaanapp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.perpustakaanapp.R;
import com.example.perpustakaanapp.adapter.PeminjamanAdapter;
import com.example.perpustakaanapp.model.PeminjamanModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeminjamanFragment extends Fragment {
    private RecyclerView rv_list;
    private PeminjamanAdapter adapter;
    private List<PeminjamanModel> list_peminjaman = new ArrayList<>();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();;
    private DatabaseReference dbRef = db.getReference("peminjaman");

    public PeminjamanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_peminjaman, container, false);
        rv_list = v.findViewById(R.id.rv_list);
        adapter = new PeminjamanAdapter(getContext());
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setAdapter(adapter);

        getPeminjaman();
        return v;
    }

    private void getPeminjaman() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_peminjaman.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    PeminjamanModel peminjamanModel = ds.getValue(PeminjamanModel.class);
                    list_peminjaman.add(peminjamanModel);
                }
                adapter.setList_peminjaman(list_peminjaman);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
