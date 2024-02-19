package com.example.ncontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class custAdaptor extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<String> values;

    public custAdaptor(Context context, ArrayList<String> values) {
        super(context, R.layout.custada, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custada, parent, false);

        // Get references to your UI elements in list_item.xml
        TextView textView = rowView.findViewById(R.id.text);
        ImageView imageView = rowView.findViewById(R.id.image);

        // Set the text for the TextView
        textView.setText(values.get(position));

        // You can set the image based on your requirements
        // For example, setting a default image for each item
        imageView.setImageResource(R.drawable.img);

        return rowView;
    }
}
