package com.xianwei.frameproject.ui.activity;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 15:44
 * @描述       所有activity基类
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.xianwei.frameproject.R;
import com.xianwei.frameproject.app.ActivityManager;
import com.xianwei.frameproject.httpresponse.OkHttpsImp;

import cn.com.tool.utils.AppUtils;
import cn.com.tool.utils.PrintUtils;
import cn.com.tool.utils.ScrollerUtils;

public class BaseActivity extends FragmentActivity implements OnClickListener{

	public FragmentManager mFragmentManager;
	public boolean ISCONNECTED;
	public OkHttpsImp mOkHttpImp;
	/**
	 *	Activitu堆栈管理工具
	 */
	protected ActivityManager activityManager = ActivityManager.getInstance();
	public final int ORDINARYLAYOUT = 0,SCROLLLAYOUT = 1;
	private RelativeLayout mIncBaseactivityTitle;
	private TextView mTvBaseactivityTitle,mTvBaseactivityRight;
	private ImageView mImgBaseactivityRight,mImgBaseactivityLeft;
	private ScrollView mSllBaseactivityLayout;

	@Override
	protected void onCreate( Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//竖屏
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//FragmentManager
		mFragmentManager = getSupportFragmentManager();
		//判断网络是否有效
		ISCONNECTED = AppUtils.isNetworkAvailable(this);
		if(!ISCONNECTED){
			PrintUtils.showToast(this,"当前网络异常");
		}
		//初始化okHttpImp
		mOkHttpImp = OkHttpsImp.SINGLEOKHTTPSIMP.newInstance(this);
		activityManager.putActivity(this);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mOkHttpImp = OkHttpsImp.SINGLEOKHTTPSIMP.newInstance(this);
	}

	/**
	 * 设置布局
	 *
	 * @param layoutResID	布局ID
	 * @param type			布局类型 0普通布局 1滚动布局
	 */
	public void setContentView(int layoutResID,int type) {
		super.setContentView(R.layout.act_baseactivity);
		mIncBaseactivityTitle = (RelativeLayout) findViewById(R.id.inc_baseactivity_title);//整体title
		mImgBaseactivityLeft = (ImageView) findViewById(R.id.img_baseactivity_left);//返回键
		mTvBaseactivityTitle = (TextView) findViewById(R.id.tv_baseactivity_title);//title
		mImgBaseactivityRight = (ImageView) findViewById(R.id.img_baseactivity_right);//右边图片
		mTvBaseactivityRight = (TextView) findViewById(R.id.tv_baseactivity_right);//右边文字
		setListener();
		View view = View.inflate(this,layoutResID,null);
		switch (type){
			case ORDINARYLAYOUT://普通布局
				LinearLayout llt_baseactivity_layout = (LinearLayout) findViewById(R.id.llt_baseactivity_layout);
				llt_baseactivity_layout.setVisibility(View.VISIBLE);
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				llt_baseactivity_layout.addView(view,lp);
				break;
			case SCROLLLAYOUT://滚动布局
				mSllBaseactivityLayout = (ScrollView) findViewById(R.id.sll_baseactivity_layout);
				mSllBaseactivityLayout.setVisibility(View.VISIBLE);
				LayoutParams lps = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
				mSllBaseactivityLayout.addView(view,lps);
				break;
		}
	}

	/**
	 * 滚动(父试图为SCROLLLAYOUT时)到顶部
	 */
	public void sUp(){
		if(null!=mSllBaseactivityLayout){
			ScrollerUtils.scrollerup(mSllBaseactivityLayout);
		}
	}

	/**
	 * 滚动(父试图为SCROLLLAYOUT时)到底部
	 */
	public void sDown(){
		if(null!=mSllBaseactivityLayout){
			ScrollerUtils.scrollerdown(mSllBaseactivityLayout);
		}
	}

	/**
	 * 设置整体title的可见性
	 */
	public void setPageTitleVisiblity(int visiblity){
		mIncBaseactivityTitle.setVisibility(visiblity);
	}

	/**
	 * 设置整体title的背景颜色
	 * r.color.cccc/r.drawle.ddddd
	 */
	public void setPageTitleBackGroundColor(int color){
		mIncBaseactivityTitle.setBackgroundResource(color);
	}

	/**
	 * 设置title tv文字
	 */
	public void setPageTitleTvText(String titleText){
		mTvBaseactivityTitle.setText(titleText);
	}

	/**
	 * 设置title tv文字
	 * 	r.string.sssss
	 */
	public void setPageTitleTvText(int resId){
		mTvBaseactivityTitle.setText(resId);
	}

	/**
	 * 设置title tv可见性
	 */
	public void setPageTitleTvVisiblity(int visiblity){
		mTvBaseactivityTitle.setText(visiblity);
	}

	/**
	 * 设置title tv文字颜色
	 * r.color.cccc
	 */
	public void setPageTitleTvColor(int color){
		mTvBaseactivityTitle.setTextColor(color);
	}

	/**
	 * 设置返回键的可见性
	 */
	public void setPageBackVisiblity(int visiblity){
		mImgBaseactivityLeft.setVisibility(visiblity);
	}

	/**
	 * 设置返回键图片
	 */
	public void setPageBackResource(int resid){
		mImgBaseactivityLeft.setImageResource(resid);
		mImgBaseactivityLeft.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右边图片
	 */
	public void setPageRightRessouce(int resid){
		mImgBaseactivityRight.setImageResource(resid);
	}

	/**
	 * 设置右边图片可见性
	 */
	public void setPageRightVisiblity(int visiblity){
		mImgBaseactivityRight.setVisibility(visiblity);
	}

	/**
	 * 设置右边 tv文字
	 */
	public void setPageRightTvText(String titleText){
		mTvBaseactivityRight.setText(titleText);
	}

	/**
	 * 设置右边 tv文字
	 * 	r.string.sssss
	 */
	public void setPageRightTvText(int resId){
		mTvBaseactivityRight.setText(resId);
	}

	/**
	 * 设置右边 tv可见性
	 */
	public void setPageRightTvVisiblity(int visiblity){
		mTvBaseactivityRight.setText(visiblity);
	}

	/**
	 * 设置右边 tv文字颜色
	 * r.color.cccc
	 */
	public void setPageRightTvColor(int color){
		mTvBaseactivityRight.setTextColor(color);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityManager.removeActivity(this);
	}

	/**
	 * 结束应用所有activity
	 *
	 */
	public void exit() {
		activityManager.exit();
	}

	/**
	 * 结束当前应用除自己以外的所有activity
	 *
	 */
	public void exitWithOutMe() {
		activityManager.exitWithOutActivity(this);
	}

	@Override
	public void onBackPressed() {
		onTitleLeftClick();
	}

	/**
	 * 返回键点击事件
	 *
	 */
	protected void onTitleLeftClick() {
		finish();
	}

	/**
	 * title tv点击事件
	 */
	protected void onTitleTextClick(){

	}

	/**
	 * title右侧tv点击事件
	 */
	protected void onTitleRightTvClick(){

	}

	/**
	 * titlt右侧img点击事件
	 */
	protected void onTitleRightImgClick(){

	}

	/**
	 * 设置监听
	 */
	private void setListener() {
		mImgBaseactivityLeft.setOnClickListener(this);
		mTvBaseactivityTitle.setOnClickListener(this);
		mImgBaseactivityRight.setOnClickListener(this);
		mTvBaseactivityRight.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		AppUtils.closeSoftInput(this);
		switch (view.getId()){
			case R.id.img_baseactivity_left:
				onTitleLeftClick();
				break;
			case R.id.tv_baseactivity_title:
				onTitleTextClick();
				break;
			case R.id.tv_baseactivity_right:
				onTitleRightTvClick();
				break;
			case R.id.img_baseactivity_right:
				onTitleRightImgClick();
				break;
		}
		ISCONNECTED = AppUtils.isNetworkAvailable(this);
		if(!ISCONNECTED){
			PrintUtils.showToast(this,"当前网络环境出错");
		}
		onChildClick(view);
	}

	/**
	 * 子类点击事件
	 */
	private void onChildClick(View view) {

	}
}
