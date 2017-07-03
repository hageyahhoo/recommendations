package com.metflix;

public class Movie {

	private String title;


	/**
	 * API 呼び出しのために必要。
	 */
	public Movie() {
	}

	public Movie(String title) {
		this.title = title;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
