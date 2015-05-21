package com.dts.app.levelreader.entity;

import java.io.Serializable;

/**
 * Article Entity
 * 
 * @author Administrator
 * @date 2015-05-21
 */
public class Article implements Serializable{
	/**
	 * use for bundle
	 */
	private static final long serialVersionUID = -5717896164437806041L;
	/** article title **/
	private String title;
	/** article content **/
	private String content;

	/**
	 * Constructor 1
	 */
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor 2
	 * @param title,content
	 */
	public Article(String title,String content){
		this.title = title;
		this.content= content;
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
}
