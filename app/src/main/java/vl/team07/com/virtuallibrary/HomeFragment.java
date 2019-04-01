/*
 * Class Name
 *
 * Date of Initiation
 *
 * Copyright @ 2019 Team 07, CMPUT 301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at the University of Alberta.
 * You can find a copy of the license in the github wiki for this project.
 */

package vl.team07.com.virtuallibrary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Displays the layout of the home page
 */
public class HomeFragment extends android.support.v4.app.Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    /**
     * Instantiates a new Home fragment.
     */
    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Modifies the screen view to accommodate the modifications
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View HomeView = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle(getResources().getText(R.string.app_name));

        viewPager = (ViewPager) HomeView.findViewById(R.id.HomeViewPager);
        tabLayout = (TabLayout) HomeView.findViewById(R.id.HomeTabLayout);

        tabLayout.setupWithViewPager(viewPager);
        setViewPager(viewPager);

        return HomeView;
    }

    @Override
    public void onStart(){
        super.onStart();

        String ISBN;

        if(MainActivity.SCAN_ISBN != null){
            ISBN = MainActivity.SCAN_ISBN;
            MainActivity.SCAN_ISBN = null;

            DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getContext());
            databaseHandler.loadBookByISBN(ISBN, new BookCallBack() {
                @Override
                public void onCallback(Book book) {
                    System.out.println("BOOK IS " + book.getTitle());
                    ScanBookInfo scanBookInfo = new ScanBookInfo(book);
                    scanBookInfo.showDialog(getContext());
                }
            });
        }


    /**
     * Set view pager.
     *
     * @param viewPager the view pager
     */
    public void setViewPager(ViewPager viewPager){
        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(getChildFragmentManager());
        
        tabViewPagerAdapter.addFragment(new MyBookFragment(), "My Books");
        tabViewPagerAdapter.addFragment(new BorrowedBookFragment(),"Borrowed Books");


        viewPager.setAdapter(tabViewPagerAdapter);
    }
}