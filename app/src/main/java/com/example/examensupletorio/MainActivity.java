package com.example.examensupletorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examensupletorio.Adaptador.PaisAdapter;
import com.example.examensupletorio.Modelo.Capital;
import com.example.examensupletorio.Modelo.Codigo;
import com.example.examensupletorio.Modelo.GeoPosicion;
import com.example.examensupletorio.Modelo.Pais;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Pais> listaPaises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(layoutManager);

        obtenerPaises();
    }

    private void obtenerPaises(){
        String url = "http://www.geognos.com/api/en/countries/info/all.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            parsearJsonPaises(jsonResponse);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void parsearJsonPaises(JSONObject response) {
        listaPaises = new ArrayList<>();
        try {
            JSONObject results = response.getJSONObject("Results");
            Iterator<String> keys = results.keys();

            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject countryJson = results.getJSONObject(key);

                String name = countryJson.getString("Name");
                JSONObject capitalJson = countryJson.optJSONObject("Capital");
                Capital capital = null;
                if (capitalJson != null) {
                    capital = new Capital(
                            capitalJson.optString("DLST"),
                            capitalJson.optDouble("TD"),
                            capitalJson.optInt("Flg"),
                            capitalJson.getString("Name"),
                            Arrays.asList(
                                    capitalJson.getJSONArray("GeoPt").getDouble(0),
                                    capitalJson.getJSONArray("GeoPt").getDouble(1)
                            )
                    );
                }



                JSONObject geoRectangleJson = countryJson.getJSONObject("GeoRectangle");
                GeoPosicion geoPosicion = new GeoPosicion(
                        geoRectangleJson.getDouble("West"),
                        geoRectangleJson.getDouble("East"),
                        geoRectangleJson.getDouble("North"),
                        geoRectangleJson.getDouble("South"));

                int seqID = countryJson.getInt("SeqID");
                JSONArray geoPtArray = countryJson.getJSONArray("GeoPt");
                List<Double> geoPt = new ArrayList<>();
                for (int i = 0; i < geoPtArray.length(); i++) {
                    geoPt.add(geoPtArray.getDouble(i));
                }
                String telPref = countryJson.getString("TelPref");
                JSONObject countryCodesJson = countryJson.getJSONObject("CountryCodes");
                Codigo countryCodes = new Codigo(
                        countryCodesJson.getString("tld"),
                        countryCodesJson.getString("iso3"),
                        countryCodesJson.getString("iso2"),
                        countryCodesJson.getString("fips"),
                        countryCodesJson.getInt("isoN"));

                String countryInfo = countryJson.getString("CountryInfo");
                String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + countryCodes.getIso2() + ".png";
                Pais pais = new Pais(name, capital, geoPosicion, seqID, geoPt, telPref, countryCodes, flagUrl);
                listaPaises.add(pais);
            }

            PaisAdapter adaptador = new PaisAdapter(this, listaPaises);
            recyclerView.setAdapter(adaptador);

            final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
                @Override
                public void onRequestDisallowInterceptTouchEvent(boolean b) {
                }

                @Override
                public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                    try {
                        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                            int position = recyclerView.getChildAdapterPosition(child);

                            Pais pais = listaPaises.get(position);
                            Intent intent = new Intent(MainActivity.this, MapaActivity.class);
                            intent.putExtra("pais", pais);
                            startActivity(intent);

                            return true;
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    return false;
                }

                @Override
                public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
        }
    }
}