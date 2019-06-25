package com.example.perpustakaanapp.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.perpustakaanapp.R;
import com.example.perpustakaanapp.adapter.BukuAdapter;
import com.example.perpustakaanapp.model.BukuModel;
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
public class BukuFragment extends Fragment {
    private RecyclerView rvlist;
    private BukuAdapter adapter;
    private List<BukuModel> listbuku = new ArrayList<>();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();;
    private DatabaseReference dbRef = db.getReference("buku");

    public BukuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_buku, container, false);
        rvlist = v.findViewById(R.id.rv_list);
        adapter = new BukuAdapter(getContext());
        rvlist.setLayoutManager(new LinearLayoutManager(getContext()));
        rvlist.setAdapter(adapter);

        getBuku();
        return v;
    }

    private void getBuku() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listbuku.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    BukuModel menuModel = ds.getValue(BukuModel.class);
                    listbuku.add(menuModel);
                }
                adapter.setList_buku(listbuku);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
