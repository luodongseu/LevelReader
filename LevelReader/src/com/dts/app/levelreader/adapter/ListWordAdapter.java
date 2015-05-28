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
import com.dts.app.levelreader.entity.Word;

public class ListWordAdapter extends BaseAdapter {

	/** adapter need a context for view **/
	private Context mContext;
	/** item data **/
	private List<Word> words;

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param arts
	 */
	public ListWordAdapter(Context context, List<Word> words) {
		this.mContext = context;
		this.words = words;
	}

	@Override
	public int getCount() {
		if (words != null)
			return words.size();
		else {
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return words.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		/** get view **/
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_words, null);
			viewHolder = new ViewHolder();
			viewHolder.word = (TextView) convertView.findViewById(R.id.word);
			viewHolder.intro = (TextView) convertView.findViewById(R.id.intro);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		/** set view data **/
		viewHolder.word.setText(words.get(arg0).getWord());
		/** set view data **/
		viewHolder.intro.setText(words.get(arg0).getIntro());

		return convertView;
	}

	/***
	 * A view holder for getView
	 * 
	 * @author Administrator
	 * 
	 */
	static class ViewHolder {
		public TextView word;
		public TextView intro;
	}
}