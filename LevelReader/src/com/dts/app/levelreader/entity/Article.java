package com.dts.app.levelreader.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Article Entity
 * 
 * @author Administrator
 * @date 2015-05-21
 */
public class Article implements Serializable {
	/**
	 * use for bundle
	 */
	private static final long serialVersionUID = -5717896164437806041L;
	/** article title **/
	private String title;
	/** article chinese **/
	private String chinese;
	/** article content **/
	private String content;
	/** article words **/
	private List<Word> words;
	/** translation **/
	private String translations;

	/**
	 * Constructor 1
	 */
	public Article() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor 2
	 * 
	 * @param title
	 *            ,content
	 */
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	public List<Word> getWords() {
		return words;
	}

	public void setWords(List<Word> words) {
		this.words = words;
	}

	public String getTranslations() {
		return translations;
	}

	public void setTranslations(String translations) {
		this.translations = translations;
	}
}
