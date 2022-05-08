package com.bmcong2k.luyentapsql2.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bmcong2k.luyentapsql2.fragment.FragmentDanhSach;
import com.bmcong2k.luyentapsql2.fragment.FragmentSearch;
import com.bmcong2k.luyentapsql2.fragment.FragmentThongTin;


public class ViewPagerAdaper extends FragmentStatePagerAdapter {


    public ViewPagerAdaper(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // vi tri fragment

        switch (position){
            case 0 :
               return new FragmentThongTin();
            case 1 :
               return new FragmentDanhSach();
            case 2:
                return new FragmentSearch();
            default:
               return new FragmentThongTin();
        }
    }

    @Override
    public int getCount() {
        // tra ve 3 fragment
        return 3;
    }
}
