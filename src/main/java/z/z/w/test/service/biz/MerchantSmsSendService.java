package z.z.w.test.service.biz ;

import java.util.List ;
import java.util.Map ;

import javax.annotation.Resource ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.stereotype.Service ;

import z.z.w.test.dao.biz.MerchantSmsSendMapper ;
import z.z.w.test.entity.biz.MerchantSmsChannel ;
import z.z.w.test.entity.biz.MerchantSmsSend ;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.service.MerchantSmsSendService.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月8日 下午3:29:50 
 *   LastChange: 2015年9月8日 下午3:29:50 
 *      History:
 * </pre>
 **************************************************************************/
@Service
public class MerchantSmsSendService
{
	private static final Logger		logger	= LoggerFactory.getLogger( MerchantSmsSendService.class ) ;
	
	private MerchantSmsSendMapper	merchantSmsSendMapper ;
	
	public MerchantSmsSendMapper getMerchantSmsSendMapper()
	{
		return merchantSmsSendMapper ;
	}
	
	@Resource
	public void setMerchantSmsSendMapper( MerchantSmsSendMapper merchantSmsSendMapper )
	{
		this.merchantSmsSendMapper = merchantSmsSendMapper ;
	}
	
	/**
	 * Create by : 2015年9月8日 下午5:00:10
	 */
	public List< MerchantSmsSend > selectResultByCondition( Map< String, Object > params )
	{
		long startTime = System.currentTimeMillis() ;
		List< MerchantSmsSend > list = null ;
		try
		{
			list = merchantSmsSendMapper.selectResultByPage( params ) ;
			logger.debug( "根据条件查询发送记录结果： " + list ) ;
			
			if ( null != list && !list.isEmpty() )
			{
				List< MerchantSmsChannel > nlist = merchantSmsSendMapper.selectSmsSendResultByIDs( list ) ;
				logger.debug( "根据条件查询状态报告结果： " + nlist ) ;
				if ( null != nlist && !nlist.isEmpty() )
				{
					for ( MerchantSmsSend mss : list )
					{
						for ( MerchantSmsChannel msc : nlist )
						{
							if ( mss.getId().longValue() == msc.getMerchantSmsSendId().longValue() )
							{
								logger.debug( "Send記錄：" + mss.toString() ) ;
								logger.debug( "Record記錄：" + msc.toString() ) ;
								
								mss.setChannelSmsId( msc.getSendSmsCode() ) ;
								mss.setDataTime( msc.getDateTime() ) ;
								
								break ;
							}
						}
						
						if ( null == mss.getReceiveTime() ) mss.setDataTime( null ) ;
						
						if ( null != mss.getCreateTime() && null != mss.getReceiveTime() ) mss.setSendTime( String.valueOf( ( mss.getReceiveTime().getTime() - mss.getCreateTime()
																																									.getTime() ) / 1000 ) ) ;
						if ( null != mss.getCreateTime() && null != mss.getDataTime() ) mss.setStatusTime( String.valueOf( ( ( mss.getDataTime().getTime() - mss.getCreateTime()
																																								.getTime() ) / 1000 ) ) ) ;
					}
				}
			}
			
			logger.debug( "==>根据条件查询发送记录结果： " + list ) ;
		}
		catch ( Exception e )
		{
			logger.error( "按短信发送记录ID查询对应状态报告信息出错:" + e.getMessage() , e ) ;
		}
		finally
		{
			logger.info( "按条件查询发送记录数据共用时：" + ( ( System.currentTimeMillis() - startTime ) ) + "ms." ) ;
		}
		
		return list ;
	}
	
	/**
	 * Create by : 2015年9月9日 上午11:41:02
	 */
	public int getCount( Map< String, Object > params )
	{
		logger.info( "查询（总数）条件：" + params ) ;
		return merchantSmsSendMapper.getCount( params ) ;
	}
	
	/**
	 * Create by : 2015年9月17日 下午2:31:43
	 */
	public void addMerchantSmsSend( MerchantSmsSend mss )
	{
		merchantSmsSendMapper.insertSelective( mss ) ;
	}
	
	/**
	 * Create by : 2015年9月21日 下午4:06:18
	 * 
	 * @return
	 */
	public long getMaxId()
	{
		return merchantSmsSendMapper.getMaxId() ;
	}
}
