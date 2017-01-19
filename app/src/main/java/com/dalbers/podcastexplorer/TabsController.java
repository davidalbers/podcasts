package com.dalbers.podcastexplorer;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.support.ControllerPagerAdapter;
import com.dalbers.podcastexplorer.Search.SearchNavigationController;

/**
 * Created by davidalbers on 1/7/17.
 */

public class TabsController extends Controller {
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.controller_tabs, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        ControllerPagerAdapter pagerAdapter = new ControllerPagerAdapter(this, false) {
            @Override
            public Controller getItem(int position) {
                return new SearchNavigationController();
            }

            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "Page " + position;
            }
        };

        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        viewPager.setAdapter(null);
        super.onDestroyView(view);
    }
}
