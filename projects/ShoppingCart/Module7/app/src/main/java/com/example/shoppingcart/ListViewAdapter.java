package com.example.shoppingcart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
public class ListViewAdapter extends ArrayAdapter<Products> {
    private List<Products> productList;
    private Context mCtx;

    //here we are getting the productList and context
    //so while creating the object of this adapter class we need to give productList and context
    public ListViewAdapter(List<Products> productList, Context mCtx) {
        super(mCtx, R.layout.list_items, productList);
        this.productList = productList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.pid);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.name);

        //Getting the prod for the specified position
        Products prod = productList.get(position);

        //setting prod values to textviews
        textViewName.setText(prod.getProductId());
        textViewImageUrl.setText(prod.getCategory());

        //returning the listitem
        return listViewItem;
    }
}