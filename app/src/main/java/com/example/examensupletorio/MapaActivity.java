package com.example.examensupletorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examensupletorio.Modelo.GeoPosicion;
import com.example.examensupletorio.Modelo.Pais;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


import org.w3c.dom.Text;

import java.util.List;

public class MapaActivity extends AppCompatActivity implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener{
    GoogleMap mapai;
    Pais pais;
    public static int REQUEST_CAMERA = 111;
    public static int REQUEST_GALLERY = 222;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        Intent intent = getIntent();

        if (intent != null && intent.hasExtra("pais")) {
            pais = (Pais) intent.getSerializableExtra("pais");

            TextView textView = findViewById(R.id.txtresults);
            textView.setText("Nombre del país: " + pais.getName() +"\n" +
                    "Capital: " + pais.getCapital().getName() + "\n" +
                    "ISO 2 Code: " + pais.getCountryCodes().getIso2() + "\n" +
                    "ISO 3 Code: " + pais.getCountryCodes().getIso3() + "\n" +
                    "FIPS Code: " + pais.getCountryCodes().getIso3() + "\n" +
                    "Tel Prefix: " + pais.getTelPref());
            ImageView imageView = findViewById(R.id.image_view);

            Glide.with(this)
                    .load(pais.getCountryInfo())
                    .into(imageView);
            SupportMapFragment mapFragment = (SupportMapFragment)
                    getSupportFragmentManager()
                            .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

        } else {
            Toast.makeText(this, "No se recibió el objeto Pais", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapai = googleMap;
        mapai.getUiSettings().setZoomControlsEnabled(true);
        mapear();
    }
    public void mapear(){
        mapai.clear();
        GeoPosicion geo= (GeoPosicion) pais.getGeoRectangle();
        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(geo.getSouth(), geo.getEast()))
                .add(new LatLng(geo.getNorth(), geo.getEast()))
                .add(new LatLng(geo.getNorth(), geo.getWest()))
                .add(new LatLng(geo.getSouth(), geo.getWest()))
                .add(new LatLng(geo.getSouth(), geo.getEast()));
        lineas.width(8);
        lineas.color(Color.BLUE);
        mapai.addPolyline(lineas);
        List<Double> centro=pais.getGeoPt();
        LatLng ubicacion = new LatLng(centro.get(0), centro.get(1));
        CameraUpdate camUpd1 = CameraUpdateFactory.newLatLngZoom(ubicacion, 4);
        mapai.moveCamera(camUpd1);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }
}