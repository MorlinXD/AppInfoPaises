package com.example.examensupletorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examensupletorio.Adaptador.PaisAdapter;
import com.example.examensupletorio.Modelo.Pais;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Pais> listaPaises;

    TextView txtError1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        txtError1 = findViewById(R.id.txtError1);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 7); // Para un layout en grid
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
                JSONObject countryCodes = countryJson.getJSONObject("CountryCodes");
                String iso2 = countryCodes.getString("iso2");
                String flagUrl = "http://www.geognos.com/api/en/countries/flag/" + iso2 + ".png";
                //txtError1.setText(flagUrl);

                Pais pais = new Pais(name, flagUrl);
                listaPaises.add(pais);
                txtError1.setText(name + key);
            }
            for (Pais pais : listaPaises) {
                Log.d("VerificarDatos", "Nombre: " + pais.getName() + ", URL de la Bandera: " + pais.getCountryInfo());
            }
            // Crear el adaptador y asociarlo al RecyclerView
            PaisAdapter adaptador = new PaisAdapter(this, listaPaises);
            txtError1.setText(listaPaises.get(0).getName());
            recyclerView.setAdapter(adaptador);



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
        }
    }
}




