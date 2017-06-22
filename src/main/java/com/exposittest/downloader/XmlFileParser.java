package com.exposittest.downloader;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlFileParser implements FileParser {

	private String fileName;

	public XmlFileParser(String name) {
		fileName = name;
	}

	public ArrayList<String[]> parse() {
		try {
			ArrayList<String[]> filesPath = new ArrayList<String[]>();

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = (org.w3c.dom.Document) documentBuilder.parse(new File(fileName));

			NodeList nodeList = ((org.w3c.dom.Document) doc).getElementsByTagName("fileInfo");
			String[] bufferPath = new String[2];
			for (int i = 0; i < nodeList.getLength(); i++) {

				Node node = nodeList.item(i);

				org.w3c.dom.Element element = (org.w3c.dom.Element) node;

				bufferPath[0] = ((org.w3c.dom.Element) element).getElementsByTagName("link").item(0).getTextContent();
				bufferPath[1] = ((org.w3c.dom.Element) element).getElementsByTagName("fileName").item(0)
						.getTextContent();

				filesPath.add(bufferPath.clone());

			}
			return filesPath;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

}
