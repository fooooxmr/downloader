package com.exposittest.downloader;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;


public class FileInfoStack {
	final static LinkedBlockingQueue<FileInfo> fileinfoStack = new LinkedBlockingQueue<FileInfo>();
	
	
	FileInfoStack(ArrayList<FileInfo> filesInfo)   {
		for (FileInfo fileInfo : filesInfo) {
			try {
				fileinfoStack.put(fileInfo);
			} catch (InterruptedException ex) {

				System.out.println(ex.getMessage());
			}
		}
	}
	

	public static  FileInfo pop() throws InterruptedException {
		FileInfo fileInfo = new FileInfo();
		if(!fileinfoStack.isEmpty())  {
		fileInfo = fileinfoStack.take();
			return fileInfo;
		}else{
			return null;
		}
	}

}
