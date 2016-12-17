package com.deneme1.erel.kredikartbasvuru.model;

import java.util.ArrayList;

/**
 * Created by melike on 17/12/2016.
 */
public class Register {

    private String TCKN;
    private String adSoyad;
    private boolean cinsiyet;
    private ArrayList<String> tercih;

    public Register(String TCKN, String adSoyad, boolean cinsiyet, ArrayList<String> tercih) {
        this.adSoyad = adSoyad;
        this.cinsiyet = cinsiyet;
        this.TCKN = TCKN;
        this.tercih = tercih;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public boolean isCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(boolean cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTCKN() {
        return TCKN;
    }

    public void setTCKN(String TCKN) {
        this.TCKN = TCKN;
    }

    public ArrayList<String> getTercih() {
        return tercih;
    }

    public void setTercih(ArrayList<String> tercih) {
        this.tercih = tercih;
    }
}
