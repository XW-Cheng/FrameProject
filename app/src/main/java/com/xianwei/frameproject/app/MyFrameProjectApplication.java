package com.xianwei.frameproject.app;
/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 10:36
 * @描述		   MyFrameProjectApplication
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import cn.com.tool.utils.DataCleanUtils;
import cn.com.tool.utils.FileUtils;
import okhttp3.OkHttpClient;

public class MyFrameProjectApplication extends Application {

	/**
	 * 进行各种初始化
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		/**
		 * 初始化okhttputils
		 * github地址：https://github.com/hongyangAndroid/okhttputils
		 */
		HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.connectTimeout(10000L, TimeUnit.MILLISECONDS)
				.readTimeout(10000L,TimeUnit.MILLISECONDS)
				.retryOnConnectionFailure(false)//当okhttp在网络情况不好的情况下会请求两次服务器
				.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)//设置可以访问所有的http网站
				//.其他想要的配置
				.build();
		OkHttpUtils.initClient(okHttpClient);

		//捕获异常
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());

		//清除缓存
		DataCleanUtils.cleanExternalCache(getApplicationContext());
		DataCleanUtils.cleanCustomCache(FileUtils.createSDCardDir("cacheImages"));
	}
}
