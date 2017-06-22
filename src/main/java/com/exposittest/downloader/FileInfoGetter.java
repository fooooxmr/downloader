package com.exposittest.downloader;

import java.util.ArrayList;

public class FileInfoGetter {

	private String fileName = new String();
	private String savePath = new String();
	private ArrayList<FileInfo> filesInfo = new ArrayList<FileInfo>();

	FileInfoGetter(String name, String path) {
		fileName = name;
		savePath = path;
	}

	public ArrayList<FileInfo> get() {
		String fileType;
		if ((fileType = getType()) != null) {
			ArrayList<String[]> filesPath = new ArrayList<String[]>();
			FileParser fileParser;

			if (fileType.equalsIgnoreCase(".csv")) {
				fileParser = new CsvFileParser(fileName);

			} else if (fileType.equalsIgnoreCase(".xml")) {
				fileParser = new XmlFileParser(fileName);

			} else if (fileType.equalsIgnoreCase(".json")) {
				fileParser = new JsonFileParser(fileName);

			} else {

				System.out.println("Incorrect filename");
				return null;
			}

			if ((filesPath = fileParser.parse()) != null) {
				for (String[] filePath : filesPath) {
					if(filePath.length==2){
					filesInfo.add(new FileInfo(filePath[0], savePath, filePath[1]));
					}
					else{
						System.out.println("Incorrect file content");
						return null;
					}
				}
				return filesInfo;

			}
		}
		return null;
	}

	private String getType() {

		int index = fileName.lastIndexOf('.');
		return index == -1 ? null : fileName.substring(index);
	}

}
