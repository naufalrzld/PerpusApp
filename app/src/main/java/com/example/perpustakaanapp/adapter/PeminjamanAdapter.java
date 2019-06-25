package com.example.perpustakaanapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.perpustakaanapp.R;
import com.example.perpustakaanapp.model.PeminjamanModel;

import java.util.ArrayList;
import java.util.List;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanAdapter.ViewHolder> {
    private Context context;
    private List<PeminjamanModel> list_peminjaman;

    public PeminjamanAdapter(Context context) {
        this.context = context;
        list_peminjaman = new ArrayList<>();
    }

    public void setList_peminjaman(List<PeminjamanModel> list_peminjaman) {
        this.list_peminjaman = list_peminjaman;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_peminjaman, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        PeminjamanModel peminjamanModel = list_peminjaman.get(i);

        viewHolder.tv_kode_peminjaman.setText(peminjamanModel.getKode_peminjaman());
        viewHolder.tv_kode_buku.setText(peminjamanModel.getKode_buku());
        viewHolder.tv_id_anggota.setText(peminjamanModel.getId_anggota());
        viewHolder.tv_tanggal_pinjam.setText(peminjamanModel.getTanggal_pinjam());
        viewHolder.tv_tanggal_kembali.setText(peminjamanModel.getTanggal_kembali());
    }

    @Override
    public int getItemCount() {
        return list_peminjaman.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_item;
        private TextView tv_kode_peminjaman, tv_kode_buku, tv_id_anggota, tv_tanggal_pinjam, tv_tanggal_kembali;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_item = itemView.findViewById(R.id.cv_item);
            tv_kode_peminjaman = itemView.findViewById(R.id.tv_kode_peminjaman);
            tv_kode_buku = itemView.findViewById(R.id.tv_kode_buku);
            tv_id_anggota = itemView.findViewById(R.id.tv_id_anggota);
            tv_tanggal_pinjam = itemView.findViewById(R.id.tv_tanggal_pinjam);
            tv_tanggal_kembali = itemView.findViewById(R.id.tv_tanggal_kembali);
        }
    }
}
