package com.example.perpustakaanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.perpustakaanapp.model.BukuModel;
import com.example.perpustakaanapp.DetailBukuActivity;
import com.example.perpustakaanapp.R;

import java.util.ArrayList;
import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolder> {
    private Context context;
    private List<BukuModel> list_buku;

    public BukuAdapter(Context context) {
        this.context = context;
        list_buku = new ArrayList<>();
    }

    public void setList_buku(List<BukuModel> list_buku) {
        this.list_buku = list_buku;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item_buku, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final BukuModel bukuModel = list_buku.get(i);
        viewHolder.tv_judul_buku.setText(bukuModel.getJudul_buku());
        viewHolder.tv_pengarang.setText(bukuModel.getPengarang());
        viewHolder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBukuActivity.class);
                intent.putExtra("data_buku", bukuModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_buku.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_item;
        private TextView tv_judul_buku, tv_pengarang;
        private ImageView iv_buku;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv_item = itemView.findViewById(R.id.cv_item);
            tv_judul_buku = itemView.findViewById(R.id.tv_judul_buku);
            tv_pengarang = itemView.findViewById(R.id.tv_pengarang);
            iv_buku = itemView.findViewById(R.id.iv_buku);
        }
    }
}
