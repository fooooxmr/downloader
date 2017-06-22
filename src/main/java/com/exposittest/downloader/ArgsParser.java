package com.exposittest.downloader;

import java.util.ArrayList;

public class ArgsParser {
	private String[] args;
	
	ArgsParser(String[] arguments){
		args = arguments;
	}
	
	public  ArrayList<FileInfo> parse()  {
		ArrayList<FileInfo> files;
		Checker checker = new Checker(args);
		files = checker.validate();
		if (files != null) {
			return files;
		} else {
			System.out.println("Incorrect arguments");
			System.exit(1);
		}

		return files;
	}

}
