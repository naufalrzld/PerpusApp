package com.example.perpustakaanapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.perpustakaanapp.model.BukuModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddBukuActivity extends AppCompatActivity {
    private TextInputEditText et_kode_buku, et_judul_buku, et_pengarang, et_penerbit, et_tahun, et_desc;
    private TextInputLayout til_kode_buku, til_judul_buku, til_pengarang, til_penerbit, til_tahun, til_desc;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("buku");

    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buku);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Buku");

        et_kode_buku = findViewById(R.id.et_kode_buku);
        et_judul_buku = findViewById(R.id.et_judul_buku);
        et_pengarang = findViewById(R.id.et_pengarang);
        et_penerbit = findViewById(R.id.et_penerbit);
        et_tahun = findViewById(R.id.et_tahun);
        et_desc = findViewById(R.id.et_desc);

        til_kode_buku = findViewById(R.id.til_kode_buku);
        til_judul_buku = findViewById(R.id.til_judul_buku);
        til_pengarang = findViewById(R.id.til_pengarang);
        til_penerbit = findViewById(R.id.til_penerbit);
        til_tahun = findViewById(R.id.til_tahun);
        til_desc = findViewById(R.id.til_desc);

        Intent dataIntent = getIntent();
        BukuModel bukuModel = dataIntent.getParcelableExtra("data_buku");
        isEdit = dataIntent.getBooleanExtra("isEdit",false);

        if (isEdit) {
            et_kode_buku.setText(bukuModel.getKode_buku());
            et_judul_buku.setText(bukuModel.getJudul_buku());
            et_pengarang.setText(bukuModel.getPengarang());
            et_penerbit.setText(bukuModel.getPenerbit());
            et_tahun.setText(String.valueOf(bukuModel.getTahun()));
            et_desc.setText(bukuModel.getDesc());
        }
    }

    private boolean is_input_valid(){
        til_kode_buku.setErrorEnabled(false);
        til_judul_buku.setErrorEnabled(false);
        til_pengarang.setErrorEnabled(false);
        til_penerbit.setErrorEnabled(false);
        til_tahun.setErrorEnabled(false);
        til_desc.setErrorEnabled(false);

        if(TextUtils.isEmpty(et_kode_buku.getText())||TextUtils.isEmpty(et_judul_buku.getText())||TextUtils.isEmpty(et_pengarang.getText())||TextUtils.isEmpty(et_tahun.getText())||TextUtils.isEmpty(et_desc.getText())){
            if(TextUtils.isEmpty(et_kode_buku.getText())){
                til_kode_buku.setErrorEnabled(true);
                til_kode_buku.setError("Kode Buku harus diisi!");
            }

            if(TextUtils.isEmpty(et_judul_buku.getText())){
                til_judul_buku.setErrorEnabled(true);
                til_judul_buku.setError("Judul harus diisi!");
            }

            if(TextUtils.isEmpty(et_pengarang.getText())){
                til_pengarang.setErrorEnabled(true);
                til_pengarang.setError("Nama Pengarang harus diisi!");
            }

            if(TextUtils.isEmpty(et_penerbit.getText())){
                til_penerbit.setErrorEnabled(true);
                til_penerbit.setError("Nama Penerbit harus diisi!");
            }

            if(TextUtils.isEmpty(et_tahun.getText())){
                til_tahun.setErrorEnabled(true);
                til_tahun.setError("Tahun terbit harus diisi!");
            }

            if(TextUtils.isEmpty(et_desc.getText())){
                til_desc.setErrorEnabled(true);
                til_desc.setError("Deskripsi harus diisi!");
            }

            return false;
        }

        return true;
    }

    private void save(){
        if (is_input_valid()){
            String kode_buku = et_kode_buku.getText().toString();
            String judul_buku = et_judul_buku.getText().toString();
            String pengarang = et_pengarang.getText().toString();
            String penerbit = et_penerbit.getText().toString();
            String tahun = et_tahun.getText().toString();
            String desc = et_desc.getText().toString();

            final BukuModel bukuModel = new BukuModel(kode_buku, judul_buku, pengarang, penerbit, Integer.parseInt(tahun), desc);

            if (isEdit) {
                Map<String, Object> map = new HashMap<>();
                map.put("/"+kode_buku, bukuModel);
                myRef.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("result", bukuModel);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            } else {
                myRef.child(kode_buku).setValue(bukuModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_save){
            save();
        } else if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
