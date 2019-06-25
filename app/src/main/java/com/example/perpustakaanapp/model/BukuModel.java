package com.example.perpustakaanapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BukuModel implements Parcelable {
    private String kode_buku;
    private String judul_buku;
    private String pengarang;
    private String penerbit;
    private int tahun;
    private String desc;

    public BukuModel() {
    }

    public BukuModel(String kode_buku, String judul_buku, String pengarang, String penerbit, int tahun, String desc) {
        this.kode_buku = kode_buku;
        this.judul_buku = judul_buku;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.desc = desc;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getJudul_buku() {
        return judul_buku;
    }

    public void setJudul_buku(String judul_buku) {
        this.judul_buku = judul_buku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode_buku);
        dest.writeString(this.judul_buku);
        dest.writeString(this.pengarang);
        dest.writeString(this.penerbit);
        dest.writeInt(this.tahun);
        dest.writeString(this.desc);
    }

    protected BukuModel(Parcel in) {
        this.kode_buku = in.readString();
        this.judul_buku = in.readString();
        this.pengarang = in.readString();
        this.penerbit = in.readString();
        this.tahun = in.readInt();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<BukuModel> CREATOR = new Parcelable.Creator<BukuModel>() {
        @Override
        public BukuModel createFromParcel(Parcel source) {
            return new BukuModel(source);
        }

        @Override
        public BukuModel[] newArray(int size) {
            return new BukuModel[size];
        }
    };
}