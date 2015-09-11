package z.z.w.test.biz.server ;

import static org.mockito.Mockito.mock ;
import static org.mockito.Mockito.when ;

import java.util.HashMap ;
import java.util.List ;
import java.util.Map ;

import javax.annotation.Resource ;
import javax.servlet.http.HttpServletRequest ;

import org.apache.commons.lang3.StringUtils ;
import org.junit.Before ;
import org.junit.Ignore ;
import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import z.z.w.test.entity.biz.MerchantSmsSend ;
import z.z.w.test.service.biz.MerchantSmsSendService ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.biz.server.MerchantSmsSendServiceTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月10日 下午5:22:38 
 *   LastChange: 2015年9月10日 下午5:22:38 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class MerchantSmsSendServiceTest
{
	private static final Logger		logger	= LoggerFactory.getLogger( MerchantSmsSendServiceTest.class ) ;
	private MerchantSmsSendService	merchantSmsSendService ;
	Map< String, Object >			params	= new HashMap< String, Object >() ;
	
	@Before
	public void setUp()
	{
		HttpServletRequest req = mock( HttpServletRequest.class ) ;
		logger.info( "REQ : " + req ) ;
		
		when( req.getParameter( "page" ) ).thenReturn( "1" ) ;
		String pag = req.getParameter( "page" ) ;
		when( req.getParameter( "pageSize" ) ).thenReturn( "3" ) ;
		String pageSi = req.getParameter( "pageSize" ) ;
		
		when( req.getParameter( "chanelId" ) ).thenReturn( "" ) ;
		String chanelId = req.getParameter( "chanelId" ) ;
		
		when( req.getParameter( "startTime" ) ).thenReturn( "2015-09-09" ) ;
		String startTime = req.getParameter( "startTime" ) ;
		
		when( req.getParameter( "endTime" ) ).thenReturn( "" ) ;
		String endTime = req.getParameter( "endTime" ) ;
		
		when( req.getParameter( "merchantAccount" ) ).thenReturn( "" ) ;
		String merchantAccount = req.getParameter( "merchantAccount" ) ;
		
		when( req.getParameter( "mobile" ) ).thenReturn( "" ) ;
		String mobile = req.getParameter( "mobile" ) ;
		
		when( req.getParameter( "sendResult" ) ).thenReturn( "0" ) ;
		String sendResult = req.getParameter( "sendResult" ) ;
		
		when( req.getParameter( "receiveStatus" ) ).thenReturn( "" ) ;
		String receiveStatus = req.getParameter( "receiveStatus" ) ;
		
		params.put( "pageNum", ( Integer.parseInt( pag ) - 1 ) * Integer.parseInt( pageSi ) ) ;
		params.put( "pageSize", Integer.parseInt( pageSi ) ) ;
		params.put( "startDate", startTime ) ;
		params.put( "endDate", endTime ) ;
		params.put( "merchantAccount", merchantAccount ) ;
		params.put( "receiveMobile", mobile ) ;
		params.put( "smsChannelCode", chanelId ) ;
		params.put( "sendResult", ( StringUtils.isBlank( sendResult ) ? sendResult : Integer.parseInt( sendResult ) ) ) ;
		params.put( "receiveStatus", ( StringUtils.isBlank( receiveStatus ) ? receiveStatus : Integer.parseInt( receiveStatus ) ) ) ;
		
		logger.info( "发送记录查询参数：{}", params ) ;
	}
	
	@Test
	@Ignore
	public void testConnection()
	{
		logger.info( "{}", merchantSmsSendService ) ;
		
		MerchantSmsSend merchantSmsSend = new MerchantSmsSend() ;
		merchantSmsSend.setId( 10002909l ) ; ;
		
		logger.info( "{}", merchantSmsSendService.getMerchantSmsSendMapper().selectSmsSendResultByID( merchantSmsSend ) ) ;
	}
	
	/**
	 * Test method for {@link z.z.w.test.service.biz.MerchantSmsSendService#selectResultByCondition(java.util.Map)}.
	 */
	@Test
//	@Ignore
			public void
			testSelectResultByCondition()
	{
		List< MerchantSmsSend > list = merchantSmsSendService.selectResultByCondition( params ) ;
		for ( MerchantSmsSend mss : list )
		{
			logger.info( "{}", mss.toString() ) ;
		}
	}
	
	/**
	 * Test method for {@link z.z.w.test.service.biz.MerchantSmsSendService#selectResultByConditionOd(java.util.Map)}.
	 */
	@Test
	@Ignore
	public void testSelectResultByConditionOd()
	{
		List< MerchantSmsSend > list = merchantSmsSendService.selectResultByConditionOd( params ) ;
		for ( MerchantSmsSend mss : list )
		{
			logger.info( "{}", mss.toString() ) ;
		}
	}
	
	/**
	 * Test method for {@link z.z.w.test.service.biz.MerchantSmsSendService#getCount(java.util.Map)}.
	 */
	@Test
	@Ignore
	public void testGetCount()
	{
		logger.info( "Count-->{}", merchantSmsSendService.getCount( params ) ) ;
	}
	
	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService ;
	}
	
	@Resource
	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService ;
	}
}
