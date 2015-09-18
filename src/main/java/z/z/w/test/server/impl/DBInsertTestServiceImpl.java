package z.z.w.test.server.impl ;

import java.util.Date ;
import java.util.Random ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.atomic.AtomicLong ;

import javax.annotation.Resource ;

import org.apache.commons.lang3.time.DateFormatUtils ;
import org.apache.commons.lang3.time.DateUtils ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.stereotype.Service ;

import z.z.w.test.entity.biz.MerchantSmsSend ;
import z.z.w.test.server.IServiceLoader ;
import z.z.w.test.service.biz.MerchantSmsSendService ;
import z.z.w.util.SpringContextUtil ;
import z.z.w.util.comm.RandomUtil ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.DBInsertTestServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月17日 下午2:28:37 
 *   LastChange: 2015年9月17日 下午2:28:37 
 *      History:
 * </pre>
 **************************************************************************/
@Service
public class DBInsertTestServiceImpl implements IServiceLoader
{
	final static Logger				logger	= LoggerFactory.getLogger( DBInsertTestServiceImpl.class ) ;
	
	private final AtomicLong		al		= new AtomicLong( 1000000l ) ;
	private ExecutorService			service	= Executors.newFixedThreadPool( 6 ) ;
	
	private MerchantSmsSendService	merchantSmsSendService ;
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#loadService()
	 */
	@Override
	public void loadService()
	{
		merchantSmsSendService = SpringContextUtil.getBean( MerchantSmsSendService.class ) ;
		
		while ( true )
		{
			service.execute( new Runnable()
			{
				@Override
				public void run()
				{
					long startTime = System.currentTimeMillis() ;
					try
					{
						logger.info( "Start insert data to db ....." ) ;
						insertData() ;
					}
					catch ( Exception e )
					{
						logger.error( "Insert data to db error :" + e.getMessage() , e ) ;
					}
					finally
					{
						logger.info( "Insert data to db use {} ms." , ( System.currentTimeMillis() - startTime ) ) ;
					}
				}
			} ) ;
			
		}
	}
	
	/**
	 * Create by : 2015年9月18日 下午12:31:05
	 */
	private void insertData()
	{
		try
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
			
			record.setReceiveMobile( "1" + Math.round( Math.random() * ( 9999999999l - 100000000l ) + 100000000l ) ) ;
			record.setSmsContent( ( random.nextInt( max ) % ( max - min + 1 ) + min ) + "（验证码），请尽快验证。【有道】" ) ;
			
			record.setSmsChannelCode( channelCodeArr[ ( random.nextInt( cmax ) % ( cmax - min + 1 ) + min ) ] ) ;
			record.setMerchantAccount( RandomUtil.INSTANCE.generateString( 10 ) ) ;
			record.setCreateTime( new Date() ) ;
			record.setReceiveTime( DateUtils.addSeconds( new Date() , random.nextInt( 3 ) % 3 + 1 ) ) ;
			record.setSendTime( DateFormatUtils.format( DateUtils.addSeconds( new Date() , random.nextInt( 3 ) % 3 + 1 ) , "yyyy-MM-dd HH:mm:ss" ) ) ;
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
			logger.info( "{}" , record.toString() ) ;
			
			merchantSmsSendService.addMerchantSmsSend( record ) ;
		}
		catch ( Exception e )
		{
			logger.error( "分庫插入數據出错:" + e.getMessage() , e ) ;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#destroy()
	 */
	@Override
	public void destroy()
	{
		// TODO 2015年9月17日 下午2:28:37
		Thread.currentThread().interrupt() ;
	}
	
	/**
	 * @return the merchantSmsSendService
	 */
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService ;
	}
	
	/**
	 * @param merchantSmsSendService the merchantSmsSendService to set
	 */
	@Resource
	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService ;
	}
	
}
