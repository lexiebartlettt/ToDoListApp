package com.ualberta.lexie;

public class ToDo {
	//this class is loosely based off of the lonely twitter class
	//LonelyTweetModel.java
	//https://github.com/lexiebartlettt/lonelyTwitter/blob/f14io/src/ca/ualberta/cs/lonelytwitter/LonelyTweetModel.java
	
	private String toDo;
	private Boolean isChecked;
	
	public String getText(){
		return toDo;
	}
	public ToDo(String text){
		super();
		this.toDo=text;
		isChecked=false;
	}
	public ToDo(String text, Boolean isChecked){
		super();
		this.toDo=text;
		this.isChecked=isChecked;
	}
	public void Check(Boolean isChecked){
		this.isChecked=isChecked;
	}
	public String getString(){
		return toDo;
	}
	public String toString(){
		return toDo;
	}
	public Boolean getCheck(){
		return isChecked;
	}
}
