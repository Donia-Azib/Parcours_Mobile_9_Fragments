package com.example.parcours_mobile_9_fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.parcours_mobile_9_fragments.R;
import com.example.parcours_mobile_9_fragments.model.Game;
import com.example.parcours_mobile_9_fragments.model.Hotel;
import com.koushikdutta.ion.Ion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private TextView hotel_name, hotel_phone;


    public HotelAdapter(@NonNull Context context, @NonNull Hotel[] objects) {
        super(context, R.layout.hotel_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView  = LayoutInflater.from(getContext()).inflate(R.layout.hotel_item,parent,false);

         hotel_name = convertView.findViewById(R.id.hotel_name);
         hotel_phone = convertView.findViewById(R.id.hotel_add);

//         {},{},{}
         Hotel item = getItem(position);
         hotel_name.setText(item.getName()+" | "+item.getCountry());
         hotel_phone.setText(item.getPhone());



        return convertView;
    }
}
