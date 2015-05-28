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
import android.widget.TextView;

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
	/** top bar text **/
	private TextView bar;

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
		bar = (TextView) findViewById(R.id.bar);
		bar.setText("文章列表");

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
			prepareData();
			initView();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}

		/**
		 * TODO just for test
		 * 
		 * not finished!
		 */
		private void prepareData() {
			/** data for test **/
			List<Word> words = new ArrayList<Word>();
			words.add(new Word("beast", WordUtil.getWordLevel("beast"),
					"n.  野兽"));
			words.add(new Word("census", WordUtil.getWordLevel("census"),
					"n.  统计数字"));
			words.add(new Word("acre", WordUtil.getWordLevel("acre"), "n.  英亩"));
			words.add(new Word("content", WordUtil.getWordLevel("content"),
					"adj. 满足的"));

			Article article = new Article();
			article.setTitle("Spare that spider");
			article.setChinese("不要伤害蜘蛛");
			article.setContent("Why, you may wonder, should spiders be our friends? Because they destroy so many insects, and insects include some of the greatest enemies of the human race. Insects would make it impossible for us to live in the world; they would devour all our crops and kill our flocks and herds, if it were not for the protection we get from insect-eating animals. We owe a lot to the birds and beasts who eat insects but all of them put together kill only a fraction of the number destroyed by spiders. Moreover, unlike some of the other insect eaters, spiders never do the harm to us or our belongings."
					+ " <br/><br/> Spiders are not insects, as many people think, nor even nearly related to them. One can tell the difference almost at a glance, for a spider always has eight legs and insect never more than six."
					+ " <br/> <br/>How many spiders are engaged in this work no our behalf? One authority on spiders made a census of the spiders in grass field in the south of England, and he estimated that there were more than 2,250,000 in one acre; that is something like 6,000,000 spiders of different kinds on a football pitch. Spiders are busy for at least half the year in killing insects. It is impossible to make more than the wildest guess at how many they kill, but they are hungry creatures, not content with only three meals a day. It has been estimated that the weight of all the insects destroyed by spiders in Britain in one year would be greater than the total weight of all the human beings in the country.");
			article.setTranslations("   你可能会觉得奇怪， 蜘蛛怎么会是我们的朋友呢？因为它们能消灭那么多的昆虫，其中包括一些人类的大敌，要不是人类受一些食虫动物的保护，昆虫就会使我们无法在地球上生活下去，昆虫会吞食我们的全部庄稼，杀死我们的成群的牛羊。我们要十分感谢那些吃昆虫的鸟和兽，然而把它们所杀死的昆虫全部加在一起也只相当于蜘蛛所消灭的一小部分。此外，蜘蛛不同于其他食虫动物，它们丝毫不危害我们和我们的财物。"
					+ "\n\n	    许多人认为蜘蛛是昆虫，但它们不是昆虫，甚至与昆虫毫无关系。人们几乎一眼就能看出二者的差异，因为蜘蛛都是8条腿，而昆虫的腿从不超过6条。"
					+ "\n\n  	有多少蜘蛛在为我们效力呢？一位研究蜘蛛的权威对英国南部一块草坪上的蜘蛛作了一次调查。他估计每英亩草坪里有225万多只蜘蛛。这就是说，在一个足球场上约有600万只不同种类的蜘蛛。蜘蛛至少有半年在忙于吃昆虫。它们一年中消灭了多少昆虫，我们简直无法猜测，它们是吃不饱的动物，不满意一日三餐。据估计，在英国蜘蛛一年里所消灭昆虫的重量超过这个国家人口的总重量。");
			article.setWords(words);

			articles.add(article);

			List<Word> words1 = new ArrayList<Word>();
			words1.add(new Word("fossil man", WordUtil.getWordLevel("fossil"),
					"adj. 化石人"));
			words1.add(new Word("recount", WordUtil.getWordLevel("recount"),
					"v.  叙述"));
			words1.add(new Word("saga", WordUtil.getWordLevel("saga"),
					"n.  英雄故事"));
			words1.add(new Word("legend", WordUtil.getWordLevel("legend"),
					"n.  传说，传奇"));
			words1.add(new Word("migration",
					WordUtil.getWordLevel("migration"), "n.  迁移，移居"));
			words1.add(new Word("anthropologist", WordUtil
					.getWordLevel("anthropologist"), "n.  人类学家"));
			words1.add(new Word("archaeologist", WordUtil
					.getWordLevel("archaeologist"), "n.  考古学家 "));
			words1.add(new Word("ancestor", WordUtil.getWordLevel("ancestor"),
					"n.  祖先"));
			words1.add(new Word("Polynesian", WordUtil
					.getWordLevel("Polynesian"), "adj.波利尼西亚（中太平洋之一群岛）的"));
			words1.add(new Word("Indonesia",
					WordUtil.getWordLevel("Indonesia"), "n.  印度尼西亚"));
			words1.add(new Word("flint", WordUtil.getWordLevel("flint"),
					"n.  燧石"));
			words1.add(new Word("rot", WordUtil.getWordLevel("rot"), "n.  烂掉"));

			Article article1 = new Article();
			article1.setTitle("Finding fossil man");
			article1.setChinese("发现化石人");
			article1.setContent("We can read of things that happened 5,000 years ago in the Near East, where people first learned to write. But there are some parts of the word where even now people cannot write. The only way that they can preserve their history is to recount it as sagas -- legends handed down from one generation of another. These legends are useful because they can tell us something about migrations of people who lived long ago, but none could write down what they did. Anthropologists wondered where the remote ancestors of the Polynesian peoples now living in the Pacific Islands came from. The sagas of these people explain that some of them came from Indonesia about 2,000 years ago. "
					+ "<br/><br/> But the first people who were like ourselves lived so long ago that even their sagas, if they had any, are forgotten. So archaeologists have neither history nor legends to help them to find out where the first 'modern men' came from. "
					+ "<br/><br/> Fortunately, however, ancient men made tools of stone, especially flint, because this is easier to shape than other kinds. They may also have used wood and skins, but these have rotted away. Stone does not decay, and so the tools of long ago have remained when even the bones of the men who made them have disappeared without trace.");
			article1.setTranslations("	我们从书籍中可读到5,000 年前近东发生的事情，那里的人最早学会了写字。但直到现在,世界上有些地方，人们还不会书写。 他们保存历史的唯一办法是将历史当作传说讲述，由讲述人一代接一代地将史实描述为传奇故事口传下来。人类学家过去不清楚如今生活在太平洋诸岛上的波利尼西亚人的祖先来自何方，当地人的传说却告诉人们：其中一部分是约在2,000年前从印度尼西亚迁来的。"
					+ "\n\n   但是，和我们相似的原始人生活的年代太久远了，因此，有关他们的传说既使有如今也失传了。于是，考古学家们既缺乏历史记载，又无口头传说来帮助他们弄清最早的“现代人”是从哪里来的。"
					+ "\n\n   然而， 幸运的是，远古人用石头制作了工具，特别是用燧石，因为燧石较之其他石头更容易成形。他们也可能用过木头和兽皮，但这类东西早已腐烂殆尽。石头是不会腐烂的。因此，尽管制造这些工具的人的骨头早已荡然无存，但远古时代的石头工具却保存了下来。");
			article1.setWords(words1);

			articles.add(article1);

			List<Word> words2 = new ArrayList<Word>();
			words2.add(new Word("Matterhorn", WordUtil
					.getWordLevel("Matterhorn "),
					"n.  马特霍恩峰（阿尔卑斯山之一，在意大利和瑞士边境）"));
			words2.add(new Word("alpinist", WordUtil.getWordLevel("alpinist"),
					"n.  登山运动员"));
			words2.add(new Word("pioneer", WordUtil.getWordLevel("pioneer"),
					"v.  开辟，倡导；\nn.  先锋，开辟者"));
			words2.add(new Word("summit", WordUtil.getWordLevel("summit"),
					"n.  顶峰"));
			words2.add(new Word("attain", WordUtil.getWordLevel("attain"),
					"v.  到达"));
			words2.add(new Word("perilous", WordUtil.getWordLevel("perilous"),
					"adj. 危险的"));
			words2.add(new Word("shudder", WordUtil.getWordLevel("shudder"),
					"v.  不寒而栗"));
			words2.add(new Word("court", WordUtil.getWordLevel("court"),
					"v.  追求"));
			words2.add(new Word("solitary", WordUtil.getWordLevel("solitary"),
					"adj. 唯一的"));
			words2.add(new Word("impoverish", WordUtil
					.getWordLevel("impoverish"), "v.  使贫困"));
			words2.add(new Word("Alpine", WordUtil.getWordLevel("Alpine"),
					"adj. 阿尔卑斯山的"));
			words2.add(new Word("flea-ridden", WordUtil
					.getWordLevel("flea-ridden"), "adj. 布满跳蚤的"));
			words2.add(new Word("coarse", WordUtil.getWordLevel("coarse"),
					"adj. 粗劣的"));
			words2.add(new Word("boast", WordUtil.getWordLevel("boast"),
					"v.  自恃有"));
			words2.add(new Word("parishioner", WordUtil
					.getWordLevel("parishioner"), "n.  教区居民"));
			words2.add(new Word("shepherd", WordUtil.getWordLevel("shepherd"),
					"n.  牧羊人"));
			words2.add(new Word("linen", WordUtil.getWordLevel("linen"),
					"n.  亚麻布床单"));
			words2.add(new Word("the Alps", WordUtil.getWordLevel("Alps"),
					"n.  阿尔卑斯山脉"));

			Article article2 = new Article();
			article2.setTitle("Matterhorn man ");
			article2.setChinese("马特霍恩山区人");
			article2.setContent("		Modern alpinists try to climb mountains by a route which will give them good sport, and the more difficult it is, the more highly it is regarded. In the pioneering days, however, this was not the case at all. The early climbers were looking for the easiest way to the top, because the summit was the prize they sought, especially if it and never been attained before. It is true that during their explorations they often faced difficulties and dangers of the most perilous nature, equipped in a manner with would make a modern climber shudder at the thought, but they did not go out of their way to court such excitement. They had a single aim, a solitary goal -- the top! "
					+ "<br/><br/>	It is hard for us to realize nowadays how difficult it was for the pioneers. Except for one or two places such as Zermatt and Chamonix, which had rapidly become popular, Alpine village tended to be impoverished settlements cut off from civilization by the high mountains. Such inns as there were generally dirty and flea-ridden; the food simply local cheese accompanied by bread often twelve months old, all washed down with coarse wine. Often a valley boasted no inn at all, and climbers found shelter wherever they could -- sometimes with the local priest (who was usually as poor as his parishioners), sometimes with shepherds or cheese-makers. Invariably the background was the same: dirt and poverty, and very uncomfortable. For men accustomed to eating seven-course dinners and sleeping between fine linen sheets at home, the change to the Alps must have very hard indeed. "
					+ "<br/><br/>	WALTER UNSWORTH Matterhorn Man");

			article2.setTranslations("	现代登山运动员总想找一条能够给他们带来运动乐趣的路线来攀登山峰。他们认为， 道路愈艰险愈带劲儿。然而，在登山运动的初期，全然不是这种情况。早期登山者所寻找的是通往山顶的最方便的途径，因为顶峰特别是前人未曾到过的顶峰 -- 才是他们寻求的目标。确实，在探险中他们经常遇到惊心动魄的困难和危险，而他们装备之简陋足以使现代登山者一想起来就胆战心惊。但是，他们并非故意寻求这种刺激，他们只有一个目标，唯一的目标 -- 顶峰！"
					+ "\n\n		我们今天很难想像昔日的登山先驱们是多么艰苦。除了泽曼特和夏蒙尼等一两个很快出了名的地方外，阿尔卑斯山山区的小村几乎全是高山环抱、与世隔绝的穷乡僻壤。那里的小客栈一般都很肮脏，而且跳蚤猖獗。 食物是当地的干酪和通常存放了一年之久的面包，人们就着劣酒吞下这种食物。山谷里常常没有小客栈，登山者只好随遇而安。有时同当地牧师 （他通常和他的教民一样穷）住在一起，有时同牧羊人或制乳酪的人住在一起。无论住在哪儿，情况都一样：肮脏、贫穷，极其不舒适。对于过惯了一顿饭吃7道菜、睡亚麻细布床单的人来说，变换一下生活环境来到阿尔卑斯山山区，那一定是很艰难的。");
			article2.setWords(words2);

			articles.add(article2);
		}
	}
}
