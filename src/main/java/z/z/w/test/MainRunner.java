package z.z.w.test;

import java.net.ServerSocket;
import java.util.Properties;
import java.util.ServiceLoader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import z.z.w.server.IServiceLoader;
import z.z.w.util.SpringContextUtil;
import z.z.w.util.comm.PropertiesUtils;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.MainRunner.java
 *         Desc:
 *      &#64;author: Z_Z.W - myhongkongzhen@gmail.com
 *     &#64;version: 2015年9月7日 下午12:12:32
 *   LastChange: 2015年9月7日 下午12:12:32
 *      History:
 * </pre>
 **************************************************************************/
public class MainRunner
{
//	private static volatile boolean	running			= true ;
	final static String			RELATIVE_PATH	= "pro/config.properties";
	private static final Logger	logger			= LoggerFactory.getLogger( MainRunner.class );
	private static ServerSocket	serverSocket	= null;
	
	/**
	 * Create by : 2015年9月7日 下午12:12:32
	 */
	public static void main( String[] args )
	{
		
		try
		{
			Properties props = PropertiesUtils.INSTANCE.getProperties( RELATIVE_PATH );
			if ( !props.isEmpty() )
			{
				String port = props.getProperty( "SINGLE.PROCESS.PORT" );
				logger.info( "Single process listen port : {}." , port );
				if ( StringUtils.isNotBlank( port ) ) serverSocket = new ServerSocket( Integer.parseInt( port ) );
				logger.debug( "serverSocket=>{}." , serverSocket );
			}
		}
		catch ( Exception e )
		{
			logger.error( "Single mainRunner processor error : {}." , e.getMessage() );
			System.exit( 1 );
		}
		
		final ServiceLoader< IServiceLoader > loader = ServiceLoader.load( IServiceLoader.class );
		
		Runtime.getRuntime().addShutdownHook( new Thread()
		{
			@Override
			public void run()
			{
				logger.debug( "Service will be stopping..." );
				for ( IServiceLoader service : loader )
					service.destroy();
				logger.debug( "Service is stopped!" );
			};
		} );
		
		logger.debug( "Service will be starting..." );
		for ( IServiceLoader service : loader )
			service.loadService();
		logger.debug( "Service is started!" );
		logger.debug( "@@{}" , SpringContextUtil.getApplicationContext() );
		
//		synchronized ( MainRunner.class )
//		{
//			while ( running )
//				try
//				{
//					MainRunner.class.wait() ;
//				}
//				catch ( Throwable e )
//				{
//					logger.error( "Single mainRunner processor error : {}." , e.getMessage() , e ) ;
//				}
//		}
	
	}
}
