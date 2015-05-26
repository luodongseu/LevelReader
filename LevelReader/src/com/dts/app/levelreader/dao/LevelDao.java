package com.dts.app.levelreader.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.dts.app.levelreader.entity.Word;

public class LevelDao {

	public List<Word> getStandardWords() {
		List<Word> words = new ArrayList<Word>();
		try {
			File file = new File("nce4_words");
			RandomAccessFile afile = new RandomAccessFile(file, "rw");

			String line = afile.readLine();
			System.out.println("line:            " + line);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;
	}

	public static void main(String[] args) {
		new LevelDao();
	}
}
