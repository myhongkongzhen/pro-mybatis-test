package z.z.w.test.service ;

import org.junit.Test ;
import org.junit.runner.RunWith ;
import org.springframework.test.context.ContextConfiguration ;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner ;
import z.z.w.test.entity.TbTestUser ;

import javax.annotation.Resource ;
import java.util.ArrayList ;
import java.util.List ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.pro-module
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-09-30 12:19
 *   LastChange: 2015-09-30 12:19
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class RedisServiceTest
{
	private RedisService	redisService ;
	
	/**
	 * @return
	 */
	public RedisService getRedisService()
	{
		return redisService ;
	}
	
	/**
	 * @param redisService
	 */
	@Resource
	public void setRedisService( RedisService redisService )
	{
		this.redisService = redisService ;
	}
	
	@Test
	public void testGet() throws Exception
	{	
		
	}
	
	@Test
	public void testAdd() throws Exception
	{
		List< TbTestUser > list = new ArrayList< TbTestUser >() ;
		for ( int i = 10 ; i < 50000 ; i++ )
		{
			TbTestUser user = new TbTestUser() ;
			user.setId( Long.getLong( String.valueOf( i ) ) ) ;
			user.setMerchantSmsUid( "redis_test" + i ) ;
			list.add( user ) ;
		}
		long begin = System.currentTimeMillis() ;
//		redisService.add( list ) ;
		System.out.println( System.currentTimeMillis() - begin ) ;
	}
	
}
