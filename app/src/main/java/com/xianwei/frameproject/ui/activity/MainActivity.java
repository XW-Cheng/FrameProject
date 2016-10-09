package com.xianwei.frameproject.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.xianwei.frameproject.R;
import com.xianwei.frameproject.ui.fragment.FirstFragment;
import com.xianwei.frameproject.ui.fragment.ForthFragment;
import com.xianwei.frameproject.ui.fragment.SecondFragment;
import com.xianwei.frameproject.ui.fragment.ThirdFragment;

import cn.com.tool.utils.PrintUtils;

public class MainActivity extends BaseActivity {

	private FrameLayout mFmMainFirst, mFmMainSecond, mFmMainThird, mFmMainForth;
	private RadioButton mRbMainFirst, mRbMainSecond, mRbMainThird, mRbMainForth;
	private FirstFragment mFirstFragment;
	private SecondFragment mSecondFragment;
	private ThirdFragment mThirdFragment;
	private ForthFragment mForthFragment;
	private OnCheckedChangeListener mCheckListener;
	private int POSITION0 = 0, POSITION1 = 1, POSITION2 = 2, POSITION3 = 3, CLICKPOSITION = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main,ORDINARYLAYOUT);

		initView();

		initFragment();

		checkButton();

		setListener();

		changeFuncPage(POSITION0);
	}

	/**
	 * 添加监听
	 */
	private void setListener(){
		mRbMainFirst.setOnCheckedChangeListener(mCheckListener);
		mRbMainSecond.setOnCheckedChangeListener(mCheckListener);
		mRbMainThird.setOnCheckedChangeListener(mCheckListener);
		mRbMainForth.setOnCheckedChangeListener(mCheckListener);
	}

	/**
	 * 地步监听
	 */
	private void checkButton() {
		mCheckListener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					if (mRbMainFirst == buttonView) {
						mRbMainSecond.setChecked(false);
						mRbMainThird.setChecked(false);
						mRbMainForth.setChecked(false);
						changeFuncPage(POSITION0);
					} else if (mRbMainSecond == buttonView) {
						mRbMainFirst.setChecked(false);
						mRbMainThird.setChecked(false);
						mRbMainForth.setChecked(false);
						changeFuncPage(POSITION1);
					} else if (mRbMainThird == buttonView) {
						mRbMainFirst.setChecked(false);
						mRbMainSecond.setChecked(false);
						mRbMainForth.setChecked(false);
						changeFuncPage(POSITION2);
					} else if (mRbMainForth == buttonView) {
						mRbMainFirst.setChecked(false);
						mRbMainSecond.setChecked(false);
						mRbMainThird.setChecked(false);
						changeFuncPage(POSITION3);
					}
				}
			}
		};
	}

	/**
	 * 切换页面
	 */
	private void changeFuncPage(int position) {
		this.CLICKPOSITION = position;
		if (position < POSITION0)
			return;
		if (position == POSITION0) {
			CLICKPOSITION = POSITION0;
			setPageBackVisiblity(View.INVISIBLE);
			setPageTitleTvText("First");
			mRbMainFirst.setChecked(true);
			mFmMainFirst.setVisibility(View.VISIBLE);
			mFmMainSecond.setVisibility(View.GONE);
			mFmMainThird.setVisibility(View.GONE);
			mFmMainForth.setVisibility(View.GONE);

		} else if (position == POSITION1) {
			CLICKPOSITION = POSITION1;
			setPageBackVisiblity(View.INVISIBLE);
			setPageTitleTvText("Second");
			mRbMainSecond.setChecked(true);
			mFmMainFirst.setVisibility(View.GONE);
			mFmMainSecond.setVisibility(View.VISIBLE);
			mFmMainThird.setVisibility(View.GONE);
			mFmMainForth.setVisibility(View.GONE);

		} else if (position == POSITION2) {
			CLICKPOSITION = POSITION2;
			setPageBackVisiblity(View.INVISIBLE);
			setPageTitleTvText("Third");
			mRbMainThird.setChecked(true);
			mFmMainFirst.setVisibility(View.GONE);
			mFmMainSecond.setVisibility(View.GONE);
			mFmMainThird.setVisibility(View.VISIBLE);
			mFmMainForth.setVisibility(View.GONE);

		} else if (position == POSITION3) {
			CLICKPOSITION = POSITION3;
			setPageBackVisiblity(View.INVISIBLE);
			setPageTitleTvText("Forth");
			mRbMainForth.setChecked(true);
			mFmMainFirst.setVisibility(View.GONE);
			mFmMainSecond.setVisibility(View.GONE);
			mFmMainThird.setVisibility(View.GONE);
			mFmMainForth.setVisibility(View.VISIBLE);

		}

	}

	/**
	 * 初始化四大布局
	 */
	private void initFragment() {
		mFirstFragment = new FirstFragment();
		mSecondFragment = new SecondFragment();
		mThirdFragment = new ThirdFragment();
		mForthFragment = new ForthFragment();

		mFragmentManager.beginTransaction().replace(R.id.fm_main_first, mFirstFragment).commit();
		mFragmentManager.beginTransaction().replace(R.id.fm_main_second, mSecondFragment).commit();
		mFragmentManager.beginTransaction().replace(R.id.fm_main_third, mThirdFragment).commit();
		mFragmentManager.beginTransaction().replace(R.id.fm_main_forth, mForthFragment).commit();
	}

	/**
	 * 初始化布局
	 */
	private void initView() {

		titleFirst();

		mFmMainFirst = (FrameLayout) findViewById(R.id.fm_main_first);
		mFmMainSecond = (FrameLayout) findViewById(R.id.fm_main_second);
		mFmMainThird = (FrameLayout) findViewById(R.id.fm_main_third);
		mFmMainForth = (FrameLayout) findViewById(R.id.fm_main_forth);

		mRbMainFirst = (RadioButton) findViewById(R.id.rb_main_first);
		mRbMainSecond = (RadioButton) findViewById(R.id.rb_main_second);
		mRbMainThird = (RadioButton) findViewById(R.id.rb_main_third);
		mRbMainForth = (RadioButton) findViewById(R.id.rb_main_forth);
	}

	/**
	 * 设置第一页
	 */
	private void titleFirst() {
	}

	/**
	 * 返回键时间间隔
	 */
	private long mExitTime;

	/**
	 * 返回键监听
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if ((System.currentTimeMillis() - mExitTime) > 1000) {
				PrintUtils.showToast(this,"在摁一次退出");

				mExitTime = System.currentTimeMillis();

			} else {

				finish();

			}

			return true;

		}

		return super.onKeyDown(keyCode, event);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
