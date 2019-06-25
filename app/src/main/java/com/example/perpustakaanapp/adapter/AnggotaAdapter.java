package com.example.perpustakaanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.perpustakaanapp.DetailAnggotaActivity;
import com.example.perpustakaanapp.R;
import com.example.perpustakaanapp.model.AnggotaModel;

import java.util.ArrayList;
import java.util.List;

public class AnggotaAdapter extends RecyclerView.Adapter<AnggotaAdapter.ViewHolder> {
    private Context context;
    private List<AnggotaModel> list_anggota;

    public AnggotaAdapter(Context context) {
        this.context = context;
        list_anggota = new ArrayList<>();
    }

    public void setList_anggota(List<AnggotaModel> list_anggota) {
        this.list_anggota = list_anggota;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.list_item_anggota, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final AnggotaModel anggotaModel = list_anggota.get(i);
        viewHolder.tv_id_anggota.setText(anggotaModel.getId_anggota());
        viewHolder.tv_nama_anggota.setText(anggotaModel.getNama_anggota());
        viewHolder.cv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailAnggotaActivity.class);
                intent.putExtra("data_anggota", anggotaModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_anggota.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_item;
        private TextView tv_id_anggota, tv_nama_anggota;
        private ImageView iv_anggota;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_item = itemView.findViewById(R.id.cv_item);
            tv_id_anggota = itemView.findViewById(R.id.tv_id_anggota);
            tv_nama_anggota = itemView.findViewById(R.id.tv_nama_anggota);
            iv_anggota = itemView.findViewById(R.id.iv_anggota);
        }
    }
}
