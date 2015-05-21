package com.dts.app.levelreader.activity;

import java.util.ArrayList;
import java.util.List;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.adapter.ListArticleAdapter;
import com.dts.app.levelreader.entity.Article;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/***
 * LevelReader
 * 
 * @implements OnItemClickListener
 * @author LD
 * @date 2015-5-20
 */
public class MainActivity extends Activity implements OnItemClickListener {

	/** a global listView **/
	private ListView mListView;
	/** global ListView adapter **/
	ListArticleAdapter adapter;
	/** global data List<Article> **/
	private List<Article> articles = new ArrayList<Article>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		loadData();
		initView();
		initListeners();
	}

	/**
	 * initialize listeners
	 */
	private void initListeners() {
		mListView.setOnItemClickListener(this);
	}

	/**
	 * initialize view
	 */
	private void initView() {
		mListView = (ListView) findViewById(R.id.list_article);

		/**initialize the adapter**/
		if (adapter == null) {
			adapter = new ListArticleAdapter(this, articles);
			mListView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * @TODO load data first
	 */
	private void loadData() {
		/***************************************************/
		/**data for test**/
		Article article = new Article();
		article.setContent("This is content!");
		article.setTitle("This is title!");
		articles.add(article);
		/***************************************************/
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		/** activity turn **/
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ReadActivity.class);
		/**use bundle to transfer an article info**/
		Bundle bundle = new Bundle();
		bundle.putSerializable("article", articles.get(arg2));
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
