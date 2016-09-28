package com.xianwei.frameproject.httpresponse;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 14:04
 * @描述       okhttp下载文件
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.content.Context;
import android.widget.ProgressBar;

import com.xianwei.frameproject.model.entity.HttpResult;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

public abstract class MyDownLoadResultCallback<T> extends FileCallBack {

	public MyDownLoadResultCallback(String destFileDir, String destFileName) {
		super(destFileDir, destFileName);
	}
	/**
	 * 请求进度条
	 */
	private ProgressBar mProgressBar;
	/**
	 * 请求上下文
	 */
	private Context mContext;

	@Override
	public void onBefore(Request request, int id) {
		super.onBefore(request, id);
	}

	@Override
	public void onResponse(File response, int id) {

	}

	@Override
	public void onError(Call call, Exception e, int id) {

	}

	@Override
	public void onAfter(int id) {
		super.onAfter(id);
	}

	@Override
	public void inProgress(float progress, long total, int id) {
		super.inProgress(progress, total, id);
		if(mProgressBar!=null){
			mProgressBar.setProgress((int) (100 * progress));
		}
	}

	/**
	 * 设置progressDialog
	 *
	 * @param pg
	 */
	public void setDialog(ProgressBar pg) {
		if (pg != null) {
			mProgressBar = pg;
		}
	}

	/**
	 * 设置上下文
	 *
	 * @param ct
	 */
	public void setContext(Context ct) {
		if (ct != null) {
			mContext = ct;
		}
	}

	/**
	 * 请求成功回调
	 *
	 * @param result
	 *            返回结果
	 */
	public abstract void onResponseResult(HttpResult result);

	/**
	 * 请求失败回调
	 */
	public abstract void onResponseFailed(String msg);
}
