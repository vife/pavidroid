package de.ksvdurlach.rssfeed;

import de.ksvdurlach.main.KSVMainActivity;
import de.ksvdurlach.main.R;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class KSVRSSFeedHandler extends Handler {

	private int displayWidth;

	private RelativeLayout relativeLayoutSplashScreen;

	private KSVRSSFeed rss;

	private Activity activity;


	public KSVRSSFeedHandler(Activity activity) {
		this.activity = activity;
		this.relativeLayoutSplashScreen = (RelativeLayout) activity.findViewById(R.id.relativeLayoutSplash);
		this.displayWidth = activity.getResources().getDisplayMetrics().widthPixels;
	}

	@Override
	public void handleMessage(Message msg) {

		postDelayed(new Runnable() {
			public void run() {

				TranslateAnimation translateAnim = new TranslateAnimation(0, - displayWidth, 0, 0);
				translateAnim.setDuration(1000);
				relativeLayoutSplashScreen.startAnimation(translateAnim);
				activity.setContentView(R.layout.activity_ksvmain);

				LinearLayout linearLayoutMain = (LinearLayout) activity.findViewById(R.id.linearLayoutMain);
				translateAnim =  new TranslateAnimation(displayWidth, 0, 0, 0);
				translateAnim.setDuration(1000);

				ListView listView = (ListView) activity.findViewById(R.id.ListViewId);

				KSVFeedEntryAdapter adapter = new KSVFeedEntryAdapter(
					activity,
					R.layout.list_item_image_left_text_right,
					rss.feedItems
				);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(adapter);
				linearLayoutMain.startAnimation(translateAnim);
			}
		}, 3500);
		//TODO VF
//		listView.setBackgroundResource(R.drawable.icon);

	}

	public void handleFeed() {
		rss = new KSVRSSFeed(this);
		rss.start();
	}
}
