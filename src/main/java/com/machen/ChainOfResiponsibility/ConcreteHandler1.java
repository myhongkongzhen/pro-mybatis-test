/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.machen.ChainOfResiponsibility;

public class ConcreteHandler1 extends Handler
{



	@Override
	public void handlerRequest(Request request)
	{
		if(request.getLevel()<=4){
			System.out.println("级别:::"+request.getLevel()+":::在我+++"+getName()+"+++这打住了,显示内容:==>>>>"+request.getContent());
			this.deal.put(request, true);
		}
	}

}
