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

import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import cn.com.tool.utils.PrintUtils;
import cn.com.tool.view.HttpDialog;
import okhttp3.Call;
import okhttp3.Request;

public abstract class MyDownLoadResultCallback<T> extends FileCallBack {

	/**
	 * 请求dialog
	 */
	private HttpDialog progressDialog;

	public static boolean isShow = true;

	/**
	 * 请求进度条
	 */
	private ProgressBar mProgressBar;

	/**
	 * 请求上下文
	 */
	private Context mContext;


	public MyDownLoadResultCallback(String destFileDir, String destFileName) {
		super(destFileDir, destFileName);
	}

	@Override
	public void onBefore(Request request, int id) {
		super.onBefore(request, id);
		showDialog();
	}

	public void showDialog(){
		try {
			if(progressDialog!=null){
				progressDialog.show();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void onResponse(File response, int id) {
		if(!response.exists()&&response.length()!=0){
			onResponseSuccess(response);
		}else{
			onResponseFailed("RESULTERROR");
			PrintUtils.showToast(mContext,"下载失败");
		}
	}

	@Override
	public void onError(Call call, Exception e, int id) {
		isShow = false;
		e.printStackTrace();
		onResponseFailed("ERROR");
		PrintUtils.showToast(mContext,"连接超时,请稍后再试");
	}

	@Override
	public void onAfter(int id) {
		super.onAfter(id);
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * @param result	返回文件
	 *
	 */
	public abstract void onResponseSuccess(File result);

	/**
	 * 请求失败回调
	 */
	public abstract void onResponseFailed(String msg);
}
