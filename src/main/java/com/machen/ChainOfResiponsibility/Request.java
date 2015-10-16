package com.machen.ChainOfResiponsibility;

public class Request
{
	private int level;
	private String content;
	
	public Request(int level, String content)
	{
		this.level = level;
		this.content = content;
	}
	public int getLevel()
	{
		return level;
	}
	public String getContent()
	{
		return content;
	}
	public void setLevel(int level)
	{
		this.level = level;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	
}
