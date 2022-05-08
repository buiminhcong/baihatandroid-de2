package com.bmcong2k.luyentapsql2.model;

import java.io.Serializable;

public class BaiHat implements Serializable {

    private int id;
    private String tenBH, tenCasi, album, theLoai;

    public BaiHat() {
    }

    public BaiHat(int id, String tenBH, String tenCasi, String album, String theLoai) {
        this.id = id;
        this.tenBH = tenBH;
        this.tenCasi = tenCasi;
        this.album = album;
        this.theLoai = theLoai;
    }

    public BaiHat(String tenBH, String tenCasi, String album, String theLoai) {
        this.tenBH = tenBH;
        this.tenCasi = tenCasi;
        this.album = album;
        this.theLoai = theLoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBH() {
        return tenBH;
    }

    public void setTenBH(String tenBH) {
        this.tenBH = tenBH;
    }

    public String getTenCasi() {
        return tenCasi;
    }

    public void setTenCasi(String tenCasi) {
        this.tenCasi = tenCasi;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }
}
