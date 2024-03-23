package com.example.examensupletorio.Modelo;

import java.util.List;

public class Pais {
        private String Name;
        private Capital Capital;
        private GeoPosicion GeoRectangle;
        private int SeqID;
        private List<Double> GeoPt;
        private String TelPref;
        private Codigo CountryCodes;
        private String CountryInfo;

    public Pais(String name, Capital capital, GeoPosicion geoRectangle, int seqID, List<Double> geoPt, String telPref, Codigo countryCodes, String countryInfo) {
        Name = name;
        Capital = capital;
        GeoRectangle = geoRectangle;
        SeqID = seqID;
        GeoPt = geoPt;
        TelPref = telPref;
        CountryCodes = countryCodes;
        CountryInfo = countryInfo;
    }

    public Pais(String name, String flagUrl) {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Capital getCapital() {
        return Capital;
    }

    public void setCapital(Capital capital) {
        Capital = capital;
    }

    public GeoPosicion getGeoRectangle() {
        return GeoRectangle;
    }

    public void setGeoRectangle(GeoPosicion geoRectangle) {
        GeoRectangle = geoRectangle;
    }

    public int getSeqID() {
        return SeqID;
    }

    public void setSeqID(int seqID) {
        SeqID = seqID;
    }

    public List<Double> getGeoPt() {
        return GeoPt;
    }

    public void setGeoPt(List<Double> geoPt) {
        GeoPt = geoPt;
    }

    public String getTelPref() {
        return TelPref;
    }

    public void setTelPref(String telPref) {
        TelPref = telPref;
    }

    public Codigo getCountryCodes() {
        return CountryCodes;
    }

    public void setCountryCodes(Codigo countryCodes) {
        CountryCodes = countryCodes;
    }

    public String getCountryInfo() {
        return CountryInfo;
    }

    public void setCountryInfo(String countryInfo) {
        CountryInfo = countryInfo;
    }
}

