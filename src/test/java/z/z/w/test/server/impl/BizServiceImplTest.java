package z.z.w.test.server.impl ;

import javax.annotation.Resource ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import z.z.w.test.service.biz.BizServiceImpl ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.BizServiceImplTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月14日 下午6:30:04 
 *   LastChange: 2015年9月14日 下午6:30:04 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class BizServiceImplTest
{
	final static Logger		logger	= LoggerFactory.getLogger( BizServiceImplTest.class ) ;
	
	private BizServiceImpl	bizServiceImpl ;
	
	/**
	 * @return the bizServiceImpl
	 */
	public BizServiceImpl getBizServiceImpl()
	{
		return bizServiceImpl ;
	}
	
	/**
	 * @param bizServiceImpl
	 *            the bizServiceImpl to set
	 */
	@Resource
	public void setBizServiceImpl( BizServiceImpl bizServiceImpl )
	{
		this.bizServiceImpl = bizServiceImpl ;
	}
	
	/**
	 * Test method for {@link z.z.w.test.service.BizServiceImpl#loadService()}.
	 */
	@Test
	public void testLoadService()
	{
		try
		{
//			bizServiceImpl = SpringContextUtil.getBean( BizServiceImpl.class ) ;
			bizServiceImpl.execute() ;
		}
		catch ( Exception e )
		{
			logger.error( "Loading bussiness error : {}." , e.getMessage() , e ) ;
		}
	}
	
}
