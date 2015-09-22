package z.z.w.test.server.impl ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.beans.BeansException ;
import org.springframework.context.ApplicationContext ;
import org.springframework.context.support.ClassPathXmlApplicationContext ;

import z.z.w.test.server.IServiceLoader ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.ContextServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月7日 下午12:14:04 
 *   LastChange: 2015年9月7日 下午12:14:04 
 *      History:
 * </pre>
 **************************************************************************/
public class ContextServiceImpl implements IServiceLoader
{
	final static Logger	logger	= LoggerFactory.getLogger( ContextServiceImpl.class ) ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#destroy()
	 */
	@Override
	public void destroy()
	{
		// TODO 2015年9月7日 下午12:14:04
		Thread.currentThread().interrupt() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#loadService()
	 */
	@Override
	public void loadService()
	{
		logger.info( "Starting load app context service....." ) ;
		try
		{
			String[] classpaths = new String[] { "classpath:spring/spring.xml" } ;
			ApplicationContext app = new ClassPathXmlApplicationContext( classpaths ) ;
			logger.info( "ApplicationContext : {}" , app ) ;
		}
		catch ( BeansException e )
		{
			logger.error( "Loading app context error : {}." , e.getMessage() , e ) ;
		}
		logger.info( "Loaded app context service." ) ;
		
	}
}
