package com.example.recorder;

import java.util.Date;
import java.util.UUID;

public class Crime {
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private Boolean mSolved;
	


	public Crime(){
		mId = UUID.randomUUID();
		mDate = new Date();
	}
	
	public Boolean isSolved() {
		return mSolved;
	}

	public void setSolved(Boolean solved) {
		mSolved = solved;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}
	
}
