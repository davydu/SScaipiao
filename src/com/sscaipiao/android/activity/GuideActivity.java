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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sscaipiao.android.R;

public class GuideActivity extends Activity {

	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);

		mViewPager = (ViewPager) findViewById(R.id.viewpager_guide);

		final RadioGroup dotGroupButton = (RadioGroup) findViewById(R.id.dotGroupButton);
		for (int i = 0; i < 3; i++) {
			// 下部的小白点
			final RadioButton dotButton = new RadioButton(this);
			dotButton.setId(i);
			dotButton.setLayoutParams(new RadioGroup.LayoutParams(
					RadioGroup.LayoutParams.WRAP_CONTENT,
					RadioGroup.LayoutParams.WRAP_CONTENT));
			dotButton.setPadding(20, 20, 20, 20);
			// dotButton.setBackgroundResource(R.drawable.dot_bg);//不能用backgroundresource，
			dotButton.setButtonDrawable(R.drawable.dot_bg);

			dotButton.setTag(i);// 设置当前位置
			// 为点注册checked事件，当点击对应的点时，Viewpager切换到对应的page,并将点击的点设置为高亮
			dotButton
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								mViewPager.setCurrentItem((Integer) dotButton
										.getTag());
							}
						}
					});

			dotGroupButton.addView(dotButton);
			dotGroupButton.check(0);// 将第一个小白点设置为高亮
		}

		/**
		 * 监听PageChange事件，切换底部小点的高亮位置
		 */
		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						((RadioButton) dotGroupButton.getChildAt(position))
								.setChecked(true);
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
					}
				});

		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.guide1, null);
		View view2 = mLi.inflate(R.layout.guide2, null);
		View view3 = mLi.inflate(R.layout.guide3, null);

		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		// 填充ViewPager的数据适配器
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
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	public void guide_next(View view) {
		SharedPreferences properties = getSharedPreferences("properties", 0);
		properties.edit().putString("needGuide", "false").commit();
		Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
		Bundle b = new Bundle();
		intent.putExtras(b);
		startActivity(intent);
	}

}
