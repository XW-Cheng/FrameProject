package com.xianwei.frameproject.httpresponse;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 10:57
 * @描述       OkHttpsImp
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;

public enum OkHttpsImp {

	/**
	 * 单例实例
	 */
	SINGLEOKHTTPSIMP;
	/**
	 * 上下文
	 */
	private static Context mContext;

	/**
	 * 获取实例
	 */
	public OkHttpsImp newInstance(Context ct) {
		mContext = ct;
		return SINGLEOKHTTPSIMP;
	}

	/**
	 * 路径拼装(获取绝对路径)
	 */
	private String getAbsoluteUrl(String relativeUrl) {
		if (relativeUrl.startsWith("http://"))
			return relativeUrl;
		return API.TEST_HOST_URL + relativeUrl;
	}

	/**
	 * 初始化GRT请求有progress
	 * @param myResultCallback 请求结果回调
	 * @param params           请求参数
	 * @param url              请求地址
	 */
	private void getProgressResponse(MyResultCallback<String> myResultCallback, Map<String, String> params
			, String url) {
		myResultCallback.setContext(mContext);
		myResultCallback.setDialog("");
		OkHttpUtils.get().url(url).params(params).build().execute(myResultCallback);
	}

	/**
	 * 初始化GRT请求无progress
	 * @param myResultCallback 请求结果回调
	 * @param params           请求参数
	 * @param url              请求地址
	 */
	private void getNoProgressResponse(MyResultCallback<String> myResultCallback
			, Map<String, String> params, String url) {
		myResultCallback.setContext(mContext);
		OkHttpUtils.get().url(url).params(params).build().execute(myResultCallback);
	}

	/**
	 * 初始化post请求有progress
	 * @param myResultCallback 请求结果回调
	 * @param params           请求参数
	 * @param url              请求地址
	 */
	private void postProgressResponse(MyResultCallback<String> myResultCallback
			, Map<String, String> params, String url) {
		myResultCallback.setContext(mContext);
		myResultCallback.setDialog("");
		OkHttpUtils.post().url(url).params(params).build().execute(myResultCallback);
	}

	/**
	 * 初始化post请求无progress
	 * @param myResultCallback 请求结果回调
	 * @param params           请求参数
	 * @param url              请求地址
	 */
	private void postNoProgressResponse(MyResultCallback<String> myResultCallback
			, Map<String, String> params, String url) {
		myResultCallback.setContext(mContext);
		OkHttpUtils.post().url(url).params(params).build().execute(myResultCallback);
	}

	/**
	 *
	 * @param myResultCallback	请求结果回调
	 * @param bean				post_bean
	 * @param url				请求地址
	 */
	private static <T> void postJsonProgressResponse(MyResultCallback<String> myResultCallback
			, String url,Class<T> bean){
		myResultCallback.setContext(mContext);
		myResultCallback.setDialog("");
		OkHttpUtils.postString().url(url).content(new JSONObject().toJSONString(bean))
				.mediaType(MediaType.parse("application/json; charset=utf-8"))
				.build().execute(myResultCallback);
	}

	/**
	 * 初始化文件上传多个文件
	 * @param myResultCallback	请求结果回调
	 * @param fileList			上传文件
	 * @param params			请求参数
	 * @param url				请求地址
	 */
	private void postFileProgressResponse(MyResultCallback<String> myResultCallback
			, List<File> fileList, Map<String, String> params, String url){
		myResultCallback.setContext(mContext);
		myResultCallback.setDialog("");

	//	OkHttpUtils.post().addFile().url(url).params(params).build().execute(myResultCallback);
	}

	/**
	 * 初始化文件上传单个文件
	 * @param myResultCallback	请求结果回调
	 * @param file				上传文件
	 * @param url				请求地址
	 */
	private void postFileProgressResponse(MyResultCallback<String> myResultCallback
			, File file, String url){
		myResultCallback.setContext(mContext);
		myResultCallback.setDialog("");
		OkHttpUtils.postFile().url(url).file(file).build().execute(myResultCallback);
	}


}
