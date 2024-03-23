package com.example.examensupletorio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.examensupletorio.Adaptador.PaisAdapter;
import com.example.examensupletorio.Modelo.Pais;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Pais> listaPaises;
    TextView txtError1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        //txtError1 = findViewById(R.id.txtError);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // Para un layout en grid
        recyclerView.setLayoutManager(layoutManager);

        obtenerPaises();
    }

    private void obtenerPaises() {
        String url = "http://www.geognos.com/api/en/countries/info/all.json";

        RequestQueue queue = Volley.newRequestQueue(this);
        /*Map<String, String> listpaises = new HashMap<>();
        WebService wslistaPaises = new WebService("http://www.geognos.com/api/en/countries/info/all.json",
            listpaises, MainActivity.this, MainActivity.this);

        wslistaPaises.execute("GET");*/

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    // Parsear la respuesta y cargar el RecyclerView
                    parsearJsonPaises(response);
                }, error -> {
                    // Manejar el error
                    //txtError1.setText();
                    Toast.makeText(MainActivity.this, "Error al obtener los datos"+ error.toString(), Toast.LENGTH_LONG).show();
                });

        queue.add(jsonObjectRequest);
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
                //txtError1.setText(name +" "+ flagUrl);
            }

            // Crear el adaptador y asociarlo al RecyclerView
            PaisAdapter adaptador = new PaisAdapter(this, listaPaises);
            //txtError1.setText(listaPaises.get(0).getName());
            recyclerView.setAdapter(adaptador);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al procesar los datos JSON", Toast.LENGTH_SHORT).show();
        }
    }

}
