package cn.com.tool.utils;/*
 * @创建者     Administrator
 * @创建时间   2016/10/9 9:33
 * @描述       WebView初始化工具类
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.app.Activity;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewInitUtils {

	public static WebView WebSettingInit(WebView webView, final Activity activity) {
		WebSettings ws = webView.getSettings();
		ws.setJavaScriptEnabled(true);//设置支持JS
		ws.setBuiltInZoomControls(false);//设置支持缩放
		ws.setUseWideViewPort(true);// 设置此属性，可任意比例缩放 将图片调整到适合webview的大小
		ws.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
		ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		ws.setSupportZoom(false); // 支持缩放
		// 设置 缓存模式
		ws.setCacheMode(WebSettings.LOAD_NO_CACHE); // 关闭webview中缓存

		webView.clearCache(true);
		webView.setTag(true);
		// 开启 DOM storage API 功能
		ws.setDomStorageEnabled(true);

		ws.setRenderPriority(WebSettings.RenderPriority.HIGH);
		// 开启 database storage API 功能
		ws.setDatabaseEnabled(false);

		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public boolean onJsAlert(WebView view, String url, String message,
									 JsResult result) {
				return super.onJsAlert(view, url, message, result);
			}

		});
		return webView;
	}


}

