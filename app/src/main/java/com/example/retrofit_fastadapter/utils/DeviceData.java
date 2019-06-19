package com.example.retrofit_fastadapter.utils;

import android.graphics.Point;
import android.view.Display;

public class DeviceData {

	private Display display = null;

	private static DeviceData instance = null;

	public static DeviceData getInstance(){
		if (instance == null){
			instance = new DeviceData();
		}
		return instance;
	}

	private DeviceData(){}

	public void setDisplay(Display display){
		this.display = display;
	}

	public int getDisplayWidth(){
		Point size = new Point();
		display.getSize(size);
		return size.x;
	}

	public int getDisplayHeight(){
		Point size = new Point();
		display.getSize(size);
		return size.y;
	}

}
