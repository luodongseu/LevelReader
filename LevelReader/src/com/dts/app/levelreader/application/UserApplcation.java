package com.dts.app.levelreader.application;

import android.app.Application;

/***
 * User Application
 * @TODO for data save and select
 *
 * @author LD
 * @date 2015-05-21
 */
public class UserApplcation extends Application {

	/**application instance**/
	public static UserApplcation userApplcationInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		userApplcationInstance = this;
	}

	/**for other activity use**/
	public static UserApplcation getInstance() {
		return userApplcationInstance;
	}

}
