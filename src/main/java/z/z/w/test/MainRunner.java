package z.z.w.test ;

import java.util.ServiceLoader ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

import z.z.w.server.IServiceLoader ;
import z.z.w.util.SpringContextUtil ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.MainRunner.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月7日 下午12:12:32 
 *   LastChange: 2015年9月7日 下午12:12:32 
 *      History:
 * </pre>
 **************************************************************************/
public class MainRunner
{
	private static final Logger		logger	= LoggerFactory.getLogger( MainRunner.class ) ;
	private static volatile boolean	running	= true ;
	
	/**
	 * Create by : 2015年9月7日 下午12:12:32
	 */
	public static void main( String[] args )
	{
		final ServiceLoader< IServiceLoader > loader = ServiceLoader.load( IServiceLoader.class ) ;
		
		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				logger.debug( "Service will be stopping..." ) ;
				for ( IServiceLoader service : loader )
					service.destroy() ;
				logger.debug( "Service is stopped!" ) ;
			} ;
		} ) ;
		
		logger.debug( "Service will be starting..." ) ;
		for ( IServiceLoader service : loader )
			service.loadService() ;
		logger.debug( "Service is started!" ) ;
		logger.debug( "@@{}" , SpringContextUtil.getApplicationContext() ) ;
		
		synchronized ( MainRunner.class )
		{
			while ( running )
				try
				{
					MainRunner.class.wait() ;
				}
				catch ( Throwable e )
				{
					logger.error( "Single mainRunner processor error : {}." , e.getMessage() , e ) ;
				}
		}
		
	}
	
}
