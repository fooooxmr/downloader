package com.exposittest.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FileDownloader {
	private static final int BUFFER_SIZE = 8192;
	private static int downloadedFilesCount = 0;
	private static int notDownloadedFilesCount = 0;
	private static long totalBytesDownloaded = 0;
	private FileInfo downloadedFile;
	private static ArrayList<FileInfo> notDownloadedFiles = new ArrayList<FileInfo>();

	public FileDownloader(FileInfo fileInfo) {
		downloadedFile = new FileInfo(fileInfo);
	}

	public FileDownloader() {
	}

	public static void printStatistic() {
		System.out.println("Download statistic:");
		System.out.println("Download files - " + downloadedFilesCount);
		System.out.println("Not download files - " + notDownloadedFilesCount);
		System.out.println("Total bytes download - " + totalBytesDownloaded);
		if(notDownloadedFilesCount>0){
			System.out.println("\n-----------------------------\nNot download:");
			for (FileInfo fileInfo : notDownloadedFiles) {
				System.out.println(fileInfo.fileUrl);
			}
		}
	}

	@SuppressWarnings("unused")
	public void downloadFile()  {
		try {
		URL url = new URL(downloadedFile.fileUrl);
		
			System.out.println("Trying to download - " + downloadedFile.fileUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			int responseCode = connection.getResponseCode();
			String responseMessage = connection.getResponseMessage();

			// check response code
			if (responseCode == HttpURLConnection.HTTP_OK) {
				String contentType = connection.getContentType();
				int contentLength = connection.getContentLength();

				// opens input stream
				InputStream inputStream = connection.getInputStream();
				String saveFilePath = downloadedFile.savePath + File.separator + downloadedFile.fileName;

				// opens output stream
				File outputFile = new File(downloadedFile.savePath);
				if (!outputFile.exists()) {
					outputFile.mkdirs();
				}
				FileOutputStream outputStream = new FileOutputStream(saveFilePath);

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				// closing streams
				outputStream.close();
				inputStream.close();
				System.out.println("\n-----------------------------");
				System.out.println(" Download succses. File save as '" + downloadedFile.savePath +"/" +downloadedFile.fileName 
						+ "'\n----------------------------");
				downloadedFilesCount++;
				totalBytesDownloaded += contentLength;
			} else {
				System.out.println("\n-----------------------------");
				System.out.println("Download failed. Error code: " + responseCode + " " + responseMessage);
				System.out.println("\n-----------------------------");
				notDownloadedFilesCount++;
				notDownloadedFiles.add(downloadedFile);
			}
			// close HTTP connection
			connection.disconnect();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			System.out.println(" Download failed\n-----------------------------");
			notDownloadedFilesCount++;
			notDownloadedFiles.add(downloadedFile);

		}

	}

}
