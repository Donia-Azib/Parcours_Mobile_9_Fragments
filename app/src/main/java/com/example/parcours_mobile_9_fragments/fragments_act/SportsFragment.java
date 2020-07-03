package com.example.parcours_mobile_9_fragments.fragments_act;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parcours_mobile_9_fragments.R;
import com.example.parcours_mobile_9_fragments.adapters.SportsAdapter;
import com.example.parcours_mobile_9_fragments.model.Sport;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportsFragment extends Fragment {

    private ListView listView;
    public SportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sports, container, false);


        listView = view.findViewById(R.id.sports_list);

        setSportListData();


        return view;
    }

    private void setSportListData() {
        String url ="https://free-football-soccer-videos1.p.rapidapi.com/v1/";
        Ion.with(getActivity())
                .load(url)
                .setHeader("x-rapidapi-host","free-football-soccer-videos1.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e != null)
                            Toast.makeText(getActivity(), "Exception "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        else
                        {
//                            {
//                            key : value,....
//                            results:[
//                            {game_1},
//                            {game_2},
//                            .......]
//
//                            }



//                            [
//                            {sport_1},
//                            {sport_2},
//                            {sport_3}
//                            ,........]

                            Gson gson = new Gson();
                            Sport[] sport_tab = gson.fromJson(result.toString(),Sport[].class);
                            SportsAdapter adapter = new SportsAdapter(getActivity(),sport_tab);
                            listView.setAdapter(adapter);
                        }
                    }
                });
    }
}
