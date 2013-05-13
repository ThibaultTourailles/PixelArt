package com.pixelart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class StartActivity extends FragmentActivity {

	MyFragmentPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_start);
		
		Intent intent = getIntent();
		Tools.size = intent.getIntExtra("size", 30);

		this.mViewPager = (ViewPager) findViewById(R.id.pager);

		this.mSectionsPagerAdapter = new MyFragmentPagerAdapter(this.getSupportFragmentManager());
		this.mViewPager.setAdapter(mSectionsPagerAdapter);
		this.mViewPager.setCurrentItem(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
