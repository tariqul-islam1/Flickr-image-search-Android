package com.example.retrofit_fastadapter.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit_fastadapter.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tariqul.Islam on 4/30/17.
 */

public class PostModel extends AbstractItem<PostModel, PostModel.ViewHolder> {

    int userId, id;
    String title, body;

    public PostModel() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int getType() {
        return R.id.recycler;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.recycler_item_posts;
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        holder.title.setText(id + ". " + title);
        holder.body.setText(body);
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        holder.title.setText(null);
        holder.body.setText(null);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }
}
