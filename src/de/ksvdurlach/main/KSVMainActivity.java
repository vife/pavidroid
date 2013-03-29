package de.ksvdurlach.main;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.RelativeLayout;
import de.ksvdurlach.rssfeed.KSVRSSFeedHandler;

public class KSVMainActivity extends Activity {

	public static Dialog startDialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		KSVRSSFeedHandler handler = new KSVRSSFeedHandler(KSVMainActivity.this);
		handler.handleFeed();
	}
}
