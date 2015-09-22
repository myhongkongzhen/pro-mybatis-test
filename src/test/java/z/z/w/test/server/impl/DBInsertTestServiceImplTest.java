package z.z.w.test.server.impl ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import z.z.w.util.SpringContextUtil ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.DBInsertTestServiceImplTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月17日 下午4:15:48 
 *   LastChange: 2015年9月17日 下午4:15:48 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class DBInsertTestServiceImplTest
{
	final static Logger				logger	= LoggerFactory.getLogger( DBInsertTestServiceImplTest.class ) ;
	private DBInsertTestServiceImpl	dBInsertTestServiceImpl ;
	
	/**
	 * Test method for {@link z.z.w.test.server.impl.DBInsertTestServiceImpl#loadService()}.
	 */
	@Test
	public void testLoadService()
	{
		try
		{
			dBInsertTestServiceImpl = SpringContextUtil.getBean( DBInsertTestServiceImpl.class ) ;
			logger.info( "{}" , dBInsertTestServiceImpl ) ;
			dBInsertTestServiceImpl.loadService() ;
		}
		catch ( Exception e )
		{
			logger.error( "DB insert service error : {}." , e.getMessage() , e ) ;
		}
	}
	
}
