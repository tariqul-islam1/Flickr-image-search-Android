package com.example.retrofit_fastadapter.models;

import java.util.List;

public class FlickrModel {
	public String title;
	public String link;
	public String description;
	public String modified;
	public String generator;
	public List<Item> items = null;

	public class Media {
		public String m;
	}
}

