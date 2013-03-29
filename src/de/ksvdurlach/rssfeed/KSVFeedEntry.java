package de.ksvdurlach.rssfeed;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@SuppressLint("SimpleDateFormat")
public class KSVFeedEntry {
	public final String title;
	public final String link;
	public final String description;
	public String pubDate = null;

	public KSVFeedEntry(String title, String description, String link, String pubDate) {
		this.title = title;
		this.description = description;
		this.link = link;

		SimpleDateFormat origDateFormat = new SimpleDateFormat(
			"EEE, dd MMM yyyy HH:mm:ss zzzzz",
			Locale.ENGLISH
		);

		SimpleDateFormat newDateFormat = new SimpleDateFormat("EEE, d.M.y");

		try {
			Date date = origDateFormat.parse(pubDate);
	        this.pubDate = newDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
