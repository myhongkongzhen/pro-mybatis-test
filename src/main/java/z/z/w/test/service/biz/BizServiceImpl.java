package z.z.w.test.service.biz;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import z.z.w.test.service.IService;
import z.z.w.util.http.HttpClientUtil;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.BizServiceImpl.java
 *         Desc: 
 *      &#64;author: Z_Z.W - myhongkongzhen@gmail.com
 *     &#64;version: 2015年9月22日 上午10:50:24 
 *   LastChange: 2015年9月22日 上午10:50:24 
 *      History:
 * </pre>
 **************************************************************************/
public class BizServiceImpl implements IService
{
	final static Logger				logger	= LoggerFactory.getLogger( BizServiceImpl.class );
	private ThreadPoolTaskExecutor	threadPoolTaskExecutor;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.service.IService#execute()
	 */
	@Override
	public void execute() throws Exception
	{
		logger.info( "Starting bussiness service....." );
		try
		{
			final String url = "http://ah.vnet.cn/SmsGW/SMS/SingleSMS";
			
//			threadPoolTaskExecutor = SpringContextUtil.getBean( ThreadPoolTaskExecutor.class ) ;
			final CompletionService< String > compService = new ExecutorCompletionService< String >( threadPoolTaskExecutor );
			final ExecutorService executor = Executors.newFixedThreadPool( 200 );
			logger.info( "-{}" , compService );
			
			final String[] mobiles =
										//
//			{ "15098648522", "18820238065", "17001930700", "17001930700", "18998561327", "13686862157", "13686862157" } ;
										{ "17001930700" , "18123975891" };
			final String[] sgins =
									//
//					{ "15098648522", "18820238065", "17001930700", "17001930700", "18998561327", "13686862157", "13686862157" } ;
									{ "9a63083cafdffb4fbed65cb73ad72c49" , "0900b399a328576dde2482026227e288" };
									
			while ( true )
			{
				
				long idx = ( ( long ) ( Math.random() * 10000 ) );
				if ( ( idx / 1000 ) == 0 )
				{
					Thread.sleep( 1000 * 20 );
					continue;
				}
				executor.submit( new Runnable()
				{
					@Override
					public void run()
					{
						for ( int i = 0 ; i < mobiles.length ; i++ )
						{
							final String mo = mobiles[ i ];
							final String sgin = sgins[ i ];
							compService.submit( new Callable< String >()
							{
								@Override
								public String call() throws Exception
								{
									try
									{
										logger.info( "Start send sms for mobile {}....." , mo );
										Map< String, String > param = new HashMap< String, String >();
										param.put( "json" , sendSmsJson( mo , sgin ) );
										logger.info( "Http client param -->{}" , param );
										
										String result = HttpClientUtil.httpPostParticular( url , param , null );
										logger.info( "RESULT : {}." , result );
										logger.info( "End send sms for mobile {}....." , mo );
										
										return result;
									}
									catch ( Exception e )
									{
										logger.error( "Send sms error : {}." , e.getMessage() , e );
										return null;
									}
								}
								
								private String sendSmsJson( String mobile , String sgin )
								{
									StringBuffer data = new StringBuffer();
									data.append( "{" );
									data.append( "\"SingleSMSRequest\":{" );
									data.append( "\"UserID\":\"10040001\", " );
									data.append( "\"TPLID\":\"1000\", " );
									data.append( "\"MSMSID\":\"1812397589120150915104950784\"," );
									data.append( "\"StampTime\":\"2015-06-12 12:31:06\"," );
									data.append( "\"SMSText\":\"您好 ： 您的注册码是" + ( ( long ) ( Math.random() * 10000 ) ) + "【技AHDX】\", " );
									// MD5(UserID+ StampTime+ TPLID+ ObjMobile+商户通信Key)
//									String ll = MD5Util.md5Hex( "100400012015-06-12 12:31:061000" + mobile + "BhpetXlwaldEMDKsoGFaEngKnqhJUXvU" ) ;
									data.append( "\"Sign\":\"" + sgin + "\"," );
									data.append( "\"ObjMobile\":\"" + mobile + "\"" );
									data.append( "}" );
									data.append( "}" );
									return data.toString();
								}
							} );
						}
						
						for ( int i = 0 ; i < mobiles.length ; i++ )
						{
							Future< String > future = null;
							try
							{
								future = compService.poll( 3L , TimeUnit.SECONDS );
								if ( future != null ) logger.info( "===>>>>>>>>Result : {}" , future.get() );
							}
							catch ( Exception e )
							{
								logger.error( "Send sms error : {}." , e.getMessage() , e );
							}
						}
					}
				} );
			}
			
		}
		catch ( Exception e )
		{
			logger.error( "Loading bussiness error : {}." , e.getMessage() , e );
		}
		logger.info( "Loaded bussiness service." );
		
	}
	
	/**
	 * @return the threadPoolTaskExecutor
	 */
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor()
	{
		return threadPoolTaskExecutor;
	}
	
	/**
	 * @param threadPoolTaskExecutor
	 *            the threadPoolTaskExecutor to set
	 */
	public void setThreadPoolTaskExecutor( ThreadPoolTaskExecutor threadPoolTaskExecutor )
	{
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run()
	{
		try
		{
			logger.info( "Starting bussiness service....." );
			execute();
		}
		catch ( Exception e )
		{
			logger.error( "Loading bussiness error : {}." , e.getMessage() , e );
		}

	}
	
}
