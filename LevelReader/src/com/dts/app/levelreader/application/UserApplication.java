package com.dts.app.levelreader.application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dts.app.levelreader.dao.LevelDao;
import com.dts.app.levelreader.entity.Word;

import android.app.Application;

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

	public static void initStandardLevel() {
		// standardLevelWords = new ArrayList<Word>();
		/** find from database **/
		LevelDao ldao = new LevelDao();
		standardLevelWords = ldao.getStandardWords();
	}

	/**
	 * read all word level
	 * 
	 * @return
	 */
	public static List<Word> getStandardLevel() {
		if (standardLevelWords == null)
			initStandardLevel();

		return standardLevelWords;
	}
}
