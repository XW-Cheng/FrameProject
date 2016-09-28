package cn.com.tool.utils;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 16:00
 * @描述       应用工具类
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.inputmethod.InputMethodManager;

public class AppUtils {

	/**
	 * 获取VersionName
	 * @param ct	上下文
	 */
	public static String getAppVersionName(Context ct){
		PackageInfo pinfo = null;
		try {
			PackageManager pm = ct.getPackageManager();
			pinfo = pm.getPackageInfo(ct.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
		}catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return pinfo.versionName;
	}

	/**
	 * 获取VersionCode
	 * @param ct	上下文
	 */
	public static String getAppVersionCode(Context ct){
		PackageInfo pinfo = null;
		try {
			PackageManager pm = ct.getPackageManager();
			pinfo = pm.getPackageInfo(ct.getPackageName(),
					PackageManager.GET_CONFIGURATIONS);
		}catch (NameNotFoundException e){
			e.printStackTrace();
		}
		return pinfo.versionCode+"";
	}

	/**
	 * 判断网络是否可用
	 * @param ct	上下文
	 */
	public static boolean isNetworkAvailable(Context ct){
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
			if(null!=connectivityManager){
				NetworkInfo info = connectivityManager.getActiveNetworkInfo();
				if(null!=info.getState()&&info.isConnected()){
					if(info.getState()==NetworkInfo.State.CONNECTED){
						return true;
					}
				}
			}
		}catch (Exception e){
			return false;
		}
		return false;
	}

	/**
	 * 打开键盘.
	 *
	 * @param context
	 *            the context
	 */
	public static void showSoftInput(Context context) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.toggleSoftInput(0,
				InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 关闭键盘事件.
	 *
	 * @param context
	 *            the context
	 */
	public static void closeSoftInput(Context context) {
		InputMethodManager inputMethodManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (inputMethodManager != null
				&& ((Activity) context).getCurrentFocus() != null) {
			inputMethodManager.hideSoftInputFromWindow(((Activity) context)
							.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
}
