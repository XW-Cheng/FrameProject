package com.xianwei.frameproject.ui.activity;

import android.os.Bundle;

import com.xianwei.frameproject.R;
import com.xianwei.frameproject.httpresponse.OkHttpsImp;

public class MainActivity extends BaseActivity {


	private OkHttpsImp okHttpsImp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		okHttpsImp = OkHttpsImp.SINGLEOKHTTPSIMP.newInstance(this);

	}
}
