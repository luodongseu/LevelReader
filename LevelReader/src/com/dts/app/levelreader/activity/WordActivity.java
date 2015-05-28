package com.dts.app.levelreader.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.adapter.ListWordAdapter;
import android.widget.ListView;
import java.util.List;
import com.dts.app.levelreader.entity.Word;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Toast;

/**
 * view new expression and words in the activity
 * 
 * @author Administrator
 * 
 */
public class WordActivity extends Activity implements OnItemClickListener,
		OnClickListener {
	/** word list adapter **/
	ListWordAdapter adapter;
	/** list view **/
	private ListView mListView;
	/** data **/
	private List<Word> words;
	/** top bar text **/
	private TextView bar;
	/** top bar back **/
	private Button back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_words);
		loadData();
		initView();
		initListeners();
	}

	/**
	 * TODO show level of the word
	 */
	private void initListeners() {
		mListView.setOnItemClickListener(this);
	}

	/**
	 * TODO initialize view
	 */
	private void initView() {
		mListView = (ListView) findViewById(R.id.list_words);
		/** back to main activity **/
		back = (Button) findViewById(R.id.back);
		back.setOnClickListener(this);
		back.setVisibility(View.VISIBLE);

		bar = (TextView) findViewById(R.id.bar);
		bar.setText("生词表");

		if (adapter == null) {
			adapter = new ListWordAdapter(this, words);
			mListView.setAdapter(adapter);
			return;
		}
		adapter.notifyDataSetChanged();
	}

	/**
	 * TODO load data from intent
	 */
	@SuppressWarnings("unchecked")
	private void loadData() {
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		if (bundle.containsKey("words")) {
			words = (List<Word>) bundle.get("words");
			return;
		}
		Toast.makeText(this, "No words", Toast.LENGTH_SHORT).show();
		finish();
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		AlertDialog.Builder builder = new Builder(WordActivity.this);
		builder.setTitle("单词等级");
		builder.setMessage(String.valueOf(words.get(arg2).getLevel()));
		builder.setPositiveButton("确认", new AlertDialog.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}

	@Override
	public void onClick(View arg0) {
		finish();
	}
}
