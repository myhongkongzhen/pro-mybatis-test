package z.z.w.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.BaseJUnitTest.java
 *         Desc: 
 *      &#64;author: Z_Z.W - myhongkongzhen@gmail.com
 *     &#64;version: 2015年8月27日 上午11:44:23 
 *   LastChange: 2015年8月27日 上午11:44:23 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class BaseJUnitTest
{
	private static Logger logger = LoggerFactory.getLogger( BaseJUnitTest.class );
	
	@Test
	public void test()
	{
		logger.info( "This is test." );
	}
	
}
