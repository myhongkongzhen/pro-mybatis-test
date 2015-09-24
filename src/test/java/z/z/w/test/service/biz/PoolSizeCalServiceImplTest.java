package z.z.w.test.service.biz ;

import javax.annotation.Resource ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.PoolSizeCalServiceImplTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月24日 上午9:45:20 
 *   LastChange: 2015年9月24日 上午9:45:20 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class PoolSizeCalServiceImplTest
{
	final static Logger				logger	= LoggerFactory.getLogger( PoolSizeCalServiceImplTest.class ) ;
	private PoolSizeCalServiceImpl	poolSizeCalServiceImpl ;
	
	/**
	 * @return the poolSizeCalServiceImpl
	 */
	public PoolSizeCalServiceImpl getPoolSizeCalServiceImpl()
	{
		return poolSizeCalServiceImpl ;
	}
	
	/**
	 * @param poolSizeCalServiceImpl
	 *            the poolSizeCalServiceImpl to set
	 */
	@Resource
	public void setPoolSizeCalServiceImpl( PoolSizeCalServiceImpl poolSizeCalServiceImpl )
	{
		this.poolSizeCalServiceImpl = poolSizeCalServiceImpl ;
	}
	
	/**
	 * Test method for {@link z.z.w.test.service.biz.PoolSizeCalServiceImpl#execute()}.
	 */
	@Test
	public void testExecute()
	{
		try
		{
			logger.info( "{}" , poolSizeCalServiceImpl ) ;
			poolSizeCalServiceImpl.execute() ;
		}
		catch ( Exception e )
		{
			logger.error( "DB insert service error : {}." , e.getMessage() , e ) ;
		}
	}
	
}
