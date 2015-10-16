package com.machen.ChainOfResiponsibility;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;
import java.util.HashMap;

public class ResiponsibilityHandlerAspect
{
	@SuppressWarnings( "unchecked" ) private void checkResiponsibility( JoinPoint joinPoint )
	{
		try
		{


			for (int i = 0; i < joinPoint.getArgs().length; i++) {
				System.out.println("**********************"+joinPoint.getArgs()[i]);
			}
			System.out.println(joinPoint.getSignature().getName());


			System.out.println(" ====================================current class = ResiponsibilityHandlerAspect.checkResiponsibility()");




			Request request = ( Request ) joinPoint.getArgs()[ 0 ];
			Class<Handler> classType = joinPoint.getSignature().getDeclaringType();
			Method getSuccessor = classType.getMethod( "getSuccessor", new Class[] {} );
			Handler successor = ( Handler ) getSuccessor.invoke( joinPoint.getThis(), new Object[] {} );

			Method getDeal = classType.getMethod( "getDeal", new Class[] {} );
			HashMap<Request, Boolean> deal = ( HashMap<Request, Boolean> ) getDeal.invoke( joinPoint.getThis(), new Object[] {} );

			boolean isDeal;
			if ( !deal.containsKey( request ) )
			{
				isDeal = false;
			}
			else
			{
				isDeal = deal.get( request );
			}
			if ( successor != null && !isDeal )
			{
				System.out.println( "級別：：：" + request.getLevel() + "::::传给继任者:" + successor.getName() );
				successor.handlerRequest( request );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}
