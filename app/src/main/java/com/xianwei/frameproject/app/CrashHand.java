package com.xianwei.frameproject.app;/*
 * @创建者     Administrator
 * @创建时间   2016/9/29 10:32
 * @描述
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

public class CrashHand implements Thread.UncaughtExceptionHandler {
	// 需求是 整个应用程序 只有一个 MyCrash-Handler
	private static CrashHand INSTANCE;

	// 1.私有化构造方法
	private CrashHand() {

	}

	public static synchronized CrashHand getInstance() {
		if (INSTANCE == null)
			INSTANCE = new CrashHand();
		return INSTANCE;
	}

	public void uncaughtException(Thread arg0, Throwable arg1) {
		// 在此可以把用户手机的一些信息以及异常信息捕获并上传,由于UMeng在这方面有很程序的api接口来调用，故没有考虑

		// 干掉当前的程序
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
