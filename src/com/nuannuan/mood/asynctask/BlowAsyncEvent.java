package com.nuannuan.mood.asynctask;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface BlowAsyncEvent {
	void DataLoadSuccess(JSONObject city);
	void DataLoadFaild();
}
