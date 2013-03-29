package de.ksvdurlach.rssfeed;

import java.util.ArrayList;
import de.ksvdurlach.main.R;
import de.ksvdurlach.main.WebActivity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class KSVFeedEntryAdapter extends ArrayAdapter<KSVFeedEntry> implements OnItemClickListener {

	private ArrayList<KSVFeedEntry> feedItems;
	private Context context;

	public KSVFeedEntryAdapter(Context context, int textViewResourceId, ArrayList<KSVFeedEntry> feedItems) {
		super(context, textViewResourceId, feedItems);
		this.feedItems = feedItems;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(
				Context.LAYOUT_INFLATER_SERVICE
			);
			v = vi.inflate(R.layout.list_item_image_left_text_right, null);
		}
		KSVFeedEntry feedItem = feedItems.get(position);
		if (feedItem != null) {
			TextView title = (TextView) v.findViewById(R.id.title);
			TextView text = (TextView) v.findViewById(R.id.text);
			TextView link = (TextView) v.findViewById(R.id.link);
			TextView pubDate = (TextView) v.findViewById(R.id.pubDate);

			if (title != null) {
				title.setText(feedItem.title);
			}

			if (text != null) {
				text.setText(
					Html.fromHtml(
						feedItem.description
							.replace("<p>", "<span>")
							.replace("</p>", "</span>")
						)
				);
			}

			if (link != null) {
				link.setText(feedItem.link);
				link.setVisibility(View.GONE);
			}

			if (pubDate != null) {
				pubDate.setText(feedItem.pubDate);
			}
		}
		return v;
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView textViewLink = (TextView) view.findViewById(R.id.link);
		Intent startBrowser = new Intent(context, WebActivity.class);
		startBrowser.putExtra("URL", (String) textViewLink.getText());
		context.startActivity(startBrowser);
	}
}
