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
import com.koushikdutta.ion.Ion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GameAdapter extends ArrayAdapter<Game> {
    private TextView game_name,game_desc;
    private ImageView game_pic;

    public GameAdapter(@NonNull Context context, @NonNull Game[] objects) {
        super(context, R.layout.game_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView  = LayoutInflater.from(getContext()).inflate(R.layout.game_item,parent,false);

         game_name = convertView.findViewById(R.id.game_name);
         game_desc = convertView.findViewById(R.id.game_desc);
         game_pic = convertView.findViewById(R.id.game_image);

//         {},{},{}
         Game game_item = getItem(position);
         game_name.setText(game_item.getName());
         game_desc.setText("Rate : "+game_item.getRating()+" | "+" Reviews : "+game_item.getReviews_count());

        Ion.with(game_pic).load(game_item.getBackground_image());


        return convertView;
    }
}
