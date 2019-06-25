package com.example.perpustakaanapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AnggotaModel implements Parcelable {
    private String id_anggota;
    private String nama_anggota;
    private String no_telepon;

    public AnggotaModel() {
    }

    public AnggotaModel(String id_anggota, String nama_anggota, String no_telepon) {
        this.id_anggota = id_anggota;
        this.nama_anggota = nama_anggota;
        this.no_telepon = no_telepon;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama_anggota() {
        return nama_anggota;
    }

    public void setNama_anggota(String nama_anggota) {
        this.nama_anggota = nama_anggota;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id_anggota);
        dest.writeString(this.nama_anggota);
        dest.writeString(this.no_telepon);
    }

    protected AnggotaModel(Parcel in) {
        this.id_anggota = in.readString();
        this.nama_anggota = in.readString();
        this.no_telepon = in.readString();
    }

    public static final Parcelable.Creator<AnggotaModel> CREATOR = new Parcelable.Creator<AnggotaModel>() {
        @Override
        public AnggotaModel createFromParcel(Parcel source) {
            return new AnggotaModel(source);
        }

        @Override
        public AnggotaModel[] newArray(int size) {
            return new AnggotaModel[size];
        }
    };
}
