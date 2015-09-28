package z.z.w.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import z.z.w.util.SpringContextUtil;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.util.SpringContextUtilTest.java
 *         Desc: 
 *      &#64;author: Z_Z.W - myhongkongzhen@gmail.com
 *     &#64;version: 2015年9月7日 下午4:12:55 
 *   LastChange: 2015年9月7日 下午4:12:55 
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class SpringContextUtilTest
{
	private static final Logger logger = LoggerFactory.getLogger( SpringContextUtilTest.class );
	
	/**
	 * Test method for {@link z.z.w.test.util.SpringContextUtil#getSpringContext()}.
	 */
	@Test
	public void testGetSpringContext()
	{
		logger.info( "@@{}" , SpringContextUtil.getApplicationContext() );
	}
	
}
