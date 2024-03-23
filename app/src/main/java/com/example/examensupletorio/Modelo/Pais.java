package com.example.examensupletorio.Modelo;

import java.util.ArrayList;
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

    // Constructor principal que inicializa todos los atributos.
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

    // Constructor sobrecargado para inicializar solo el nombre y la URL de la bandera.
    // Los demás atributos se inicializan con valores por defecto o nulos.
    public Pais(String name, String flagUrl) {
        this.Name = name;
        this.CountryInfo = flagUrl;
        // Inicialización de otros campos con valores por defecto
        this.Capital = null; // O un nuevo objeto Capital con valores por defecto si es necesario
        this.GeoRectangle = null; // O un nuevo objeto GeoPosicion con valores por defecto si es necesario
        this.SeqID = 0;
        this.GeoPt = new ArrayList<>(); // Lista vacía
        this.TelPref = "";
        this.CountryCodes = null; // O un nuevo objeto Codigo con valores por defecto si es necesario
    }

    // Getters y setters para cada atributo.
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
