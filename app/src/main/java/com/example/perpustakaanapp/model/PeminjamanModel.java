package com.example.perpustakaanapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PeminjamanModel implements Parcelable {
    private String kode_peminjaman;
    private String kode_buku;
    private String id_anggota;
    private String tanggal_pinjam;
    private String tanggal_kembali;

    public PeminjamanModel() {
    }

    public PeminjamanModel(String kode_peminjaman, String kode_buku, String id_anggota, String tanggal_pinjam, String tanggal_kembali) {
        this.kode_peminjaman = kode_peminjaman;
        this.kode_buku = kode_buku;
        this.id_anggota = id_anggota;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getKode_peminjaman() {
        return kode_peminjaman;
    }

    public void setKode_peminjaman(String kode_peminjaman) {
        this.kode_peminjaman = kode_peminjaman;
    }

    public String getKode_buku() {
        return kode_buku;
    }

    public void setKode_buku(String kode_buku) {
        this.kode_buku = kode_buku;
    }

    public String getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kode_peminjaman);
        dest.writeString(this.kode_buku);
        dest.writeString(this.id_anggota);
        dest.writeString(this.tanggal_pinjam);
        dest.writeString(this.tanggal_kembali);
    }

    protected PeminjamanModel(Parcel in) {
        this.kode_peminjaman = in.readString();
        this.kode_buku = in.readString();
        this.id_anggota = in.readString();
        this.tanggal_pinjam = in.readString();
        this.tanggal_kembali = in.readString();
    }

    public static final Creator<PeminjamanModel> CREATOR = new Creator<PeminjamanModel>() {
        @Override
        public PeminjamanModel createFromParcel(Parcel source) {
            return new PeminjamanModel(source);
        }

        @Override
        public PeminjamanModel[] newArray(int size) {
            return new PeminjamanModel[size];
        }
    };
}
