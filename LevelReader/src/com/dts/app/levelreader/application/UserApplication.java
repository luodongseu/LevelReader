package com.dts.app.levelreader.application;

import java.io.IOException;
import java.util.List;

import com.dts.app.levelreader.dao.LevelDao;
import com.dts.app.levelreader.entity.Word;

import android.app.Application;
import android.content.res.Resources;

/***
 * User Application
 * 
 * @TODO for data save and select
 * 
 * @author LD
 * @date 2015-05-21
 */
public class UserApplication extends Application {

	/** application instance **/
	public static UserApplication userApplcationInstance;
	/** Standard Word level list **/
	public static List<Word> standardLevelWords;

	@Override
	public void onCreate() {
		super.onCreate();
		userApplcationInstance = this;
	}

	/** for other activity use **/
	public static UserApplication getInstance() {
		return userApplcationInstance;
	}

	public static void initStandardLevel(Resources res) throws IOException {
		// standardLevelWords = new ArrayList<Word>();
		/** find from database **/
		LevelDao ldao = new LevelDao();
		standardLevelWords = ldao.getStandardWords(res);
	}

	/**
	 * read all word level
	 * 
	 * @return
	 */
	public static List<Word> getStandardLevel() {
		return standardLevelWords;
	}
}
