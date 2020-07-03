package com.example.parcours_mobile_9_fragments.fragments_act;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parcours_mobile_9_fragments.R;
import com.example.parcours_mobile_9_fragments.adapters.GameAdapter;
import com.example.parcours_mobile_9_fragments.model.Game;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class GameDetailActivity extends AppCompatActivity {

    private TextView game_name,game_desc,game_rating,game_released,game_reviews;
    private ImageView game_pic;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        game_desc = findViewById(R.id.game_desc);
        game_name = findViewById(R.id.game_name);
        game_rating = findViewById(R.id.game_rate);
        game_released = findViewById(R.id.game_released);
        game_reviews = findViewById(R.id.game_reviews);
        game_pic = findViewById(R.id.game_pic);

        setGameDesc();
    }

    private void setGameDesc() {
        id = getIntent().getStringExtra("game_id");
        String url = "https://rawg-video-games-database.p.rapidapi.com/games/"+id;
        Ion.with(this)
                .load(url)
                .setHeader("x-rapidapi-host","rawg-video-games-database.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                            Toast.makeText(GameDetailActivity.this, "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
                            String desc = result.get("description_raw").getAsString();
                            game_desc.setText(desc);

                            String name = result.get("name").getAsString();
                            game_name.setText(name);

                            String rate = result.get("rating").getAsString();
                            game_rating.setText(rate);

                            String reviews = result.get("ratings_count").getAsString();
                            game_reviews.setText("Reviews count : "+reviews);

                            String released = result.get("released").getAsString();
                            game_released.setText("Released : "+released);

                            Ion.with(game_pic).load(result.get("background_image").getAsString());


                        }


                    }
                });

    }
}

