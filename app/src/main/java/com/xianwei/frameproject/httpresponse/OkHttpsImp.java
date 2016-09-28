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
import android.widget.ProgressBar;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;

import java.io.File;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
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
	private static final String SignType = "MD5";
	private static final String inputCharset = "UTF-8";
	public static final String md5_key = "d2a57dc1d883fd21fb9951699df71cc7";

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

		PostFormBuilder builder = OkHttpUtils.post();
		for(int i=0;i<fileList.size();i++){
			builder.addFile("file"+i,"image"+i+".png",fileList.get(i));
		}

		builder.url(url).params(params).build().execute(myResultCallback);

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

	/**
	 * 初始化文件下载
	 * @param myDownLoadResultCallback	请求结果回调
	 * @param url						请求地址
	 * @param p							ProgressBar
	 */
	private void downloadFileResponse(MyDownLoadResultCallback<String> myDownLoadResultCallback
			,String url,ProgressBar p){
		myDownLoadResultCallback.setDialog(p);
		myDownLoadResultCallback.setContext(mContext);
		OkHttpUtils.get().url(url).build().execute(myDownLoadResultCallback);
	}


	/**
	 * 签名方法
	 */
	private static String getSign(String md5_key, Map<String, String> dataMap) throws Exception {
		List<String> keyList = new ArrayList<>(dataMap.keySet());
		Collections.sort(keyList);
		StringBuilder builder = new StringBuilder();
		for (String mapKey : keyList) {
			// builder.append(mapKey).append("=").append(dataMap.get(mapKey))
			// .append("&");
			if (!isChinese(dataMap.get(mapKey))) {
				builder.append(dataMap.get(mapKey));
			}
		}
		// builder.append("key=").append(md5_key);
		builder.append(md5_key);
		MessageDigest md5 = MessageDigest.getInstance(SignType);
		md5.update(builder.toString().getBytes(inputCharset));
		byte[] md5Bytes = md5.digest();
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 判断是否有中文
	 */
	public static boolean isChinese(String str) {
		if (str.length() < str.getBytes().length) {
			return true;// 中文
		} else {
			return false;
		}
	}


}
