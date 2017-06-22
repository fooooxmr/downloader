package com.exposittest.downloader;

import java.io.IOException;

/**
 * Program, that downloads a file from a URL
 * 
 * @author foooox
 *
 */

public class App {

	/**
	 * Downloads a file from a URL
	 * 
	 * @param l
	 *            HTTP URL of the file to be download
	 * @param s
	 *            path to save the file
	 * @param n
	 *            name of the file to save
	 * @param t
	 *            threads count
	 * @throws IOException
	 */

	public static void main(String[] args)  {

		ThreadDownloader thredDownloader;
		try {
			thredDownloader = new ThreadDownloader(args);
			thredDownloader.download();

		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}

	}
}
