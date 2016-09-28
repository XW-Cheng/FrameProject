package com.xianwei.frameproject.model.entity;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 13:11
 * @描述       http返回最外层数据
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import java.io.Serializable;

public class HttpResult implements Serializable {
	private static final long serialVersionUID = -796948551411307701L;

	private int code;
	private String msg;
	private String data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
