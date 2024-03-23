package com.example.examensupletorio.Modelo;

import java.io.Serializable;

public class GeoPosicion implements Serializable {

    private double West;
    private double East;
    private double North;
    private double South;



    public GeoPosicion(double west, double east, double north, double south) {
        West = west;
        East = east;
        North = north;
        South = south;
    }

    public double getWest() {
        return West;
    }

    public void setWest(double west) {
        West = west;
    }

    public double getEast() {
        return East;
    }

    public void setEast(double east) {
        East = east;
    }

    public double getNorth() {
        return North;
    }

    public void setNorth(double north) {
        North = north;
    }

    public double getSouth() {
        return South;
    }

    public void setSouth(double south) {
        South = south;
    }
}