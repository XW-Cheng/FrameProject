package com.xianwei.frameproject.httpresponse;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 11:09
 * @描述       String callback
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.xianwei.frameproject.model.entity.HttpResult;
import com.zhy.http.okhttp.callback.StringCallback;

import cn.com.tool.view.HttpDialog;
import okhttp3.Call;
import okhttp3.Request;

public abstract class MyResultCallback<T> extends StringCallback {

	/**
	 * 请求dialog
	 */
	private HttpDialog progressDialog;

	/**
	 * 请求上下文
	 */
	public Context mContext;

	public static boolean isShow = true;


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
	public void onResponse(String response, int id) {
		if (!TextUtils.isEmpty(response)){
			HttpResult r = JSON.parseObject(response,HttpResult.class);
			if(r!=null){
				if(r.getCode()==API.SUCCESS_EXIST){
					onResponseSuccess(r);
				}else{
					onResponseFailed("RESULTERROR");
				}
			}
		}
	}

	@Override
	public void onError(Call call, Exception e, int id) {
		isShow = false;

		e.printStackTrace();
		onResponseFailed("ERROR");
	}

	@Override
	public void onAfter(int id) {
		super.onAfter(id);
		try {
			if (progressDialog != null) {
				progressDialog.dismiss();
			}

		} catch (Exception e) {
		}
	}

	/**
	 * 设置progressDialog
	 *
	 * @param pg
	 */
	public void setDialog(String pg) {
		progressDialog = new HttpDialog(mContext);
		if (!TextUtils.isEmpty(pg)) {
			progressDialog.setMessage(pg);
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
	public abstract void onResponseSuccess(HttpResult result);

	/**
	 * 请求失败回调
	 */
	public abstract void onResponseFailed(String msg);


}
