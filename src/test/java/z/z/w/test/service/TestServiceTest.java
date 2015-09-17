/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.TestServiceTest.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2014-10-14 上午10:43:27 
 *   LastChange: 2014-10-14 上午10:43:27 
 *      History:
 * </pre>
 **************************************************************************/
package z.z.w.test.service ;

import static org.junit.Assert.fail ;

import java.util.List ;

import javax.annotation.Resource ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;

import z.z.w.test.entity.TbTestUser ;

/**
 * z.z.w.test.service.TestServiceTest.java
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class TestServiceTest
{
	
	private static Logger	logger		= LoggerFactory.getLogger( TestServiceTest.class ) ;
	private TestService		testService	= null ;
	
	/**
	 * Test method for {@link z.z.w.test.service.TestService#queryAllUsers()}.
	 */
	@Test
	public void testQueryAllUsers()
	{
		try
		{
			List< TbTestUser > list = testService.queryAllUsers() ;
			for ( TbTestUser ttu : list )
			{
				logger.info( "{}", ttu.toString() ) ;
			}
		}
		catch ( Exception e )
		{
			fail( "MASSAGE : " + e.getMessage() + "\nCAUSE : " + e.getCause() + "\nCLASS : " + e.getClass() ) ;
		}
	}
	
	/**
	 * <br>
	 * Created on: 2014-10-14 上午10:45:21
	 * 
	 * @return the testService
	 */
	public TestService getTestService()
	{
		return testService ;
	}
	
	/**
	 * <br>
	 * Created on: 2014-10-14 上午10:45:21
	 * 
	 * @param testService
	 *            the testService to set
	 */
	@Resource
	public void setTestService( TestService testService )
	{
		this.testService = testService ;
	}
	
}
