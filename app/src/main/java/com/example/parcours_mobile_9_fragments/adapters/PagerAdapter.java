package com.example.parcours_mobile_9_fragments.adapters;

import com.example.parcours_mobile_9_fragments.fragments_act.GamesFragment;
import com.example.parcours_mobile_9_fragments.fragments_act.HotelsFragment;
import com.example.parcours_mobile_9_fragments.fragments_act.SportsFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {


    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                GamesFragment gamesFragment = new GamesFragment();
                return gamesFragment;

            case 1 :
                SportsFragment sportsFragment = new SportsFragment();
                return sportsFragment;

            case 2 :
                HotelsFragment hotelsFragment = new HotelsFragment();
                return hotelsFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
