package com.xianwei.frameproject.ui.fragment;/*
 * @创建者     Administrator
 * @创建时间   2016/9/29 14:48
 * @描述       SecondFragment
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianwei.frameproject.ui.activity.MainActivity;

public class SecondFragment extends Fragment {

	private MainActivity mActivity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		mActivity = (MainActivity) getActivity();

		TextView tv = new TextView(mActivity);
		tv.setText("Second");

		return tv;
	}
}
