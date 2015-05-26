package com.dts.app.levelreader.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.adapter.ListArticleAdapter;
import com.dts.app.levelreader.application.UserApplication;
import com.dts.app.levelreader.entity.Article;
import com.dts.app.levelreader.entity.Word;
import com.dts.app.levelreader.utils.WordUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
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
 * 
 *       TODO load data & display list of article
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

		/** initialize the adapter **/
		if (adapter == null) {
			adapter = new ListArticleAdapter(this, articles);
			mListView.setAdapter(adapter);
		} else {
			adapter.notifyDataSetChanged();
		}
		initListeners();
	}

	/**
	 * @TODO load data first
	 */
	private void loadData() {
		new MyPrepareTask().execute();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		/** activity turn **/
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, ReadActivity.class);
		/** use bundle to transfer an article info **/
		Bundle bundle = new Bundle();
		bundle.putSerializable("article", articles.get(arg2));
		intent.putExtras(bundle);
		startActivity(intent);
	}

	class MyPrepareTask extends AsyncTask<Integer, Integer, String> {

		@Override
		protected String doInBackground(Integer... params) {
			final Resources res = MainActivity.this.getResources();
			try {
				UserApplication.initStandardLevel(res);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			List<Word> words = new ArrayList<Word>();
			words.add(new Word("beast", WordUtil.getWordLevel("beast"),
					"n.  野兽"));
			words.add(new Word("census", WordUtil.getWordLevel("census"),
					"n.  统计数字"));
			words.add(new Word("acre", WordUtil.getWordLevel("acre"), "n.  英亩"));
			words.add(new Word("content", WordUtil.getWordLevel("content"),
					"adj. 满足的"));
			/** data for test **/
			Article article = new Article();
			article.setTitle("Spare that spider");
			article.setChinese("不要伤害蜘蛛");
			article.setContent("Why, you may wonder, should spiders be our friends? Because they destroy so many insects, and insects include some of the greatest enemies of the human race. Insects would make it impossible for us to live in the world; they would devour all our crops and kill our flocks and herds, if it were not for the protection we get from insect-eating animals. We owe a lot to the birds and beasts who eat insects but all of them put together kill only a fraction of the number destroyed by spiders. Moreover, unlike some of the other insect eaters, spiders never do the harm to us or our belongings."
					+ "\n Spiders are not insects, as many people think, nor even nearly related to them. One can tell the difference almost at a glance, for a spider always has eight legs and insect never more than six."
					+ "\n How many spiders are engaged in this work no our behalf? One authority on spiders made a census of the spiders in grass field in the south of England, and he estimated that there were more than 2,250,000 in one acre; that is something like 6,000,000 spiders of different kinds on a football pitch. Spiders are busy for at least half the year in killing insects. It is impossible to make more than the wildest guess at how many they kill, but they are hungry creatures, not content with only three meals a day. It has been estimated that the weight of all the insects destroyed by spiders in Britain in one year would be greater than the total weight of all the human beings in the country.");
			article.setTranslations("\n你可能会觉得奇怪， 蜘蛛怎么会是我们的朋友呢？因为它们能消灭那么多的昆虫，其中包括一些人类的大敌，要不是人类受一些食虫动物的保护，昆虫就会使我们无法在地球上生活下去，昆虫会吞食我们的全部庄稼，杀死我们的成群的牛羊。我们要十分感谢那些吃昆虫的鸟和兽，然而把它们所杀死的昆虫全部加在一起也只相当于蜘蛛所消灭的一小部分。此外，蜘蛛不同于其他食虫动物，它们丝毫不危害我们和我们的财物。"
					+ "\n许多人认为蜘蛛是昆虫，但它们不是昆虫，甚至与昆虫毫无关系。人们几乎一眼就能看出二者的差异，因为蜘蛛都是8条腿，而昆虫的腿从不超过6条。"
					+ "\n有多少蜘蛛在为我们效力呢？一位研究蜘蛛的权威对英国南部一块草坪上的蜘蛛作了一次调查。他估计每英亩草坪里有225万多只蜘蛛。这就是说，在一个足球场上约有600万只不同种类的蜘蛛。蜘蛛至少有半年在忙于吃昆虫。它们一年中消灭了多少昆虫，我们简直无法猜测，它们是吃不饱的动物，不满意一日三餐。据估计，在英国蜘蛛一年里所消灭昆虫的重量超过这个国家人口的总重量。");
			article.setWords(words);

			articles.add(article);
			initView();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
	}
}
