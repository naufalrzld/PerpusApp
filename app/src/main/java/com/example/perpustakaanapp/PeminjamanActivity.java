package com.example.perpustakaanapp;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.perpustakaanapp.model.PeminjamanModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PeminjamanActivity extends AppCompatActivity {
    private TextInputEditText et_kode_peminjaman, et_kode_buku, et_id_anggota, et_tanggal_pinjam, et_tanggal_kembali;
    private TextInputLayout til_kode_peminjaman, til_kode_buku, til_id_anggota, til_tanggal_pinjam, til_tanggal_kembali;
    private Button btnPinjam;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("peminjaman");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Form Peminjaman");

        et_kode_peminjaman = findViewById(R.id.et_kode_peminjaman);
        et_kode_buku = findViewById(R.id.et_kode_buku);
        et_id_anggota = findViewById(R.id.et_id_anggota);
        et_tanggal_pinjam = findViewById(R.id.et_tanggal_pinjam);
        et_tanggal_kembali = findViewById(R.id.et_tanggal_kembali);
        btnPinjam = findViewById(R.id.btn_pinjam);

        til_kode_peminjaman = findViewById(R.id.til_kode_peminjaman);
        til_kode_buku = findViewById(R.id.til_kode_buku);
        til_id_anggota = findViewById(R.id.til_id_anggota);
        til_tanggal_pinjam = findViewById(R.id.til_tanggal_pinjam);
        til_tanggal_kembali = findViewById(R.id.til_tanggal_kembali);

        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        et_tanggal_pinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(PeminjamanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + month + "/" + year;
                        et_tanggal_pinjam.setText(date);
                    }
                }, year, month, dayOfMonth);
                dialog.show();
            }
        });

        et_tanggal_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(PeminjamanActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth + "/" + month + "/" + year;
                        et_tanggal_kembali.setText(date);
                    }
                }, year, month, dayOfMonth);
                dialog.show();
            }
        });
    }

    private boolean is_input_valid(){
        til_kode_peminjaman.setErrorEnabled(false);
        til_kode_buku.setErrorEnabled(false);
        til_id_anggota.setErrorEnabled(false);
        til_tanggal_pinjam.setErrorEnabled(false);
        til_tanggal_kembali.setErrorEnabled(false);

        if(TextUtils.isEmpty(et_kode_peminjaman.getText())||TextUtils.isEmpty(et_kode_buku.getText())||TextUtils.isEmpty(et_id_anggota.getText())||TextUtils.isEmpty(et_tanggal_pinjam.getText())||TextUtils.isEmpty(et_tanggal_kembali.getText())){
            if(TextUtils.isEmpty(et_kode_peminjaman.getText())){
                til_kode_peminjaman.setErrorEnabled(true);
                til_kode_peminjaman.setError("Kode Buku harus diisi!");
            }

            if(TextUtils.isEmpty(et_kode_buku.getText())){
                til_kode_buku.setErrorEnabled(true);
                til_kode_buku.setError("Kode Buku harus diisi!");
            }

            if(TextUtils.isEmpty(et_id_anggota.getText())){
                til_id_anggota.setErrorEnabled(true);
                til_id_anggota.setError("ID Anggota harus diisi!");
            }

            if(TextUtils.isEmpty(et_tanggal_pinjam.getText())){
                til_tanggal_pinjam.setErrorEnabled(true);
                til_tanggal_pinjam.setError("Tanggal pinjam harus diisi!");
            }

            if(TextUtils.isEmpty(et_tanggal_kembali.getText())){
                til_tanggal_kembali.setErrorEnabled(true);
                til_tanggal_kembali.setError("Tanggal kembali harus diisi!");
            }

            return false;
        }

        return true;
    }

    private void save(){
        if (is_input_valid()){
            String kode_peminjaman = et_kode_peminjaman.getText().toString();
            String kode_buku = et_kode_buku.getText().toString();
            String id_anggota = et_id_anggota.getText().toString();
            String tanggal_pinjam = et_tanggal_pinjam.getText().toString();
            String tanggal_kembali = et_tanggal_kembali.getText().toString();

            final PeminjamanModel peminjamanModel = new PeminjamanModel(kode_peminjaman, kode_buku, id_anggota, tanggal_pinjam, tanggal_kembali);

            myRef.child(kode_peminjaman).setValue(peminjamanModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
