package com.example.perpustakaanapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perpustakaanapp.model.BukuModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailBukuActivity extends AppCompatActivity {
    private ImageView iv_buku;
    private TextView tv_kode_buku, tv_judul_buku, tv_pengarang, tv_penerbit, tv_tahun_terbit, tv_desc;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference("buku");

    private BukuModel bukuModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_buku);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Buku");

        iv_buku = findViewById(R.id.iv_buku);
        tv_kode_buku = findViewById(R.id.tv_kode_buku);
        tv_judul_buku = findViewById(R.id.tv_judul_buku);
        tv_pengarang = findViewById(R.id.tv_pengarang);
        tv_penerbit = findViewById(R.id.tv_penerbit);
        tv_tahun_terbit = findViewById(R.id.tv_tahun_terbit);
        tv_desc = findViewById(R.id.tv_desc);

        Intent dataIntent = getIntent();
        bukuModel = dataIntent.getParcelableExtra("data_buku");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_kode_buku.setText(bukuModel.getKode_buku());
        tv_judul_buku.setText(bukuModel.getJudul_buku());
        tv_pengarang.setText(bukuModel.getPengarang());
        tv_penerbit.setText(bukuModel.getPenerbit());
        tv_tahun_terbit.setText(String.valueOf(bukuModel.getTahun()));
        tv_desc.setText(bukuModel.getDesc());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_delete:
                dbRef.child(bukuModel.getKode_buku()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Buku berhasil dihapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            case R.id.menu_edit:
                Intent intent = new Intent(DetailBukuActivity.this, AddBukuActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("data_buku", bukuModel);
                startActivityForResult(intent, 22);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 22 && resultCode == RESULT_OK) {
            assert data != null;
            bukuModel = data.getParcelableExtra("result");
        }
    }
}
