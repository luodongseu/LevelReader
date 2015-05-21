package com.dts.app.levelreader.adapter;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.dts.app.levelreader.R;
import com.dts.app.levelreader.entity.Article;
/**
 * ListView Adapter for articles
 * 
 * @author Administrator
 * @date 2015-05-21
 */
public class ListArticleAdapter extends BaseAdapter {

	/** adapter need a context for view **/
	private Context mContext;
	/** item data **/
	private List<Article> articles ;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param arts
	 */
	public ListArticleAdapter(Context context, List<Article> arts) {
		this.mContext = context;
		this.articles = arts;
	}

	@Override
	public int getCount() {
		if(articles!=null)
			return articles.size();
		else{
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return articles.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@SuppressLint("InflateParams") 
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		/**get view**/
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_article, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.art_title);
			viewHolder.readCount = (TextView) convertView.findViewById(R.id.art_readcount);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/**set view data**/
		viewHolder.title.setText(articles.get(arg0).getTitle());
		viewHolder.readCount.setText("0");
		
		return convertView;
	}
	
	/***
	 * A view holder for getView
	 * @author Administrator
	 *
	 */
	static class ViewHolder{
		public TextView title;
		public TextView readCount;
	}

}
