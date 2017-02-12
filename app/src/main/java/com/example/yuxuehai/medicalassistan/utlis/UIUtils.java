package com.example.yuxuehai.medicalassistan.utlis;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;

import com.example.yuxuehai.medicalassistan.GlobalApplication;

/**
 * 工具类, 专门处理UI相关的逻辑
 * 
 * @author yuxuehai
 * 
 */
public class UIUtils {

	// 获取Application的内容、handler、主线程
	public static Context getContext() {

		return GlobalApplication.getContext();

	}

	public static Handler getHandler() {

		return GlobalApplication.getHandler();

	}

	public static int getMainThreadId() {

		return GlobalApplication.getMyTid();
	}

	// /////////////加载资源文件////////////////////

	/**
	 * 根据id获取字符串
	 */
	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	/**
	 * 根据id获取字符串数组
	 */
	public static String[] getStringArray(int id) {
		return getContext().getResources().getStringArray(id);
	}

	/**
	 * 根据id获取图片
	 */
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	/**
	 * 根据id获取颜色值
	 */
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	/**
	 * 获取颜色状态集合
	 */
	public static ColorStateList getColorStateList(int id) {
		return getContext().getResources().getColorStateList(id);
	}

	/**
	 * 根据id获取尺寸
	 */
	public static int getDimen(int id) {
		return getContext().getResources().getDimensionPixelSize(id);// 返回具体像素
	}

	////////////////dip和dp转换///////////////////
	/**
	 * dp转px
	 */
	public static int dip2px(float dp) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return (int) (density * dp + 0.5);
	}

	/**
	 * px转dp
	 */
	public static float px2dip(float px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}
	////////////////加载布局///////////////////
	/**
	 * 加载布局文件
	 */
	public static View inflate(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}
	////////////////判断是否运行在主线程///////////////////
	/**
	 * 判断当前是否运行在主线程
	 * 
	 * @return
	 */
	public static boolean isRunOnUiThread() {
		//获得当前线程的ID,如果当前线程的ID与主线程ID相同，那么当前就是主线程
		
		return getMainThreadId() == android.os.Process.myTid();
	}
	
	/**
	 * 保证当前的操作运行在UI主线程
	 * 
	 * @param runnable
	 */
	public static void runOnUiThread(Runnable runnable) {
		if (isRunOnUiThread()) {
			//已经是主线程，就直接运行
			runnable.run();
		} else {
			//如果是子线程，则用handler的post去让其运行在主线程
			getHandler().post(runnable);
		}
	}
}
