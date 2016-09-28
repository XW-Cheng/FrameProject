package cn.com.tool.utils;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 17:30
 * @描述       ScrollerUtils
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.os.Handler;
import android.widget.ScrollView;

public class ScrollerUtils {
	private static final long TIME = 10;

	/**
	 * 设置滚动到底部
	 */
	public static void scrollerdown(final ScrollView scroll) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				scroll.fullScroll(ScrollView.FOCUS_DOWN);

			}
		}, TIME);
	}

	/**
	 * 设置滚动到顶部
	 */
	public static void scrollerup(final ScrollView scroll) {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				scroll.fullScroll(ScrollView.FOCUS_UP);

			}
		}, TIME);
	}
}
