package com.dts.app.levelreader.entity;

import java.io.Serializable;

/**
 * Word Entity
 * 
 * @author LD
 * @data 2015-05-22
 */
public class Word implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5513812634123223110L;
	/** for 0-6 level **/
	private int level;
	/** for a word content **/
	private String word;
	/** word type ex. adj. **/
	private String intro;

	public Word() {
		this.word = "";
		this.level = -1;
	}

	public Word(String wor) {
		this.word = wor;
		this.level = -1;
	}

	public Word(String wor, int lev) {
		this.word = wor;
		this.level = lev >= 0 && lev <= 6 ? lev : -1;
	}

	public Word(String wor, int lev, String intro) {
		this.word = wor;
		this.level = lev >= 0 && lev <= 6 ? lev : -1;
		this.intro = intro;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level >= 0 && level <= 6 ? level : -1;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
