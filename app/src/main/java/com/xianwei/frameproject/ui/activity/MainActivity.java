package com.xianwei.frameproject.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.xianwei.frameproject.R;
import com.xianwei.frameproject.httpresponse.OkHttpsImp;

public class MainActivity extends FragmentActivity {


	private OkHttpsImp okHttpsImp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);
		okHttpsImp = OkHttpsImp.SINGLEOKHTTPSIMP.newInstance(this);
		final String s = null;
		findViewById(R.id.tv_main).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(s);
			}
		});
	}
}
