package com.exposittest.downloader;

import java.util.ArrayList;

public class Checker {
	private String[] args;
	private String l = new String();
	private String p = new String();
	private String n = new String();
	private String f = new String();
	private int t;
	private boolean flagT = false;
	private boolean flagL = false;
	private boolean flagP = false;
	private boolean flagN = false;
	private boolean flagF = false;

	Checker(String[] arguments) {
		args = arguments;
	}

	// check arguments 
	public boolean check() {
		if ((args.length > 0) && (args.length <= 8)) {
			for (int i = 0; i < args.length - 1; i++) {

				if (args[i].equals("-l")) {
					if ((!flagL) && (!flagF)) {
						flagL = true;
						l = args[i + 1];
					} else {
						return false;
					}
				}

				if (args[i].equals("-p")) {

					if (!flagP) {
						flagP = true;
						p = args[i + 1];

					} else {
						return false;
					}
				}

				if (args[i].equals("-n")) {
					if ((!flagN) && (!flagF)) {
						flagN = true;
						n = args[i + 1];
					} else {
						return false;
					}
				}

				if (args[i].equals("-f")) {
					if ((!flagF) && (!flagN) && (!flagL)) {
						flagF = true;
						f = args[i + 1];
					} else {
						return false;
					}
				}
				if (args[i].equals("-t")) {
					if (!flagT)  {
						flagT = true;
						try {
					        t = Integer.parseInt(args[i + 1]);
					        if((t<1)||(t>20)){
					        	return false;
					        }
					    } catch (NumberFormatException ex) {  
					        System.err.println("-t is not a number"); 
					        return false;
					    } 
					} else {
						return false;
					}
				}
			}
		}else{
			return false;
		}

		if ((((!flagL) || (!flagP) || (!flagN)) && (!flagF)) || (((!flagF) || (!flagP)) && ((!flagL) || (!flagP))||((!flagF)&&(flagT)))) {
			return false;
		}
		

		return true;
	}

	@SuppressWarnings("static-access")
	public ArrayList<FileInfo> validate()  {
		ArrayList<FileInfo> files = new ArrayList<FileInfo>();
		if (check()) {
			
			if(flagT){// thread used
				ThreadDownloader downloader = new ThreadDownloader();
				downloader.threadsCount = t;
			}

			if (!flagF) {// file not used
				files.add(new FileInfo(l, p, n));
				return files;

			} else {// file used

				FileInfoGetter fileInfoGetter = new FileInfoGetter(f, p);
				return fileInfoGetter.get();

			}
		} else {
			return null;
		}
	}

}
