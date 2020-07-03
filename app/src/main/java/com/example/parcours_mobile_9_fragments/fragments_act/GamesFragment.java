package com.example.parcours_mobile_9_fragments.fragments_act;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcours_mobile_9_fragments.R;
import com.example.parcours_mobile_9_fragments.adapters.GameAdapter;
import com.example.parcours_mobile_9_fragments.model.Game;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends Fragment {

    private ListView listView;


    public GamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_games, container, false);

        listView = view.findViewById(R.id.game_list);

        setListGameData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Game game_item = (Game) parent.getItemAtPosition(position);
                String game_id = game_item.getId();
                Intent i = new Intent(getActivity(),GameDetailActivity.class);
                i.putExtra("game_id",game_id);
                startActivity(i);
            }
        });


        return view;
    }

    private void setListGameData() {
        String url = "https://rawg-video-games-database.p.rapidapi.com/games";
        Ion.with(getActivity())
                .load(url)
                .setHeader("x-rapidapi-host","rawg-video-games-database.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(e!=null)
                            Toast.makeText(getActivity(), "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
//                            [ {game_1},{game_2},{game_3},........] Game
                            JsonArray result_array = result.get("results").getAsJsonArray();
//                           [game_1],[game_2],[game_3],..... //GAME
//                            [
// {
//           { "id": 3498,
//            "name": "Grand Theft Auto V",
//            "background_image": "https://media.rawg.io/media/games/b11/b115b2bc6a5957a917bc7601f4abdda2.jpg",
//            "rating": 4.48,
//            "reviews_count": 3812,}
//              {
//                            "id": 3498,
////            "name": "Grand Theft Auto V",
////            "background_image": "https://media.rawg.io/media/games/b11/b115b2bc6a5957a917bc7601f4abdda2.jpg",
////            "rating": 4.48,
////            "reviews_count": 3812,}
//               ]
                            Gson gson = new Gson();
//                          {},{} //type Game
                            Game[] game_tab = gson.fromJson(result_array.toString(),Game[].class);

                            GameAdapter adapter = new GameAdapter(getActivity(),game_tab);
                            listView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }


                    }
                });

    }
}
