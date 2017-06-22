package com.exposittest.downloader;

public class FileInfo {
	public String fileUrl;
	public String savePath;
	public String fileName;

	FileInfo(String url, String path, String name) {
		fileUrl = url;
		savePath = path;
		fileName = name;
		
	}
	public FileInfo(FileInfo fileInfo) {
		this(fileInfo.fileUrl, fileInfo.savePath, fileInfo.fileName);
	}

	public FileInfo() {
	}

	public FileInfo(FileInfo[] validate) {
	}

}
