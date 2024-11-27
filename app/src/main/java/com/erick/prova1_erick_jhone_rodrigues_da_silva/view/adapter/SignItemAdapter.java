package com.erick.prova1_erick_jhone_rodrigues_da_silva.view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.erick.prova1_erick_jhone_rodrigues_da_silva.R;
import com.erick.prova1_erick_jhone_rodrigues_da_silva.model.Sign;

import java.util.ArrayList;

public class SignItemAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Sign> items;

    public SignItemAdapter(Context context, ArrayList<Sign> items) {
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Sign getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Sign item = items.get(position);
        convertView = inflater.inflate(R.layout.item_list, null);
        TextView text = convertView.findViewById(R.id.textViewItemList);
        text.setText(item.getName());
        ImageView image = convertView.findViewById(R.id.imageItemList);
        image.setImageResource(item.getImage());
        return convertView;
    }
}
