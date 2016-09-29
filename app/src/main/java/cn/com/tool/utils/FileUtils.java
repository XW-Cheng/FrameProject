package cn.com.tool.utils;/*
 * @创建者     Administrator
 * @创建时间   2016/9/29 9:56
 * @描述       关于文件一些工具类
 *
 * @更新者     $Author$ 
 * @更新时间   $Date$
 * @更新描述
 */

import android.os.Environment;

import java.io.File;

public class FileUtils {
	/**
	 * 创建文件夹
	 *
	 * @return 文件夹绝对路径
	 */
	public static String createSDCardDir(String fileName) {
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			// 创建一个文件夹对象，赋值为外部存储器的目录
			File sdcardDir = Environment.getExternalStorageDirectory();
			// 得到一个路径，内容是sdcard的文件夹路径和名字
			String path = sdcardDir.getPath() + File.separator + fileName;
			File path1 = new File(path);
			if (!path1.exists()) {
				// 若不存在，创建目录，可以在应用启动的时候创建
				path1.mkdirs();
			}
			return path1.getAbsolutePath();
		} else {
			File mFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + File.separator + fileName);
			if (!mFile.exists()) {
				mFile.mkdir();
			}
			return mFile.getAbsolutePath();
		}
	}
}
