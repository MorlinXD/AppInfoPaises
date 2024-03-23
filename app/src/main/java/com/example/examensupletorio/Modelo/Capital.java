package com.example.examensupletorio.Modelo;

import java.io.Serializable;
import java.util.List;

public class Capital implements Serializable {
    private String DLST;
    private double TD;
    private int Flg;
    private String Name;
    private List<Double> GeoPt;

    public Capital(String DLST, double TD, int flg, String name, List<Double> geoPt) {
        this.DLST = DLST;
        this.TD = TD;
        Flg = flg;
        Name = name;
        GeoPt = geoPt;
    }
    public Capital() {

    }

    public String getDLST() {
        return DLST;
    }

    public void setDLST(String DLST) {
        this.DLST = DLST;
    }

    public double getTD() {
        return TD;
    }

    public void setTD(double TD) {
        this.TD = TD;
    }

    public int getFlg() {
        return Flg;
    }

    public void setFlg(int flg) {
        Flg = flg;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Double> getGeoPt() {
        return GeoPt;
    }

    public void setGeoPt(List<Double> geoPt) {
        GeoPt = geoPt;
    }
}