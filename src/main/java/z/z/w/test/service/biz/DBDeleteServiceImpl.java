package z.z.w.test.service.biz ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor ;

import z.z.w.test.service.IService ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.DBDeleteServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月23日 上午9:18:56 
 *   LastChange: 2015年9月23日 上午9:18:56 
 *      History:
 * </pre>
 **************************************************************************/
public class DBDeleteServiceImpl implements IService
{
	final static Logger				logger	= LoggerFactory.getLogger( DBDeleteServiceImpl.class ) ;
	private MerchantSmsSendService	merchantSmsSendService ;
	private ThreadPoolTaskExecutor	threadPoolTaskExecutor ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.service.IService#execute()
	 */
	@Override
	public void execute() throws Exception
	{
		logger.info( "開始刪除數據庫數據..." ) ;
	}
	
	/**
	 * @return the merchantSmsSendService
	 */
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService ;
	}
	
	/**
	 * @return the threadPoolTaskExecutor
	 */
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor()
	{
		return threadPoolTaskExecutor ;
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
			execute() ;
		}
		catch ( Exception e )
		{
			logger.error( "刪除數據出错:" + e.getMessage() , e ) ;
		}
	}
	
	/**
	 * @param merchantSmsSendService
	 *            the merchantSmsSendService to set
	 */
	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService ;
	}
	
	/**
	 * @param threadPoolTaskExecutor
	 *            the threadPoolTaskExecutor to set
	 */
	public void setThreadPoolTaskExecutor( ThreadPoolTaskExecutor threadPoolTaskExecutor )
	{
		this.threadPoolTaskExecutor = threadPoolTaskExecutor ;
	}
	
}
