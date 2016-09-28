package cn.com.tool.view;/*
 * @创建者     Administrator
 * @创建时间   2016/9/28 11:11
 * @描述       HttpDialog
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述   ${TODO}
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.xianwei.frameproject.R;

public class HttpDialog extends Dialog {

	private String message = "";

	private int gifView = 0;

	private TextView mTextView;

	private GifView mGifView;

	private Context mContext;

	public HttpDialog(Context context) {
		this(context, R.style.HttpDialog);
		setCanceledOnTouchOutside(false);
	}

	public HttpDialog(Context context, int themeResId) {
		super(context, themeResId);
		setContentView(R.layout.httpdialog);
		initView();
	}

	private void initView(){
		mTextView = (TextView) findViewById(R.id.tv_httpdialog);
		mTextView.setText("请稍后...");
		mGifView = (GifView) findViewById(R.id.gif_httpdialog);
		mGifView.setMovieResource(R.mipmap.httpdialog);
	}

	/**
	 * 设置diaolog文字
	 */
	public void setMessage(String message){
		this.message = message;
		mTextView.setText(message);
	}

	/**
	 * 设置dialog gif动画
	 */
	public void setGifView(int gifView){
		this.gifView = gifView;
		mGifView.setMovieResource(gifView);
	}

	/**
	 * 设置diaolog颜色
	 */
	public void setColor(int color){
		mTextView.setTextColor(mContext.getResources().getColor(color));
	}

	/**
	 * 获取dialog文字
	 */
	public String getMessage() {
		return message;
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

}
