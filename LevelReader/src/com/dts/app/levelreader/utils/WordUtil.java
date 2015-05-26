package com.dts.app.levelreader.utils;

import java.util.ArrayList;
import java.util.List;

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

	final static String textStr1 = "<font color=\"";
	final static String textStr2 = "\">";
	final static String textStr3 = "</font> ";

	/**
	 * String2Words
	 * 
	 * @param content
	 * @return
	 */
	public static List<Word> string2Words(String content) {
		List<Word> words = new ArrayList<Word>();
		String[] wor = content.split("\\s+");
		for (String wo : wor) {
			switch (hasSymbol(wo)) {
			case -1:
				words.add(new Word(wo));
				break;
			case SYM_QYINHAO:
				words.add(new Word("'"));
				words.add(new Word(wo.replaceAll("'", "")));
				break;
			case SYM_HYINHAO:
				words.add(new Word(wo.replaceAll("'", "")));
				words.add(new Word("'"));
				break;
			case SYM_JUHAO:
				words.add(new Word(wo.replaceAll("\\.", "")));
				words.add(new Word("."));
				break;
			case SYM_DOUHAO:
				words.add(new Word(wo.replaceAll(",", "")));
				words.add(new Word(","));
				break;
			case SYM_WENHAO:
				words.add(new Word(wo.replaceAll("\\?", "")));
				words.add(new Word("?"));
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
			word = word.replaceAll(".", "");
			return SYM_JUHAO;
		} else if (word.contains(",")) {
			word = word.replaceAll(",", "");
			return SYM_DOUHAO;
		} else if (word.contains("?")) {
			word = word.replaceAll("?", "");
			return SYM_WENHAO;
		} else
			return -1;
	}

	public static void main(String[] args) {
		List<Word> words = string2Words("Several cases have been reported in Russia recently of people who can detect colours with their fingers, and even see through solid and walls. One case concerns and eleven-year-old schoolgirl, Vera Petrova, who has normal vision but who can also perceive things with different parts of her skin, and through solid walls. This ability was first noticed by her father. One day she came into his office and happened to put her hands on the door of a locked safe. Suddenly she asked her father why he kept so many old newspapers locked away there, and even described the way they were done up in bundles. Vera's curious talent was brought to the notice of a scientific research institute in the town of Ulyanovsk, near where she lives, and in April she was given a series of tests by a special commission of the Ministry of Health of the Russian Federal Republic. During these tests she was able to read a newspaper through an opaque screen and, stranger still, by moving her elbow over a child's game of Lotto she was able to describe the figures and colours printed on it; and, in another instance, wearing stockings and slippers, to make out with her foot the outlines and colours of a picture hidden under a carpet. Other experiments showed that her knees and shoulders had a similar sensitivity. During all these tests Vera was blindfold; and, indeed, except when blindfold she lacked the ability to perceive things with her skin. It was also found that although she could perceive things with her fingers this ability ceased the moment her hands were wet.");
		for (Word w : words) {
			System.out.println(w.getWord());
		}

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
			result = textStr1 + "#ffffff" + textStr2 + word.getWord()
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
	public final static void initWordLevel(Word word) {

	}
}
