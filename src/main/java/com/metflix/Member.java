package com.metflix;

public class Member {

	private String user;
	private int    age;


	/**
	 * API 呼び出しのために必要。
	 */
	public Member() {
	}

	public Member(String user, int age) {
		this.user = user;
		this.age  = age;
	}


	public String getUser() {
		return this.user;
	}

	public int getAge() {
		return this.age;
	}


	public void setUser(String user) {
		this.user = user;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
