package com.example.retrofit_fastadapter.models;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.retrofit_fastadapter.R;
import com.example.retrofit_fastadapter.utils.DeviceData;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

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
    public long id;

    public long getId() {
        try {
            id = Long.valueOf(Uri.parse(link).getLastPathSegment() + "");
        } catch (NullPointerException npe) {
            id = new Random().nextLong();
            npe.printStackTrace();
        }
        return id;
    }

    @Override
    public int getType() {
        return R.id.recycler;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.grid_item;
    }

    @Override
    public void bindView(Item.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);
        Picasso.get().load(media.m).into(holder.img);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            img.getLayoutParams().width = DeviceData.getInstance().getDisplayWidth() / 2;
            img.getLayoutParams().height = DeviceData.getInstance().getDisplayWidth() / 2;
        }
    }
}
