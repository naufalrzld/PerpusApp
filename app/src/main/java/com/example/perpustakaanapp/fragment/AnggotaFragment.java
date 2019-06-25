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
import com.example.perpustakaanapp.adapter.AnggotaAdapter;
import com.example.perpustakaanapp.model.AnggotaModel;
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
public class AnggotaFragment extends Fragment {
    private RecyclerView rv_list;
    private AnggotaAdapter adapter;
    private List<AnggotaModel> list_anggota = new ArrayList<>();

    private FirebaseDatabase db = FirebaseDatabase.getInstance();;
    private DatabaseReference dbRef = db.getReference("anggota");

    public AnggotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_anggota, container, false);
        rv_list = v.findViewById(R.id.rv_list);
        adapter = new AnggotaAdapter(getContext());
        rv_list.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_list.setAdapter(adapter);

        getAnggota();
        return v;
    }

    private void getAnggota() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_anggota.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    AnggotaModel anggotaModel = ds.getValue(AnggotaModel.class);
                    list_anggota.add(anggotaModel);
                }
                adapter.setList_anggota(list_anggota);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
