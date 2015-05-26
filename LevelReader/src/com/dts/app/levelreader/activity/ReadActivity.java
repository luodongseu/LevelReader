package com.dts.app.levelreader.activity;

import java.util.List;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.application.UserApplication;
import com.dts.app.levelreader.entity.Article;
import com.dts.app.levelreader.entity.Word;
import com.dts.app.levelreader.utils.WordUtil;
import com.dts.app.levelreader.view.SliderView;
import com.dts.app.levelreader.view.TextSpanned;

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
		StringBuffer stringBuffer = new StringBuffer();
		List<Word> words = WordUtil
				.string2Words("Several cases have been reported in Russia recently of people who can detect colours with their fingers, and even see through solid and walls. One case concerns and eleven-year-old schoolgirl, Vera Petrova, who has normal vision but who can also perceive things with different parts of her skin, and through solid walls. This ability was first noticed by her father. One day she came into his office and happened to put her hands on the door of a locked safe. Suddenly she asked her father why he kept so many old newspapers locked away there, and even described the way they were done up in bundles. Vera's curious talent was brought to the notice of a scientific research institute in the town of Ulyanovsk, near where she lives, and in April she was given a series of tests by a special commission of the Ministry of Health of the Russian Federal Republic. During these tests she was able to read a newspaper through an opaque screen and, stranger still, by moving her elbow over a child's game of Lotto she was able to describe the figures and colours printed on it; and, in another instance, wearing stockings and slippers, to make out with her foot the outlines and colours of a picture hidden under a carpet. Other experiments showed that her knees and shoulders had a similar sensitivity. During all these tests Vera was blindfold; and, indeed, except when blindfold she lacked the ability to perceive things with her skin. It was also found that although she could perceive things with her fingers this ability ceased the moment her hands were wet.");
		for (Word w : words) {
			//sSystem.out.println(w.getWord());

			stringBuffer.append(WordUtil.word2Html(w));

		}
		TextSpanned textSpan = new TextSpanned();
		textSpan.setContent(stringBuffer.toString());
		content.setText(textSpan.getFinalText());

		new Thread(new Runnable() {

			@Override
			public void run() {
				UserApplication.getStandardLevel();
			}
		}).start();
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
