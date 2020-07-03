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
import com.example.parcours_mobile_9_fragments.model.Sport;
import com.koushikdutta.ion.Ion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SportsAdapter extends ArrayAdapter<Sport> {
    private TextView sport_name, sport_date;
    private ImageView sport_pic;

    public SportsAdapter(@NonNull Context context, @NonNull Sport[] objects) {
        super(context, R.layout.sport_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView  = LayoutInflater.from(getContext()).inflate(R.layout.sport_item,parent,false);

         sport_name = convertView.findViewById(R.id.sport_name);
         sport_date = convertView.findViewById(R.id.sport_date);
         sport_pic = convertView.findViewById(R.id.sport_image);

//         {},{},{}
         Sport sport_item = getItem(position);
         sport_name.setText(sport_item.getTitle());
         sport_date.setText("Date : "+sport_item.getDate());

        Ion.with(sport_pic).load(sport_item.getThumbnail());


        return convertView;
    }
}
