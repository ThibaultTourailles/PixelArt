package com.pixelart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import fragment.DrawingFragment;
import fragment.ShareFragment;
import fragment.ToolsFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
	
	final int PAGE_COUNT = 3;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
    	Fragment myFragment;
    	switch(arg0) {
    	case 0 :
    		myFragment = new ToolsFragment();
    		break;
    	case 1 :
    		myFragment = new DrawingFragment();
    		break ;
    	default :
    		myFragment = new ShareFragment();
    	}
        Bundle data = new Bundle();
        data.putInt("current_page", arg0+1);
        myFragment.setArguments(data);
        return myFragment;
    }
 
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    
    @Override
    public CharSequence getPageTitle(int position) {
    	String s = "";
    	switch (position) {
    	case 0 : 
    		s = "Colors";
    		break;
    	case 1 :
    		s = "Draw";
    		break;
    	case 2 :
    		s = "Save";
    		break;
    	}
        return s;
    }

}
