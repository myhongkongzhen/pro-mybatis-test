/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.machen.ChainOfResiponsibility;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Cilent
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		Handler hTest = (Handler) factory.getBean("test");

		
		
		Random rn = new Random();
		List<Request> requests = new ArrayList<Request>();
		for(int i = 0;i<10;i++){
			requests.add(new Request(rn.nextInt(10)," content ... xxx:"+rn.nextDouble()));
		}
		
		for (Request request : requests)
		{
			hTest.handlerRequest(request);
		}
	}

}
