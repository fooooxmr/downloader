package com.exposittest.downloader;

import java.util.ArrayList;

public class ThreadDownloader {

	ArrayList<FileInfo> filesInfo;
	public static int threadsCount = 1;

	public ThreadDownloader(String[] args)  {
		ArgsParser argsParser = new ArgsParser(args);
		filesInfo = new ArrayList<FileInfo>(argsParser.parse());
	}

	public ThreadDownloader() {
	}
// download files with another thread
	public void download() throws InterruptedException {
		FileInfoStack fileInfoStack = new FileInfoStack(filesInfo);
		for (int i = 0; i < threadsCount; i++) {
			AnotherThread anotherThread = new AnotherThread(fileInfoStack);
			anotherThread.start();
		}
	}
}
