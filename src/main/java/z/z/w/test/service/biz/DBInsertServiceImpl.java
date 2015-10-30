package z.z.w.test.service.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import z.z.w.test.entity.biz.MerchantSmsSend;
import z.z.w.test.service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.DBInsertServiceImpl.java
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015年9月22日 上午10:50:11
 *   LastChange: 2015年9月22日 上午10:50:11
 *      History:
 * </pre>
 **************************************************************************/
public class DBInsertServiceImpl implements IService
{
	final static Logger logger = LoggerFactory.getLogger( DBInsertServiceImpl.class );

	private AtomicLong al  = null;									/*
																					 * new
																					 * AtomicLong(
																					 * 1002258 ) ;
																					 */
	private int        idx = ThreadLocalRandom.current().nextInt();
	@Value( "${EXECUTOR.POOL.THREAD.SIZE}" ) private Integer threadNum;
	@Value( "${OPER.DATASIZE}" ) private             Long    dataSize;
	@Value( "${OPER.PER.DATASIZE}" ) private         Long    dataSizePer;
	@Value( "${OPER.BATCHSIZE}" ) private            Integer batchSize;
	@Value( "${EXECUTOR.POOL.QUEUESIZE}" ) private   Integer queueSize;
	private ExecutorService service = null;
	private MerchantSmsSendService merchantSmsSendService;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.service.IService#execute()
	 */

	public void execute() throws Exception
	{
		logger.info( "Thread size : {}. data size : {}. per data size :{},queue size : {}.", threadNum, dataSize, dataSizePer, queueSize );
//		service = Executors.newFixedThreadPool( threadNum ) ;
		service = new ThreadPoolExecutor( threadNum, threadNum, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>( queueSize ) );
		Long maxID = merchantSmsSendService.getMaxId();
		logger.info( "Current table send max id : {}.", maxID );
		if ( null == maxID ) maxID = 0l;
		al = new AtomicLong( maxID + 1000 );
		logger.debug( "{}.", al );

		List<MerchantSmsSend> list = new ArrayList<MerchantSmsSend>();

//		int idx = new Long( ( dataSize.longValue() / dataSizePer.longValue() ) ).intValue() ;
//		logger.info( "IDX===>{}" , idx ) ;

//		for ( int j = 0 ; j < idx ; j++ )
//		{
		for ( int i = 0 ; i < dataSizePer ; i++ )
			try
			{
				MerchantSmsSend record = getMSS( ++idx );
				list.add( record );

				if ( ( ( ( i + 1 ) % batchSize ) == 0 ) )
				{
					final List<MerchantSmsSend> lt = new ArrayList<MerchantSmsSend>( list );

					if ( service.isShutdown() )
					{
						logger.warn( "Executors shutdown......break..." );
						break;
					}
					service.execute( new Runnable()
					{
						private void insertData( List<MerchantSmsSend> lt )
						{
							try
							{
								long startTime = System.currentTimeMillis();
								try
								{
									merchantSmsSendService.addMerchantSmsSend( lt );
								}
								finally
								{
									logger.info( "Add merchant sms send data to db use {} ms.", ( System.currentTimeMillis() - startTime ) );
								}
							}
							catch ( Exception e )
							{
								logger.error( "分庫插入數據出错:" + e.getMessage(), e );
							}
						}

						public void run()
						{
							try
							{
								logger.debug( "Start insert data to db ....." );
								logger.debug( "{}===>>>>>>>>>{}", lt.size(), lt );
								insertData( lt );
							}
							catch ( Exception e )
							{
								logger.error( "Insert data to db error :" + e.getMessage(), e );
							}

						}
					} );

					list.clear();
				}
			}
			finally
			{
				logger.debug( "Insert data to db size : {}.", ( i + 1 ) );
			}

//			Thread.sleep( 1000 * 30 ) ;
//			logger.info( "IDX---->>{}" , j ) ;
//		}

	}

	/**
	 * @return the merchantSmsSendService
	 */
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService;
	}

	/**
	 * Create by : 2015年9月18日 下午12:31:05
	 */
//	private void insertData()
//	{
//		try
//		{
//			MerchantSmsSend record = getMSS() ;
//
//			long startTime = System.currentTimeMillis() ;
//			try
//			{
//				merchantSmsSendService.addMerchantSmsSend( record ) ;
//			}
//			finally
//			{
//				logger.info( "Add merchant sms send data to db use {} ms." , ( System.currentTimeMillis() - startTime ) ) ;
//			}
//		}
//		catch ( Exception e )
//		{
//			logger.error( "分庫插入數據出错:" + e.getMessage() , e ) ;
//		}
//
//	}

	/**
	 * @param merchantSmsSendService the merchantSmsSendService to set
	 */
	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */

	/**
	 * Create by : 2015年9月21日 下午8:44:00
	 *
	 * @return
	 */
	private MerchantSmsSend getMSS( int i )
	{
//		String[] channelCodeArr = { "AH_UNICOM_CHANNEL001" ,
//				"BJ_CT_CHANNEL001" ,
//				"BJ_CT_CHANNEL002" ,
//				"BJ_CT_CHANNEL003" ,
//				"BJ_JTD_DX_SINGLE_CHANNEL" ,
//				"BJ_JTD_LT_SINGLE_CHANNEL" ,
//				"BJ_JTD_YDSW_CHANNEL001" ,
//				"BJ_JTD_YDSW_CHANNEL002" ,
//				"BJ_JTD_YDSW_CHANNEL003" ,
//				"BJ_JTD_YD_CHANNEL001" ,
//				"DG_UNICOM_CHANNEL001" ,
//				"DG_UNICOM_CHANNEL002" ,
//				"DG_UNICOM_CHANNEL003" ,
//				"FX_YD_CHANNEL_001" ,
//				"GZ_LY_DX_CHANNEL001" ,
//				"GZ_LY_LT_CHANNEL001" ,
//				"GZ_LY_YD_CHANNEL001" ,
//				"HZ_YD_CHANNEL001" ,
//				"TJ_COMM_CHANNEL001" ,
//				"TJ_COMM_CHANNEL002" ,
//				"TJ_COMM_CHANNEL003" ,
//				"ZS_P2P_CHANNEL001" ,
//				"ZS_P2P_CHANNEL002" ,
//				"ZS_P2P_CHANNEL003" } ;

		MerchantSmsSend record = new MerchantSmsSend();
//		int idx = channelCodeArr.length ;
//
//		/**
//		 * 'zhisen007','1','ADSF23A4DFAS','1234（验证码），#time#内有效，请尽快验证。【【智验】】','TJ_COMM_CHANNEL001','2015-07-06
//		 * 10:56:16','13169919817',NULL,'1','0',NULL,'1','1','广东','深圳','LT',NULL,NULL
//		 */
//
//		record.setReceiveMobile( "1" + Math.round( ( ThreadLocalRandom.current().nextDouble() * ( 9999999999l - 100000000l ) ) + 100000000l ) ) ;
//		record.setSmsContent( ( ( ThreadLocalRandom.current().nextInt( max ) % ( ( max - min ) + 1 ) ) + min ) + "（验证码），请尽快验证。【有道】" ) ;
//
//		record.setSmsChannelCode( channelCodeArr[ ( ( ThreadLocalRandom.current().nextInt( cmax ) % ( ( cmax - min ) + 1 ) ) + min ) ] ) ;
//		record.setMerchantAccount( RandomUtil.INSTANCE.generateString( 10 ) ) ;
//		record.setCreateTime( new Date() ) ;
//		record.setReceiveTime( DateUtils.addSeconds( new Date(), ( ThreadLocalRandom.current().nextInt( 3 ) % 3 ) + 1 ) ) ;
//		record.setSendTime( DateFormatUtils.format( DateUtils.addSeconds( new Date() , ( ThreadLocalRandom.current().nextInt( 3 ) % 3 ) + 1 ) ,
//													"yyyy-MM-dd HH:mm:ss" ) ) ;
//		record.setReceiveStatus( ThreadLocalRandom.current().nextInt( 1 ) % 2 ) ;
//		record.setSendResult( Short.parseShort( String.valueOf( ThreadLocalRandom.current().nextInt( 1 ) % 2 ) ) ) ;
//		record.setReceiveStatusChannel( ThreadLocalRandom.current().nextInt( max ) % ( max + 1 ) ) ;
//		record.setMerchantSmsUid( RandomUtil.INSTANCE.generateLowerString( 32 ) ) ;
//		record.setChannelSmsId( record.getMerchantSmsUid().substring( 20 ) ) ;
//		record.setSmsSignerId( -1 ) ;
//		record.setSmsTemplateId( "-1" ) ;
//		record.setSmsType( 1 ) ;// 默认国内短信
//		record.setResource( 1 ) ;
//		record.setId( al.incrementAndGet() ) ;
//		logger.debug( "{}" , record.toString() ) ;
//
//		record.setReceiveMobile( "15098648522" );
//		record.setSmsContent( i + "（验证码），请尽快验证。【有道】" );
//
//		record.setSmsChannelCode( i + "ZS_P2P_CHANNEL003" );
//		record.setMerchantAccount( i + "ZS_P2P_CHANNEL003" );
//		record.setCreateTime( new Date() );
//		record.setReceiveTime( new Date() );
//		record.setSendTime( DateFormatUtils.format( new Date() , "yyyy-MM-dd HH:mm:ss" ) );
//		record.setReceiveStatus( 1 );
//		record.setSendResult( ( short ) 1 );
//		record.setReceiveStatusChannel( i );
//		record.setMerchantSmsUid( i + "ZS_P2P_CHANNEL003" );
//		record.setChannelSmsId( i + "ZS_P2P_CHANNEL003" );
//		record.setSmsSignerId( -1 );
//		record.setSmsTemplateId( "-1" );
//		record.setSmsType( 1 );// 默认国内短信
//		record.setResource( 1 );
//		record.setId( al.incrementAndGet() );
//		logger.debug( "{}" , record.toString() );

		return record;
	}

	public void run()
	{
		try
		{
			execute();

			service.shutdown();
		}
		catch ( Exception e )
		{
			logger.error( "分庫插入數據出错:" + e.getMessage(), e );
		}

	}
}
