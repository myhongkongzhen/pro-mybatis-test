package z.z.w.test.server.impl ;

import java.util.HashMap ;
import java.util.Map ;
import java.util.concurrent.Callable ;
import java.util.concurrent.CompletionService ;
import java.util.concurrent.ExecutorCompletionService ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.Future ;
import java.util.concurrent.TimeUnit ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor ;
import org.springframework.stereotype.Service ;

import z.z.w.test.server.IServiceLoader ;
import z.z.w.util.SpringContextUtil ;
import z.z.w.util.http.HttpClientUtil ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.BizServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月10日 下午4:31:30 
 *   LastChange: 2015年9月10日 下午4:31:30 
 *      History:
 * </pre>
 **************************************************************************/
@Service
public class BizServiceImpl implements IServiceLoader
{
	final static Logger				logger	= LoggerFactory.getLogger( BizServiceImpl.class ) ;
	
	private ThreadPoolTaskExecutor	threadPoolTaskExecutor ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#loadService()
	 */
	public void loadService()
	{
		logger.info( "Starting bussiness service....." ) ;
		try
		{
			final String url = "http://ah.vnet.cn/SmsGW/SMS/SingleSMS" ;
			
			threadPoolTaskExecutor = SpringContextUtil.getBean( ThreadPoolTaskExecutor.class ) ;
			final CompletionService< String > compService = new ExecutorCompletionService< String >( threadPoolTaskExecutor ) ;
			final ExecutorService executor = Executors.newFixedThreadPool( 200 ) ;
			logger.info( "-{}", compService ) ;
			
			final String[] mobiles =
			//
//			{ "15098648522", "18820238065", "17001930700", "17001930700", "18998561327", "13686862157", "13686862157" } ;
			{ "17001930700", "18123975891" } ;
			final String[] sgins =
			//
//					{ "15098648522", "18820238065", "17001930700", "17001930700", "18998561327", "13686862157", "13686862157" } ;
			{ "9a63083cafdffb4fbed65cb73ad72c49", "0900b399a328576dde2482026227e288" } ;
			
			while ( true )
			{
				
				long idx = ( ( long ) ( Math.random() * 10000 ) ) ;
				if ( idx / 1000 == 0 )
				{
					Thread.sleep( 1000 * 20 ) ;
					continue ;
				}
				executor.submit( new Runnable()
				{
					@Override
					public void run()
					{
						for ( int i = 0 ; i < mobiles.length ; i++ )
						{
							final String mo = mobiles[ i ] ;
							final String sgin = sgins[ i ] ;
							compService.submit( new Callable< String >()
							{
								@Override
								public String call() throws Exception
								{
									logger.info( "Start send sms for mobile {}.....", mo ) ;
									Map< String, String > param = new HashMap< String, String >() ;
									param.put( "json", sendSmsJson( mo, sgin ) ) ;
									logger.info( "Http client param -->{}", param ) ;
									
									String result = HttpClientUtil.httpPostParticular( url, param, null ) ;
									logger.info( "RESULT : {}.", result ) ;
									logger.info( "End send sms for mobile {}.....", mo ) ;
									
									return result ;
								}
							} ) ;
						}
						
						for ( int i = 0 ; i < mobiles.length ; i++ )
						{
							Future< String > future = null ;
							try
							{
								future = compService.poll( 3L, TimeUnit.SECONDS ) ;
								if ( future != null )
								{
									logger.info( "===>>>>>>>>Result : {}", future.get() ) ;
								}
							}
							catch ( Exception e )
							{
								logger.error( "Send sms error : {}.", e.getMessage(), e ) ;
							}
						}
					}
				} ) ;
			}
			
		}
		catch ( Exception e )
		{
			logger.error( "Loading bussiness error : {}.", e.getMessage(), e ) ;
		}
		logger.info( "Loaded bussiness service." ) ;
		
	}
	
	/**
	 * Create by : 2015年9月14日 下午6:19:16
	 */
	private String sendSmsJson( String mobile, String sgin )
	{
		StringBuffer data = new StringBuffer() ;
		data.append( "{" ) ;
		data.append( "\"SingleSMSRequest\":{" ) ;
		data.append( "\"UserID\":\"10040001\", " ) ;
		data.append( "\"TPLID\":\"1000\", " ) ;
		data.append( "\"MSMSID\":\"1812397589120150915104950784\"," ) ;
		data.append( "\"StampTime\":\"2015-06-12 12:31:06\"," ) ;
		data.append( "\"SMSText\":\"您好 ： 您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技AHDX】\", " ) ;
		// MD5(UserID+ StampTime+ TPLID+ ObjMobile+商户通信Key)
//		String ll = MD5Util.md5Hex( "100400012015-06-12 12:31:061000" + mobile + "BhpetXlwaldEMDKsoGFaEngKnqhJUXvU" ) ;
		data.append( "\"Sign\":\"" + sgin + "\"," ) ;
		data.append( "\"ObjMobile\":\"" + mobile + "\"" ) ;
		data.append( "}" ) ;
		data.append( "}" ) ;
		return data.toString() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#destroy()
	 */
	public void destroy()
	{
		// TODO 2015年9月10日 下午4:31:30
		Thread.currentThread().interrupt() ;
	}
	
}
