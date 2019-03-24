package com.example.shoppingcart;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Products extends AppCompatActivity implements Serializable {
    String productId,category,mainCategory,suppliername,weightUnit,description;
    double weightMeasure,price,width,depth,height;
    int quantity;
    String name,picUrl,uom,currencyCode,dimUnit,status;

    int c=0;
    int r = 0;
    ListView listView1;

    public Products() {

    }

    public Products(String productId, String category, String mainCategory, String suppliername, String weightUnit, String description,
                    String name, String status, int quantity , String uom, String currencyCode, String dimUnit, String picUrl, double weightMeasure, double price, double width
            , double depth, double height) {
        this.productId = productId;
        this.category = category;
        this.mainCategory = mainCategory;
        this.suppliername = suppliername;
        this.weightUnit = weightUnit;
        this.quantity = quantity;
        this.description = description;
        this.name = name;
        this.status = status;
        this.uom = uom;
        this.currencyCode = currencyCode;
        this.dimUnit = dimUnit;
        this.picUrl = picUrl;
        this.weightMeasure = weightMeasure;
        this.price = price;
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

    public double getprice() {
        return price;
    }

    public double getdepth() {
        return depth;
    }

    public double getheight() {
        return height;
    }

    public double getweightMeasure() {
        return weightMeasure;
    }

    public double getwidth() {
        return width;
    }

    public int getquantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getcurrencyCode() {
        return currencyCode;
    }

    public String getdescription() {
        return description;
    }

    public String getmainCategory() {
        return mainCategory;
    }

    public String getname() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public String getpicUrl() {
        return picUrl;
    }
    //
    public String getsuppliername() {
        return suppliername;
    }

    public String getdimUnit() {
        return dimUnit;
    }

    public String getstatus() {
        return status;
    }

    public String getuom() {
        return uom;
    }
    //
    public String getweightUnit() {
        return weightUnit;
    }
    public  String toString() {
        return  this.name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_data);
        Intent intent = getIntent();
        final Products productData = (Products) intent.getSerializableExtra("Product");

        intent.putExtra("data", productData);
        System.out.println(productData);


        TextView pid = (TextView) findViewById(R.id.pid);
        pid.setText(productData.getProductId());

        TextView category = (TextView) findViewById(R.id.category);
        category.setText(productData.getCategory());

        TextView maincategory = (TextView) findViewById(R.id.mcategory);
        maincategory.setText(productData.getmainCategory());


        TextView name = (TextView) findViewById(R.id.name);
        name.setText(productData.getname());

        TextView sname = (TextView) findViewById(R.id.sname);
        sname.setText(productData.getsuppliername());

        TextView wunit = (TextView) findViewById(R.id.wunit);
        wunit.setText(productData.getweightUnit());

        TextView des = (TextView) findViewById(R.id.descrition);
        des.setText(productData.getdescription());

        ImageView pic = (ImageView) findViewById(R.id.productpic);
        String current = "http://msitmp.herokuapp.com" + productData.getpicUrl();
        new DownloadImage(pic).execute(current);


        TextView status = (TextView) findViewById(R.id.status);
        status.setText(productData.getstatus());

        TextView qty = (TextView) findViewById(R.id.quantity);
        qty.setText(productData.getquantity() + " ");

        TextView uom = (TextView) findViewById(R.id.uom);
        uom.setText(productData.getuom());

        TextView currencymode = (TextView) findViewById(R.id.currencycode);
        currencymode.setText(productData.getcurrencyCode());

        TextView price = (TextView) findViewById(R.id.price);
        price.setText(productData.getprice() + " ");

        TextView width = (TextView) findViewById(R.id.width);
        width.setText(productData.getwidth() + " ");

        TextView height = (TextView) findViewById(R.id.height);
        height.setText(productData.getheight() + " ");

        TextView depth = (TextView) findViewById(R.id.depth);
        depth.setText(productData.getdepth() + " ");

        TextView dim = (TextView) findViewById(R.id.dimUnit);
        dim.setText(productData.getdimUnit() + " ");

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c < productData.getquantity()) {
                    c++;
                    Toast.makeText(Products.this, "Added  " + productData.getname() + "\n count" + c, Toast.LENGTH_SHORT).show();
                }
                if (c == productData.getquantity()) {
                    Toast.makeText(Products.this, "Exceeded count  " + productData.getname() + "\n count" + c, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}

class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;
    public DownloadImage(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }
    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}