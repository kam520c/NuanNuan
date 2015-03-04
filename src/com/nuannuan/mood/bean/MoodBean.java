package com.nuannuan.mood.bean;

public class MoodBean {
	// 心情图片
	private int moodImage;
	// 记录心情时，当天的天气
	private String moodWeather;
	// 心情记录
	private String mood;
	// 记录心情所在的位置
	private int moodPosition;

	public MoodBean(int moodPosition, int moodImage, String moodWeather,
			String mood) {
		this.moodPosition = moodPosition;
		this.moodImage = moodImage;
		this.moodWeather = moodWeather;
		this.mood = mood;
	}

	public int getMoodPosition() {
		return moodPosition;
	}

	public void setMoodPosition(int moodPosition) {
		this.moodPosition = moodPosition;
	}

	public int getMoodImage() {
		return moodImage;
	}

	public void setMoodImage(int moodImage) {
		this.moodImage = moodImage;
	}

	public String getMoodWeather() {
		return moodWeather;
	}

	public void setMoodWeather(String moodWeather) {
		this.moodWeather = moodWeather;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

}
