package com.dts.app.levelreader.activity;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.entity.Article;
import com.dts.app.levelreader.view.SliderView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/***
 * LevelReader
 * 
 * @author LD
 * @date 2015-5-20
 */
public class ReadActivity extends Activity implements OnClickListener {

	/** a global Article instance **/
	private Article article;
	/** global TextView for article title **/
	private TextView title;
	/** global TextView for article content **/
	private TextView content;
	/** global SliderView for slider bar **/
	private SliderView sliderView;
	/** slider dialog for show which slider bar has selected **/
	private TextView sliderDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras() == null ? new Bundle() : intent
				.getExtras();
		article = bundle.get("article") == null ? new Article("No Article!",
				" ") : (Article) (bundle.get("article"));

		initView();
		initListeners();
	}

	/**
	 * initialize views
	 */
	private void initView() {
		title = (TextView) findViewById(R.id.art_title);
		content = (TextView) findViewById(R.id.art_content);
		sliderView = (SliderView) findViewById(R.id.slider_bar);
		sliderDialog = (TextView) findViewById(R.id.slider_dialog);
		/** point out the dialog for SliderView **/
		sliderView.setTextView(sliderDialog);

		title.setText(article.getTitle());

		String textStr1 = "<font color=\"#ffff00\">abc</font><br>";
		String textStr2 = "<font color=\"#00ff00\">def</font><br>";
		String textStr3 = "<font color=\"#ff00ff\">efg，</font><br>";
		String textStr4 = "<font color=\"#00ffff\">hi</font><br>";
		content.setText(Html
				.fromHtml(textStr1 + textStr2 + textStr3 + textStr4));
		// content.setText(article.getContent());
		// aTextView.setText("adbcidsoiuneviuashfiuasbifbaisfuvaipdbvpsudhvpsudhv9psdh");
	}

	/**
	 * initialize listeners
	 */
	private void initListeners() {

	}

	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		// TODO Auto-generated method stub

	}
}
