package z.z.w.test.service.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import z.z.w.test.service.IService;
import z.z.w.util.ShardRedisOperator;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.DBSelectServcieImpl.java
 *         Desc:
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月23日 上午9:19:26
 *   LastChange: 2015年9月23日 上午9:19:26
 *      History:
 * </pre>
 **************************************************************************/
public class DBSelectServcieImpl implements IService
{
	final static Logger logger = LoggerFactory.getLogger( DBSelectServcieImpl.class );
	private MerchantSmsSendService merchantSmsSendService;
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	private ShardRedisOperator shardRedisOperator;

	public ShardRedisOperator getShardRedisOperator()
	{
		return shardRedisOperator;
	}

	public void setShardRedisOperator( ShardRedisOperator shardRedisOperator )
	{
		this.shardRedisOperator = shardRedisOperator;
	}

	/*


分布式调用中不支持事务。

因为事务是在服务器端实现，而在分布式中，每批次的调用对象都可能访问不同的机器，所以，没法进行事务。

		 * (non-Javadoc)
		 * @see z.z.w.test.service.IService#execute()
		 */
	@Override
	public void execute() throws Exception
	{

		// TODO 2015年9月23日 上午9:19:26
		logger.info( "開始查詢數據庫數據...." );

		String value = "te00000st1";
		String key   = "tljkljlest2";
		logger.info( "DBSelectServcieImpl== shardRedisOperator.set( key, valu==={}..{}.", shardRedisOperator.set( key, value ), shardRedisOperator.phget( key ) );
		Thread.sleep( 5 * 1000 );

		logger.info( "DBSelectServcieImpl== .del( key, val-DBSelectServcieImpl===shardRedisOperator.get( key )=={}...", shardRedisOperator.phget( key ) );
//		shardRedisOperator.del( key );

		logger.info( "i================= .del( key, val-DBSelectServcieImpl===shardRedisOperator.get( key )=={}...", shardRedisOperator.phget( key ) );

		Thread.sleep( 10 * 1000 );

	}

	/**
	 * @return the merchantSmsSendService
	 */
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService;
	}

	/**
	 * @return the threadPoolTaskExecutor
	 */
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor()
	{
		return threadPoolTaskExecutor;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override public void run()
	{
		try
		{
			execute();
		}
		catch ( Exception e )
		{
			logger.error( "查詢數據出错:" + e.getMessage(), e );
		}
	}

	/**
	 * @param merchantSmsSendService the merchantSmsSendService to set
	 */
	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService;
	}

	/**
	 * @param threadPoolTaskExecutor the threadPoolTaskExecutor to set
	 */
	public void setThreadPoolTaskExecutor( ThreadPoolTaskExecutor threadPoolTaskExecutor )
	{
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
	}

}
