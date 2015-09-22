package z.z.w.test.service.biz ;

import java.util.ArrayList ;
import java.util.Date ;
import java.util.List ;
import java.util.Random ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.atomic.AtomicLong ;

import org.apache.commons.lang3.time.DateFormatUtils ;
import org.apache.commons.lang3.time.DateUtils ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.beans.factory.annotation.Value ;

import z.z.w.test.entity.biz.MerchantSmsSend ;
import z.z.w.test.service.IService ;
import z.z.w.util.comm.RandomUtil ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.DBInsertServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月22日 上午10:50:11 
 *   LastChange: 2015年9月22日 上午10:50:11 
 *      History:
 * </pre>
 **************************************************************************/
public class DBInsertServiceImpl implements IService
{
	final static Logger				logger	= LoggerFactory.getLogger( DBInsertServiceImpl.class ) ;
	
	private static AtomicLong		al		= null ;													/*
																										 * new
																										 * AtomicLong(
																										 * 1002258 ) ;
																										 */
	@Value( "${EXECUTOR.POOL.SIZE}" )
	private Integer					threadNum ;
	@Value( "${OPER.DATASIZE}" )
	private Long					dataSize ;
	@Value( "${OPER.BATCHSIZE}" )
	private Integer					batchSize ;
	
	private ExecutorService			service	= null ;
	
	private MerchantSmsSendService	merchantSmsSendService ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.service.IService#execute()
	 */
	@Override
	public void execute() throws Exception
	{
		logger.info( "Thread size : {}. data size : {}." , threadNum , dataSize ) ;
		service = Executors.newFixedThreadPool( threadNum ) ;
		Long maxID = merchantSmsSendService.getMaxId() ;
		logger.info( "Current table send max id : {}." , maxID ) ;
		if ( null == maxID ) maxID = 0l ;
		al = new AtomicLong( maxID + 1000 ) ;
		logger.debug( "{}." , al ) ;
		
		List< MerchantSmsSend > list = new ArrayList< MerchantSmsSend >() ;
		
		for ( int i = 0 ; i < dataSize ; i++ )
		{
			try
			{
				MerchantSmsSend record = getMSS() ;
				list.add( record ) ;
				
				if ( ( ( ( i + 1 ) % batchSize ) == 0 ) )
				{
					final List< MerchantSmsSend > lt = new ArrayList< MerchantSmsSend >( list ) ;
					
					service.execute( new Runnable()
					{
						private void insertData( List< MerchantSmsSend > lt )
						{
							try
							{
								long startTime = System.currentTimeMillis() ;
								try
								{
									merchantSmsSendService.addMerchantSmsSend( lt ) ;
								}
								finally
								{
									logger.info( "Add merchant sms send data to db use {} ms." , ( System.currentTimeMillis() - startTime ) ) ;
								}
							}
							catch ( Exception e )
							{
								logger.error( "分庫插入數據出错:" + e.getMessage() , e ) ;
							}
						}
						
						@Override
						public void run()
						{
							try
							{
								logger.debug( "Start insert data to db ....." ) ;
								logger.debug( "{}===>>>>>>>>>{}" , lt.size() , lt ) ;
								insertData( lt ) ;
							}
							catch ( Exception e )
							{
								logger.error( "Insert data to db error :" + e.getMessage() , e ) ;
							}
							
						}
					} ) ;
					
					list.clear() ;
				}
			}
			finally
			{
				logger.info( "Insert data to db size : {}." , ( i + 1 ) ) ;
			}
			
		}
	}
	
	/**
	 * @return the merchantSmsSendService
	 */
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService ;
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
	 * Create by : 2015年9月21日 下午8:44:00
	 * 
	 * @return
	 */
	private MerchantSmsSend getMSS()
	{
		String[] channelCodeArr = { "AH_UNICOM_CHANNEL001" ,
				"BJ_CT_CHANNEL001" ,
				"BJ_CT_CHANNEL002" ,
				"BJ_CT_CHANNEL003" ,
				"BJ_JTD_DX_SINGLE_CHANNEL" ,
				"BJ_JTD_LT_SINGLE_CHANNEL" ,
				"BJ_JTD_YDSW_CHANNEL001" ,
				"BJ_JTD_YDSW_CHANNEL002" ,
				"BJ_JTD_YDSW_CHANNEL003" ,
				"BJ_JTD_YD_CHANNEL001" ,
				"DG_UNICOM_CHANNEL001" ,
				"DG_UNICOM_CHANNEL002" ,
				"DG_UNICOM_CHANNEL003" ,
				"FX_YD_CHANNEL_001" ,
				"GZ_LY_DX_CHANNEL001" ,
				"GZ_LY_LT_CHANNEL001" ,
				"GZ_LY_YD_CHANNEL001" ,
				"HZ_YD_CHANNEL001" ,
				"TJ_COMM_CHANNEL001" ,
				"TJ_COMM_CHANNEL002" ,
				"TJ_COMM_CHANNEL003" ,
				"ZS_P2P_CHANNEL001" ,
				"ZS_P2P_CHANNEL002" ,
				"ZS_P2P_CHANNEL003" } ;
		
		MerchantSmsSend record = new MerchantSmsSend() ;
		int idx = channelCodeArr.length ;
		
		/**
		 * 'zhisen007','1','ADSF23A4DFAS','1234（验证码），#time#内有效，请尽快验证。【【智验】】','TJ_COMM_CHANNEL001','2015-07-06
		 * 10:56:16','13169919817',NULL,'1','0',NULL,'1','1','广东','深圳','LT',NULL,NULL
		 */
		
		int cmax = idx - 1 ;
		int max = 9999 ;
		int min = 0 ;
		Random random = new Random( System.currentTimeMillis() ) ;
		
		record.setReceiveMobile( "1" + Math.round( ( Math.random() * ( 9999999999l - 100000000l ) ) + 100000000l ) ) ;
		record.setSmsContent( ( ( random.nextInt( max ) % ( ( max - min ) + 1 ) ) + min ) + "（验证码），请尽快验证。【有道】" ) ;
		
		record.setSmsChannelCode( channelCodeArr[ ( ( random.nextInt( cmax ) % ( ( cmax - min ) + 1 ) ) + min ) ] ) ;
		record.setMerchantAccount( RandomUtil.INSTANCE.generateString( 10 ) ) ;
		record.setCreateTime( new Date() ) ;
		record.setReceiveTime( DateUtils.addSeconds( new Date() , ( random.nextInt( 3 ) % 3 ) + 1 ) ) ;
		record.setSendTime( DateFormatUtils.format( DateUtils.addSeconds( new Date() , ( random.nextInt( 3 ) % 3 ) + 1 ) , "yyyy-MM-dd HH:mm:ss" ) ) ;
		record.setReceiveStatus( random.nextInt( 1 ) % 2 ) ;
		record.setSendResult( Short.parseShort( String.valueOf( random.nextInt( 1 ) % 2 ) ) ) ;
		record.setReceiveStatusChannel( random.nextInt( max ) % ( max + 1 ) ) ;
		record.setMerchantSmsUid( RandomUtil.INSTANCE.generateLowerString( 32 ) ) ;
		record.setChannelSmsId( record.getMerchantSmsUid().substring( 20 ) ) ;
		record.setSmsSignerId( -1 ) ;
		record.setSmsTemplateId( "-1" ) ;
		record.setSmsType( 1 ) ;// 默认国内短信
		record.setResource( 1 ) ;
		record.setId( al.incrementAndGet() ) ;
		logger.debug( "{}" , record.toString() ) ;
		return record ;
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
			logger.error( "分庫插入數據出错:" + e.getMessage() , e ) ;
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
}
