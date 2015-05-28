package com.dts.app.levelreader.utils;

import java.util.ArrayList;
import java.util.List;

import com.dts.app.levelreader.application.UserApplication;
import com.dts.app.levelreader.entity.Word;

/**
 * Handle word and String
 * 
 * @author LD
 * @date 2015-05-23
 */
public class WordUtil {
	public static final int SYM_QYINHAO = 0;
	public static final int SYM_HYINHAO = 1;
	public static final int SYM_JUHAO = 2;
	public static final int SYM_DOUHAO = 3;
	public static final int SYM_WENHAO = 4;
	public static final int SYM_FENHAO = 5;
	final static String textStr1 = "<font color=\"";
	final static String textStr2 = "\">";
	final static String textStr3 = "</font> ";

	/**
	 * String2Words
	 * 
	 * @param content
	 * @return
	 */
	public static List<Word> string2Words(String content, int level) {
		List<Word> words = new ArrayList<Word>();
		String[] wor = content.split("\\s+");
		for (String wo : wor) {
			switch (hasSymbol(wo)) {
			case -1:
				if (wo.equals("\n")) {
					words.add(initWordAndLevel("\n\n	", level));
				} else
					words.add(initWordAndLevel(wo, level));
				break;
			case SYM_QYINHAO:
				words.add(new Word("'"));
				wo = wo.replaceAll("'", "");
				words.add(initWordAndLevel(wo, level));
				break;
			case SYM_HYINHAO:
				wo = wo.replaceAll("'", "");
				words.add(initWordAndLevel(wo, level));
				words.add(new Word("'"));
				break;
			case SYM_JUHAO:
				wo = wo.replaceAll("\\.", "");
				words.add(initWordAndLevel(wo, level));
				words.add(new Word("."));
				break;
			case SYM_DOUHAO:
				wo = wo.replaceAll(",", "");
				words.add(initWordAndLevel(wo, level));
				words.add(new Word(","));
				break;
			case SYM_WENHAO:
				wo = wo.replaceAll("\\?", "");
				words.add(initWordAndLevel(wo, level));
				words.add(new Word("?"));
				break;
			case SYM_FENHAO:
				wo = wo.replaceAll(";", "");
				words.add(initWordAndLevel(wo, level));
				words.add(new Word(";"));
				break;
			}
		}
		return words;
	}

	/**
	 * @return symbol type
	 * @param word
	 * @return
	 */
	public static int hasSymbol(String word) {
		if (word.startsWith("'")) {
			word = word.replaceAll("'", "");
			return SYM_QYINHAO;
		} else if (word.contains("'")) {
			word = word.replaceAll("'", "");
			return SYM_HYINHAO;
		} else if (word.contains(".")) {
			word = word.replaceAll("\\.", "");
			return SYM_JUHAO;
		} else if (word.contains(",")) {
			word = word.replaceAll(",", "");
			return SYM_DOUHAO;
		} else if (word.contains("?")) {
			word = word.replaceAll("\\?", "");
			return SYM_WENHAO;
		} else if (word.contains(";")) {
			word = word.replaceAll(";", "");
			return SYM_FENHAO;
		} else
			return -1;
	}

	/**
	 * add color to html
	 * 
	 * @param word
	 * @return
	 */
	public static String word2Html(Word word) {
		String result = "";
		switch (word.getLevel()) {
		case 0:
			result = textStr1 + "#8470FF" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 1:
			result = textStr1 + "#ff0000" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 2:
			result = textStr1 + "#eeaeee" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 3:
			result = textStr1 + "#EEB422" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 4:
			result = textStr1 + "#D15FEE" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 5:
			result = textStr1 + "#BCEE68" + textStr2 + word.getWord()
					+ textStr3;
			break;
		case 6:
			result = textStr1 + "#8B5A00" + textStr2 + word.getWord()
					+ textStr3;
			break;
		default:
			result = textStr1 + "#3f3f3f" + textStr2 + word.getWord()
					+ textStr3;
			break;
		}
		return result;
	}

	/**
	 * initialize word level
	 * 
	 * @param word
	 */
	public final static Word initWordAndLevel(String word, int level) {
		List<Word> standard = UserApplication.getStandardLevel();
		for (Word wor : standard) {
			if (wor.getWord().equals(word) && wor.getLevel() <= level)
				return new Word(word, wor.getLevel());
		}
		return new Word(word, -1);
	}

	/**
	 * TODO get word level
	 */
	public final static int getWordLevel(String word) {
		List<Word> standard = UserApplication.getStandardLevel();
		for (Word wor : standard) {
			if (wor.getWord().equals(word))
				return wor.getLevel();
		}
		return -1;
	}
}
