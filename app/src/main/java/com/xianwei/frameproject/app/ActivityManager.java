package com.xianwei.frameproject.app;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 16:39
 * @描述       Activity管理器
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.app.Activity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ActivityManager {
	ACTIVITYMANAGER;
	public static ActivityManager getInstance(){
		return ACTIVITYMANAGER;
	}
	/**
	 * task map，用于记录activity栈，方便退出程序（这里为了不影响系统回收activity，所以用软引用）
	 */
	private final HashMap<String, SoftReference<Activity>> taskMap = new HashMap<String, SoftReference<Activity>>();

	/**
	 * 往应用task map加入activity
	 */
	public final void putActivity(Activity atv) {
		taskMap.put(atv.toString(), new SoftReference<Activity>(atv));
	}

	/**
	 * 往应用task mapy移除activity
	 */
	public final void removeActivity(Activity atv) {
		taskMap.remove(atv.toString());
	}

	/**
	 * 清除应用的task栈，如果程序正常运行这会导致应用退回到桌面
	 */
	public final void exit() {
		exitWithOutActivity(null);
	}

	/**
	 * 清除应用的task栈，留下指定activity
	 */
	public final void exitWithOutActivity(Activity act) {
		for (Iterator<Map.Entry<String, SoftReference<Activity>>> iterator = taskMap
				.entrySet().iterator(); iterator.hasNext();) {
			SoftReference<Activity> activityReference = iterator.next()
					.getValue();
			Activity activity = activityReference.get();
			if (activity != null && activity != act) {
				activity.finish();
			}
		}
		taskMap.clear();
	}

	/**
	 * 清除应用的task栈，留下指定activity列表
	 */
	public final void exitWithOutActivityList(ArrayList<Activity> acts) {
		for (Iterator<Map.Entry<String, SoftReference<Activity>>> iterator = taskMap
				.entrySet().iterator(); iterator.hasNext();) {
			SoftReference<Activity> activityReference = iterator.next()
					.getValue();
			Activity activity = activityReference.get();
			if (activity != null) {
				if (acts != null && acts.contains(activity)) {
					continue;
				} else {
					activity.finish();
				}
			}
		}
		taskMap.clear();
	}

}
