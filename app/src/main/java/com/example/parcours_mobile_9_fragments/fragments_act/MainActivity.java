package com.example.parcours_mobile_9_fragments.fragments_act;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.parcours_mobile_9_fragments.adapters.PagerAdapter;
import com.example.parcours_mobile_9_fragments.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

//        create tab item => text
        tabLayout.addTab(tabLayout.newTab().setText("Games"));//pos: 0
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));//pos: 1
        tabLayout.addTab(tabLayout.newTab().setText("Hotels"));//pos: 2
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        view pager mission
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
