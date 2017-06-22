package com.exposittest.downloader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileParser implements FileParser{
	
	private String fileName;

	public JsonFileParser(String name) {
		fileName = name;
	}
	
	public ArrayList<String[]> parse(){
		JSONParser parser = new JSONParser();

		ArrayList<String[]> filesPath = new ArrayList<String[]>();

		String[] bufferPath = new String[2];
		try {

			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fileName));

			JSONArray files = (JSONArray) jsonObject.get("filesInfo");

			@SuppressWarnings("rawtypes")
			Iterator i = files.iterator();
			while (i.hasNext()) {
				JSONObject file = (JSONObject) i.next();

				bufferPath[0] = (String) file.get("link");
				bufferPath[1] = (String) file.get("fileName");

				filesPath.add(bufferPath.clone());
			}
			return filesPath;

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}

		return null;
	}

}
