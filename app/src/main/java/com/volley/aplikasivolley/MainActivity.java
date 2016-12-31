package com.volley.aplikasivolley;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.volley.aplikasivolley.database.BeritaDAO;
import com.volley.aplikasivolley.model.Kategori;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements KategoryAdapter.MyListItemClick {

    private static final String URL = "http://192.168.56.1/ebook/api.php";
    List<Kategori> modelKategoris = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        KategoryAdapter adapter = new KategoryAdapter(modelKategoris, MainActivity.this,MainActivity.this);
        recyclerView.setAdapter(adapter);

//        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("response", "onResponse: "+response.toString());
                try {
                    JSONObject parentObjek = new JSONObject(response);
                    JSONArray parentArray = parentObjek.getJSONArray("newsberita");

                    for (int i = 0; i < parentArray.length(); i++){
                        //    "cid": "6",
//            "category_name": "Pengampunan Pajak",
//            "category_image": "4052-2016-12-30.jpg",
//            "author": "Sasmito",
//            "status": "1"
                        JSONObject finalObjek = parentArray.getJSONObject(i);

                        Kategori kategori = new Kategori();
                        kategori.setkId(finalObjek.getString("cid"));
                        kategori.setkName(finalObjek.getString("category_name"));
                        kategori.setkImage(finalObjek.getString("category_image"));
                        kategori.setStatus(finalObjek.getString("status"));
                        kategori.setAuthor(finalObjek.getString("author"));

//                        String kategori_name = finalObjek.getString("category_name");
//                        Log.i("response", "onResponse: "+kategori_name);

                        modelKategoris.add(kategori);
                    }

                    BeritaDAO.getsInstance().simpanKategori(MainActivity.this, modelKategoris);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("response", "onResponse: "+error.toString());

                modelKategoris = BeritaDAO.getsInstance().getPostKategoriFromDb(MainActivity.this);
                KategoryAdapter adapter = new KategoryAdapter(modelKategoris, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });

        KoneksiManager.getInstance(this).add(stringRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemClick(Kategori itemClick) {
        //Buka Web View with the link the post
        Toast.makeText(MainActivity.this, "item Click "+itemClick.getkName(), Toast.LENGTH_SHORT).show();
    }
}
