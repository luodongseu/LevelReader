package com.dts.app.levelreader.utils;

import com.dts.app.levelreader.application.UserApplication;

import android.content.Context;
import android.content.res.Resources;

/**
 * @TODO convert pixel
 * 
 * @author LD
 * @date 2015-05-21
 */
public class PixelUtil {

	/** initialize the context **/
	private static Context mContext = UserApplication.getInstance();

	/**
	 * dp2px.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int dp2px(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	/**
	 * dp2px.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int dp2px(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	/**
	 * px2dp.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int px2dp(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	/**
	 * px2dp.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int px2dp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	/**
	 * sp2px.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int sp2px(float value) {
		Resources r;
		if (mContext == null) {
			r = Resources.getSystem();
		} else {
			r = mContext.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	/**
	 * sp2px.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int sp2px(float value, Context context) {
		Resources r;
		if (context == null) {
			r = Resources.getSystem();
		} else {
			r = context.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	/**
	 * px2sp.
	 * 
	 * @param value
	 *            the value
	 * @return the int
	 */
	public static int px2sp(float value) {
		final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

	/**
	 * px2sp.
	 * 
	 * @param value
	 *            the value
	 * @param context
	 *            the context
	 * @return the int
	 */
	public static int px2sp(float value, Context context) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

}
