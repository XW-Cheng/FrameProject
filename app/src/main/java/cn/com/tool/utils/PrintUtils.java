package cn.com.tool.utils;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 16:21
 * @描述       Log Toast封装类
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class PrintUtils {

	private static Toast toast = null;
	/**
	 * isDebug :是用来控制，是否打印日志
	 */
	private static final boolean isDeBug = true;
	/**
	 * VERBOSE日志形式的标识符
	 */
	public static final int VERBOSE = 5;
	/**
	 * DEBUG日志形式的标识符
	 */
	public static final int DEBUG = 4;
	/**
	 * INFO日志形式的标识符
	 */
	public static final int INFO = 3;
	/**
	 * WARN日志形式的标识符
	 */
	public static final int WARN = 2;
	/**
	 * ERROR日志形式的标识符
	 */
	public static final int ERROR = 1;

	/**
	 * Toast展示
	 * @param ct	上下文
	 * @param msg	展示文本
	 */
	public static void showToast(Context ct, String msg) {
		if (null != toast) {
			toast.setText(msg);
		} else {
			toast = Toast.makeText(ct, msg, Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	/**
	 * 把异常用来输出日志的综合方法
	 *
	 * @param  tag 日志标识
	 * @param  throwable 抛出的异常
	 * @param  type 日志类型
	 */
	public static void log(String tag, Throwable throwable, int type) {
		log(tag, exToString(throwable), type);
	}

	/**
	 * 用来输出日志的综合方法（文本内容）
	 *
	 * @param tag 日志标识
	 * @param msg 要输出的内容
	 * @param type 日志类型
	 */
	public static void log(String tag, String msg, int type) {
		switch (type) {
			case VERBOSE:
				v(tag, msg);// verbose等级
				break;
			case DEBUG:
				d(tag, msg);// debug等级
				break;
			case INFO:
				i(tag, msg);// info等级
				break;
			case WARN:
				w(tag, msg);// warn等级
				break;
			case ERROR:
				e(tag, msg);// error等级
				break;
			default:
				break;
		}
	}

	/**
	 * verbose等级的日志输出
	 *
	 * @param tag	日志标识
	 * @param msg	要输出的内容
	 */
	public static void v(String tag, String msg) {
		// 是否开启日志输出
		if (isDeBug) {
			Log.v(tag, msg);
		}

	}

	/**
	 * debug等级的日志输出
	 *
	 * @param tag	标识
	 * @param msg	内容
	 */
	public static void d(String tag, String msg) {
		if (isDeBug) {
			Log.d(tag, msg);
		}

	}

	/**
	 * info等级的日志输出
	 *
	 * @param  tag 标识
	 * @param  msg 内容
	 */
	public static void i(String tag, String msg) {
		if (isDeBug) {
			Log.i(tag, msg);
		}

	}

	/**
	 * warn等级的日志输出
	 *
	 * @param tag 标识
	 * @param msg 内容
	 */
	public static void w(String tag, String msg) {
		if (isDeBug) {
			Log.w(tag, msg);
		}

	}

	/**
	 * error等级的日志输出
	 *
	 * @param  tag 标识
	 * @param  msg 内容
	 */
	public static void e(String tag, String msg) {
		if (isDeBug) {
			Log.w(tag, msg);
		}

	}

	/**
	 * 把异常信息转化为字符串
	 *
	 * @param ex 异常信息
	 * @return 异常信息字符串
	 */
	private static String exToString(Throwable ex) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		printWriter.close();
		String result = writer.toString();
		return result;
	}

}
