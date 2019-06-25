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

import com.example.perpustakaanapp.model.AnggotaModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailAnggotaActivity extends AppCompatActivity {
    private ImageView iv_anggota;
    private TextView tv_id_anggota, tv_nama_anggota, tv_no_tlp;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference("anggota");

    private AnggotaModel anggotaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_anggota);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Anggota");

        iv_anggota = findViewById(R.id.iv_anggota);
        tv_id_anggota = findViewById(R.id.tv_id_anggota);
        tv_nama_anggota = findViewById(R.id.tv_nama_anggota);
        tv_no_tlp = findViewById(R.id.tv_no_tlp);

        Intent dataIntent = getIntent();
        anggotaModel = dataIntent.getParcelableExtra("data_anggota");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv_id_anggota.setText(anggotaModel.getId_anggota());
        tv_nama_anggota.setText(anggotaModel.getNama_anggota());
        tv_no_tlp.setText(anggotaModel.getNo_telepon());
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
                dbRef.child(anggotaModel.getId_anggota()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Anggota berhasil dihapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
            case R.id.menu_edit:
                Intent intent = new Intent(DetailAnggotaActivity.this, AddAnggotaActivity.class);
                intent.putExtra("isEdit", true);
                intent.putExtra("data_anggota", anggotaModel);
                startActivityForResult(intent, 22);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 22 && resultCode == RESULT_OK) {
            assert data != null;
            anggotaModel = data.getParcelableExtra("result");
        }
    }
}
