package com.teamvariance.teamvariance1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.teamvariance.teamvariance1.Model.Items;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListViewAdapter listViewAdapter;
    ArrayList<Items> itemsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        itemsArrayList = new ArrayList<>();
        listViewAdapter = new ListViewAdapter(itemsArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listViewAdapter);
        makeJsonRequest();
    }

    private void makeJsonRequest() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ServerConfig.URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("--->", response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    Gson gson = new Gson();
                    itemsArrayList = new ArrayList<>();
                    for(int i =0; i<jsonArray.length(); i++){
                        itemsArrayList.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Items.class));
                    }
//                    listViewAdapter.notifyDataSetChanged();
                    listViewAdapter = new ListViewAdapter(itemsArrayList);
                    recyclerView.setAdapter(listViewAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error--->", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


}
