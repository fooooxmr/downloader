package com.exposittest.downloader;


public class AnotherThread extends Thread {

	public static FileInfoStack fileInfoStack;

	public static int aliveThreads = 0;
	public static long startTime = System.currentTimeMillis();

	AnotherThread(FileInfoStack stack) {
		fileInfoStack = stack;
		aliveThreads++;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		// download file from stack
		FileInfo fileInfo = new FileInfo();
		FileDownloader downloader = new FileDownloader();
		try {
			while ((fileInfo = FileInfoStack.pop()) != null) {
				downloader = new FileDownloader(fileInfo);
				downloader.downloadFile();
			}
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
		if (isAlive()) {
			aliveThreads--;
		}
		if (aliveThreads == 0) {
			downloader.printStatistic();
			long timeSpent = System.currentTimeMillis() - startTime;
			System.out.println("Total time: " + timeSpent / 1000 + " seconds");
		}
	}

}
