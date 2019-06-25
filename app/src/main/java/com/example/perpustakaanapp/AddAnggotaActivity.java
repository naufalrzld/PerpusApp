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

import com.example.perpustakaanapp.model.AnggotaModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddAnggotaActivity extends AppCompatActivity {
    private TextInputEditText et_id_anggota, et_nama_anggota, et_no_telp;
    private TextInputLayout til_id_anggota, til_nama_anggota, til_no_telp;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("anggota");

    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anggota);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Anggota");

        et_id_anggota = findViewById(R.id.et_id_anggota);
        et_nama_anggota = findViewById(R.id.et_nama_anggota);
        et_no_telp = findViewById(R.id.et_no_telp);

        til_id_anggota = findViewById(R.id.til_id_anggota);
        til_nama_anggota = findViewById(R.id.til_nama_anggota);
        til_no_telp = findViewById(R.id.til_no_telp);

        Intent dataIntent = getIntent();
        AnggotaModel anggotaModel = dataIntent.getParcelableExtra("data_anggota");
        isEdit = dataIntent.getBooleanExtra("isEdit",false);

        if (isEdit) {
            et_id_anggota.setText(anggotaModel.getId_anggota());
            et_nama_anggota.setText(anggotaModel.getNama_anggota());
            et_no_telp.setText(anggotaModel.getNo_telepon());
        }
    }

    private boolean is_input_valid(){
        til_id_anggota.setErrorEnabled(false);
        til_nama_anggota.setErrorEnabled(false);
        til_no_telp.setErrorEnabled(false);

        if(TextUtils.isEmpty(et_id_anggota.getText())||TextUtils.isEmpty(et_nama_anggota.getText())||TextUtils.isEmpty(et_no_telp.getText())){
            if(TextUtils.isEmpty(et_id_anggota.getText())){
                til_id_anggota.setErrorEnabled(true);
                til_id_anggota.setError("ID Anggota harus diisi!");
            }

            if(TextUtils.isEmpty(et_nama_anggota.getText())){
                til_nama_anggota.setErrorEnabled(true);
                til_nama_anggota.setError("JNama Anggota harus diisi!");
            }

            if(TextUtils.isEmpty(et_no_telp.getText())){
                til_no_telp.setErrorEnabled(true);
                til_no_telp.setError("Nomor telepon harus diisi!");
            }

            return false;
        }

        return true;
    }

    private void save(){
        if (is_input_valid()){
            String id_anggota = et_id_anggota.getText().toString();
            String nama_anggota = et_nama_anggota.getText().toString();
            String no_tlp = et_no_telp.getText().toString();

            final AnggotaModel anggotaModel = new AnggotaModel(id_anggota, nama_anggota, no_tlp);

            if (isEdit) {
                Map<String, Object> map = new HashMap<>();
                map.put("/"+ id_anggota, anggotaModel);
                myRef.updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("result", anggotaModel);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            } else {
                myRef.child(id_anggota).setValue(anggotaModel).addOnSuccessListener(new OnSuccessListener<Void>() {
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
