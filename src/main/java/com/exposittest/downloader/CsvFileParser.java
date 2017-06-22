package com.exposittest.downloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvFileParser implements FileParser {

	private String fileName;

	public CsvFileParser(String name) {
		fileName = name;
	}

	public ArrayList<String[]> parse() {

		ArrayList<String[]> filesPath = new ArrayList<String[]>();
		try {

			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			while (reader.ready()) {
				filesPath.add(reader.readLine().split(","));
			}
			reader.close();
			return filesPath;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
