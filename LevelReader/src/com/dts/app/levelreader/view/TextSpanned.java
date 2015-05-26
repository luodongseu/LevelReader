package com.dts.app.levelreader.view;


import android.text.Html;
import android.text.Spanned;

/***
 * prepare high lighted text
 * 
 * @author LD
 * @date 2015-05-23
 */
public class TextSpanned {

	

	/** content **/
	private String contentString;

	/**
	 * set text content
	 * 
	 * @param con
	 */
	public void setContent(String con) {
		this.contentString = con;
	}

	/**
	 * get text with high lighted
	 * 
	 * @return
	 */
	public Spanned getFinalText() {

		return Html.fromHtml(contentString);
	}


	
	
}
