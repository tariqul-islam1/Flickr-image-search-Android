package com.example.retrofit_fastadapter.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import com.example.retrofit_fastadapter.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;

public class FlickrModel extends AbstractItem<FlickrModel, FlickrModel.ViewHolder> {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("modified")
    @Expose
    public String modified;
    @SerializedName("generator")
    @Expose
    public String generator;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;

    @Override
    public int getType() {
        return R.id.recycler;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.recycler_item_posts;
    }

    @Override
    public void bindView(FlickrModel.ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        holder.title.setText("jj");
        holder.body.setText("oo");
    }

    @Override
    public void unbindView(FlickrModel.ViewHolder holder) {
        super.unbindView(holder);
        holder.title.setText(null);
        holder.body.setText(null);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }

    public class Item {
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("link")
        @Expose
        public String link;
        @SerializedName("media")
        @Expose
        public Media media;
        @SerializedName("date_taken")
        @Expose
        public String dateTaken;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("published")
        @Expose
        public String published;
        @SerializedName("author")
        @Expose
        public String author;
        @SerializedName("author_id")
        @Expose
        public String authorId;
        @SerializedName("tags")
        @Expose
        public String tags;
    }

    public class Media {
        @SerializedName("m")
        @Expose
        public String m;
    }
}

