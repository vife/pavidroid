package de.ksvdurlach.rssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.ksvdurlach.main.KSVMainActivity;

import android.os.Handler;

public class KSVRSSFeed extends Thread {

	public ArrayList<KSVFeedEntry> feedItems;

	private String feedUrl = "http://ksvdurlach.de/news.xml";

	private Handler handler;

	public KSVRSSFeed(Handler handler) {
		this.handler = handler;
	}

	public void run() {
		InputStream is = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			URL url = new URL(feedUrl);
			is = url.openConnection().getInputStream();

			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			feedItems = new ArrayList<KSVFeedEntry>();
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					feedItems.add(
						readItem(eElement)
					);
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.handler.sendEmptyMessage(0);
	}

	private KSVFeedEntry readItem(Element eElement) {
		return new KSVFeedEntry(
			eElement.getElementsByTagName("title").item(0).getTextContent(),
			eElement.getElementsByTagName("description").item(0).getTextContent(),
			eElement.getElementsByTagName("link").item(0).getTextContent(),
			eElement.getElementsByTagName("pubDate").item(0).getTextContent()
		);
	}
}
