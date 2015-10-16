package com.machen.ChainOfResiponsibility;

import java.util.HashMap;

public abstract class Handler
{
	protected String name;
	protected Handler successor;
	protected HashMap<Request, Boolean> deal;
	public Handler(){
		this.deal = new HashMap<Request, Boolean>();
	}
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean getSpecificDeal(Request request)
	{
		return deal.get(request);
	}

	public HashMap<Request, Boolean> getDeal()
	{
		return deal;
	}
	
	public abstract void handlerRequest(Request request);

	public Handler getSuccessor()
	{
		return successor;
	}

	public void setSuccessor(Handler successor)
	{
		this.successor = successor;
	}

}
