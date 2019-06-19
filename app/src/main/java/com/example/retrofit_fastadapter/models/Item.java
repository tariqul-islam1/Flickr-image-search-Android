package com.example.retrofit_fastadapter.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofit_fastadapter.R;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Item extends AbstractItem<Item, Item.ViewHolder> {
	public String title;
	public String link;
	public FlickrModel.Media media;
	public String dateTaken;
	public String description;
	public String published;
	public String author;
	public String authorId;
	public String tags;

	@Override
	public int getType() {
		return R.id.recycler;
	}

	@Override
	public int getLayoutRes() {
		return R.layout.recycler_item_posts;
	}

	@Override
	public void bindView(Item.ViewHolder holder, List<Object> payloads) {
		super.bindView(holder, payloads);

//		holder.title.setText(title);
//		holder.body.setText(media.m);
		Picasso.get().load(media.m).into(holder.img);

	}

	@Override
	public void unbindView(Item.ViewHolder holder) {
		super.unbindView(holder);
//		holder.title.setText(null);
//		holder.body.setText(null);
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
//		TextView title, body;
		ImageView img;

		public ViewHolder(View itemView) {
			super(itemView);

//			title = itemView.findViewById(R.id.title);
			//			body = itemView.findViewById(R.id.body);
						img = itemView.findViewById(R.id.img);

		}
	}
}
