package com.bmcong2k.luyentapsql2.model;

import java.io.Serializable;

public class Sort implements Serializable {
    private int dem;
    private String tL;

    public Sort() {
    }

    public Sort(int dem, String tL) {
        this.dem = dem;
        this.tL = tL;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }

    public String gettL() {
        return tL;
    }

    public void settL(String tL) {
        this.tL = tL;
    }
}
