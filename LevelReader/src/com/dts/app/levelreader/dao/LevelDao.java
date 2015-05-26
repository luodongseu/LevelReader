package com.dts.app.levelreader.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.util.EncodingUtils;
import android.content.res.Resources;

import com.dts.app.levelreader.R;
import com.dts.app.levelreader.entity.Word;

/**
 * load file
 * 
 * @author LD
 * @date 2015-05-25
 */
public class LevelDao {

	/**
	 * read from raw/txt
	 * 
	 * @param myFile
	 * @return
	 */
	public List<Word> getStandardWords(Resources res) throws IOException {
		List<Word> words = new ArrayList<Word>();
		String result = "";
		String resStrings[] = null;
		try {
			InputStream in = res.openRawResource(R.raw.nce4_words);
			int lenght = in.available();
			byte[] buffer = new byte[lenght];
			in.read(buffer);
			result = EncodingUtils.getString(buffer, "UTF-8");
			resStrings = result.split("\n");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String tmp : resStrings) {
			String wors[] = tmp.split(" ");
			int level = -1;
			if (wors != null && wors.length == 2) {
				try {
					level = Integer.valueOf(wors[wors.length - 1]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < wors.length - 1; i++) {
					words.add(new Word(wors[i], level));
				}
			}
		}
		return words;
	}

	public static void main(String[] args) {
		new LevelDao();
	}
}
