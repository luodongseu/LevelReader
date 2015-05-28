package com.dts.app.levelreader.activity;

import java.io.Serializable;
import java.util.List;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.entity.Article;
import com.dts.app.levelreader.entity.Word;
import com.dts.app.levelreader.utils.WordUtil;
import com.dts.app.levelreader.view.SliderView;
import com.dts.app.levelreader.view.SliderView.OnSliderChangedListener;
import com.dts.app.levelreader.view.TextSpanned;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/***
 * LevelReader
 * 
 * @author LD
 * @date 2015-5-20
 */
public class ReadActivity extends Activity implements OnClickListener {

	/** final variables **/
	private static final int SHOW_CHINESE = 1;
	private static final int SHOW_HIGH = 2;
	private static final int SHOW_TRANS = 3;

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
	/** button to see words **/
	private Button buttonSeeWords;
	/** button to see chinese for article **/
	private Button buttonSeeTrans;
	/** top bar text **/
	private TextView bar;
	/** top bar back **/
	private Button back;

	@SuppressLint("HandlerLeak")
	public Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SHOW_CHINESE:
				showTranslation();
				break;
			case SHOW_HIGH:
				prepareView(msg.arg1);
				break;
			case SHOW_TRANS:
				showTranslation();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras() == null ? new Bundle() : intent
				.getExtras();
		article = (Article) bundle.get("article") == null ? new Article(
				"No Article!", " ") : (Article) (bundle.get("article"));

		initView();
		initListeners();
	}

	/**
	 * TODO show translation of aritcle
	 */
	protected void showTranslation() {
		AlertDialog.Builder builder = new Builder(ReadActivity.this);
		builder.setMessage(article.getTranslations());
		builder.setTitle(article.getChinese());
		builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	/**
	 * initialize views
	 */
	private void initView() {
		title = (TextView) findViewById(R.id.art_title);
		content = (TextView) findViewById(R.id.art_content);
		sliderView = (SliderView) findViewById(R.id.slider_bar);
		sliderDialog = (TextView) findViewById(R.id.slider_dialog);
		buttonSeeWords = (Button) findViewById(R.id.btn_view_words);
		buttonSeeTrans = (Button) findViewById(R.id.trans);
		buttonSeeTrans.setVisibility(View.VISIBLE);
		/** point out the dialog for SliderView **/
		sliderView.setTextView(sliderDialog);
		bar = (TextView) findViewById(R.id.bar);
		bar.setText("阅读文章");
		/** back to main activity **/
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		back.setVisibility(View.VISIBLE);
		/** prepare for first time **/
		prepareView(-1);
	}

	/**
	 * TODO initialize listeners
	 */
	private void initListeners() {
		title.setOnClickListener(this);
		buttonSeeWords.setOnClickListener(this);
		buttonSeeTrans.setOnClickListener(this);

		sliderView.setOnSliderChangedListener(new OnSliderChangedListener() {
			@Override
			public void onSliderChangedListener(String s) {
				int level = -1;
				try {
					level = Integer.parseInt(s);
					if (level < 0 || level > 6)
						return;
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				}
				Message msg = new Message();
				msg.what = SHOW_HIGH;
				msg.arg1 = level;
				handler.sendMessage(msg);
			}
		});
	}

	/***
	 * on click Listener
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_view_words:
			Intent intent = new Intent();
			intent.setClass(this, WordActivity.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("words", (Serializable) article.getWords());
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.art_title:
			handler.sendEmptyMessage(SHOW_CHINESE);
			break;
		case R.id.trans:
			handler.sendEmptyMessage(SHOW_TRANS);
			break;
		case R.id.back:
			finish();
			break;
		}
	}

	/**
	 * @param level
	 * 
	 *            TODO prepare view from data
	 */
	public void prepareView(int level) {
		title.setText(article.getTitle());

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("  ");
		List<Word> words = WordUtil.string2Words(article.getContent(), level);
		for (Word w : words) {
			stringBuffer.append(WordUtil.word2Html(w));
		}
		TextSpanned textSpan = new TextSpanned();
		textSpan.setContent(stringBuffer.toString());
		content.setText(textSpan.getFinalText());
	}
}
