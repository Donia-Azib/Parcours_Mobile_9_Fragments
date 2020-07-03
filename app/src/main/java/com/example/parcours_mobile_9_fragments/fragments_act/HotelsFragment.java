package com.example.parcours_mobile_9_fragments.fragments_act;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.parcours_mobile_9_fragments.R;
import com.example.parcours_mobile_9_fragments.adapters.HotelAdapter;
import com.example.parcours_mobile_9_fragments.model.Hotel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotelsFragment extends Fragment {

    private ListView list;
    public HotelsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hotels, container, false);
        
        list = view.findViewById(R.id.hotel_list);
     
        setHotelListData();
        return view;
    }

    private void setHotelListData() {
        String url = "https://leejaew-hotels-in-singapore-v1.p.rapidapi.com/hotels?country=Singapore";
        Ion.with(getActivity())
                .load(url)
                .setHeader("x-rapidapi-host","leejaew-hotels-in-singapore-v1.p.rapidapi.com")
                .setHeader("x-rapidapi-key","1eca79ea2fmshb41dd269e4b7f29p16c9b3jsnc398cc5272a7")
                .setHeader("useQueryString","true")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        if(e!=null)
                        {
                            Log.e("TAG", "onCompleted: Exception "+e.getMessage() );

                        }
                        else
                        {
                            Gson gson = new Gson();
                            Hotel[] hotel_tab = gson.fromJson(result.toString(),Hotel[].class);

                            HotelAdapter adapter = new HotelAdapter(getActivity(),hotel_tab);
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }
                    }
                });
    }


}

