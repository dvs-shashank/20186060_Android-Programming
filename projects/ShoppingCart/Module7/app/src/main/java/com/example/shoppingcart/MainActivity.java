package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String JSON_URL = "http://msitmp.herokuapp.com/getproducts/20186092";
    //listview object
    ListView listView;

    int sum = 0;
    //the product list where we will store all the product objects after parsing json
    List<Products> productList;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing listview and product list
        listView = (ListView) findViewById(R.id.listView);
        productList = new ArrayList<>();

        loadproductList();
        ListView lv = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Products.class);
                intent.putExtra("Product", (Serializable) productList.get(i));
                startActivity(intent);
            }
            });
    }

    private void loadproductList() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray productArray = obj.getJSONArray("ProductCollection");
                            for (int i = 0; i < productArray.length(); i++) {
                                JSONObject productObject = productArray.getJSONObject(i);
                                Products product = new Products(productObject.getString("ProductId"), productObject.getString("Category"), productObject.getString("MainCategory")
                                        , productObject.getString("SupplierName"), productObject.getString("WeightUnit"),productObject.getString("Description")
                                        , productObject.getString("Name"), productObject.getString("Status"), Integer.parseInt(productObject.getString("Quantity")),productObject.getString("UoM")
                                        , productObject.getString("CurrencyCode"),productObject.getString("DimUnit")
                                        ,productObject.getString("ProductPicUrl")
                                        ,Double.parseDouble(productObject.getString("WeightMeasure")),Double.parseDouble(productObject.getString("Price"))
                                        ,Double.parseDouble(productObject.getString("Width")),Double.parseDouble(productObject.getString("Depth"))
                                        ,Double.parseDouble(productObject.getString("Height")));
                                productList.add(product);
                            }
                            ListViewAdapter adapter = new ListViewAdapter(productList,getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Check your net connection", Toast.LENGTH_SHORT).show();
//                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
