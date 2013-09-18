package com.sscaipiao.android.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.sscaipiao.android.R;

public class GuideActivity extends Activity {
	
	private ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);
		
		 mViewPager = (ViewPager)findViewById(R.id.viewpager_guide  );

 
	     //将要分页显示的View装入数组中
		 	        LayoutInflater mLi = LayoutInflater.from(this);
		 	        View view1 = mLi.inflate(R.layout.guide1, null);
		 	        View view2 = mLi.inflate(R.layout.guide2, null);
		 	        View view3 = mLi.inflate(R.layout.guide3, null);
		 	        
		 	       final ArrayList<View> views = new ArrayList<View>();
		 	      	        views.add(view1);
		 	      	        views.add(view2);
		 	      	        views.add(view3);
		 	      	        
		 	      	   //填充ViewPager的数据适配器
		 	      	     	        PagerAdapter mPagerAdapter = new PagerAdapter() {
		 	      	     	             
		 	      	     	            @Override
		 	      	     	            public boolean isViewFromObject(View arg0, Object arg1) {
		 	      	     	                return arg0 == arg1;
		 	      	     	            }
		 	      	     	             
		 	      	     	            @Override
		 	      	     	            public int getCount() {
		 	      	     	                return views.size();
		 	      	     	            }
		 	      	     	 
		 	      	    	            @Override
		 	      	     	            public void destroyItem(View container, int position, Object object) {
		 	      	     	                ((ViewPager)container).removeView(views.get(position));
		 	      	     	            }
		 	      	     	 
		 	      	     	 
		 	      	     	            @Override
		 	      	     	            public Object instantiateItem(View container, int position) {
		 	      	     	                ((ViewPager)container).addView(views.get(position));
		 	      	     	                return views.get(position);
		 	      	     	            }
		 	      	     	        };
		 	      	     	         
		 	      	     	        mViewPager.setAdapter(mPagerAdapter);
	}
	
	public void guide_next(View view){
		 SharedPreferences properties = getSharedPreferences("properties", 0); 
		 properties.edit().putString("needGuide", "false").commit();  
        Intent intent  = new Intent(GuideActivity.this, LoginActivity.class);
        Bundle b=new Bundle();		                            
        intent.putExtras(b);
        startActivity(intent);
	}

}
