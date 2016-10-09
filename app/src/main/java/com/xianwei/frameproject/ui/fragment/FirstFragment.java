package com.xianwei.frameproject.ui.fragment;/*
 * @创建者     Administrator
 * @创建时间   2016/9/29 14:48
 * @描述       FirstFagment
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xianwei.frameproject.R;
import com.xianwei.frameproject.httpresponse.OkHttpsImp;
import com.xianwei.frameproject.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import cn.com.tool.view.RecycleViewDivider;

public class FirstFragment extends Fragment {

	private MainActivity mActivity;
	private OkHttpsImp mOkHttpsImp;
	private RecyclerView mRecyclerView;
	private List<String> mDatas;
	private HomeAdapter mAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fm_firstfragment,null);
		mActivity = (MainActivity) getActivity();
		initView(view);
		initData();
		mOkHttpsImp = OkHttpsImp.SINGLEOKHTTPSIMP.newInstance(mActivity);
		return view;
	}

	private void initData() {
		mDatas = new ArrayList<>();
		for (int i='A';i<'z';i++){
			mDatas.add(""+(char)i);
		}
	}

	private void initView(View view) {
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_firstfragment);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
		mRecyclerView.addItemDecoration(new RecycleViewDivider(mActivity,RecycleViewDivider.VERTICAL_LIST,R.drawable.divider_recyclerview));
		mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
	}

	class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{


		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mActivity)
					.inflate(R.layout.item_firstfragment,parent,false));
			return holder;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.tv.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder{

			TextView tv;

			public MyViewHolder(View itemView) {
				super(itemView);
				tv = (TextView) itemView.findViewById(R.id.tv_item_firstframent);
			}
		}
	}
}
