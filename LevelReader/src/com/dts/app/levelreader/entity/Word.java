package com.dts.app.levelreader.entity;

/**
 * Word Entity
 * 
 * @author LD
 * @data 2015-05-22
 */
public class Word {
	/** for 0-6 level **/
	private int level;
	/** for a word content **/
	private String word;
	/** word type ex. adj. **/
	private String type;
	/** word Chinese translation **/
	private String translation;

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

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type + ".";
	}
}
