/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.util;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Response;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.util.RedisOperatorTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-09 10:09
 *   LastChange: 2015-10-09 10:09
 *      History:
 * </pre>
 *********************************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class ) @ContextConfiguration( locations = { "classpath:spring/spring.xml" } ) public class RedisOperatorTest
{
	final int size = 300;
	private ShardedJedisPool shardedJedisPool;

	@After public void tearDown() throws Exception
	{
		try
		{
			shardedJedisPool.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			shardedJedisPool.destroy();
		}
	}

	/**
	 * 用時 6min28s 、、、、
	 */
	@Test public void testSetex()
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );

			Map<String, String> data = new HashMap<String, String>();

			//hmset 
			long start = System.currentTimeMillis();
			//直接hmset
			for ( int i = 0 ; i < size ; i++ )
			{
				data.put( "k_" + i, "v_" + i );
			}
			redis.hmset( "account_smsig", data );
			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms .." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}
	}

	@Test public void testSet() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );

			Map<String, String> data = new HashMap<String, String>();

			long start = System.currentTimeMillis();
//			data.put( "74386746486091", "743867464486091|13718249651|111|下发失敗|1|2011-03-19 24:50:00" );
//			data.put( "143867464287031","143867464287031|13839399808|0|下发成功|0|2015-03-19 05:50:00" );

			data.put( "14386746487031", "chuzhong" );
			data.put( "143867464287031", "meiyjie" );
			data.put( "74386746486091", "chuzhong" );
			data.put( "743867464486091", "meiyjie" );
			redis.hmset( "ZHIYAN::STATUS::ACCOUNT::SMSID", data );

			data = new HashMap<String, String>();
			data.put( "14386746487031", UUID.randomUUID().toString().replace( "-", "" ) );
			data.put( "143867464287031", UUID.randomUUID().toString().replace( "-", "" ) );
			data.put( "74386746486091", UUID.randomUUID().toString().replace( "-", "" ) );
			data.put( "743867464486091", UUID.randomUUID().toString().replace( "-", "" ) );
			redis.hmset( "ZHIYAN::STATUS::UUID::SMSID", data );

			data = new HashMap<String, String>();
			data.put( "chuzhong", "http://113.31.87.136:9111/status/test/push" );
			data.put( "meiyjie", "http://113.31.87.136:9111/status/test/push" );
			redis.hmset( "ZHIYAN::STATUS::ACCOUNT::URL", data );

			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms .." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}

	}

	/**
	 * 簡直就是秒殺啊！！！！！！！！！！！
	 *
	 * @throws Exception
	 */
	@Test public void testPipleLine() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );
			ShardedJedisPipeline pipeline = redis.pipelined();
			Map<String, String> data = new HashMap<String, String>();

			//hmset
			long start = System.currentTimeMillis();
			//直接hmset
			for ( int i = 0 ; i < size ; i++ )
			{
				data.put( "k_" + i, "v_0" + i );
			}
			Response<String> rs = pipeline.hmset( "account_uuid", data );
			pipeline.sync();
			System.out.println( "rs.get() =" + rs.get() + " , " + "current class = RedisOperatorTest.testPipleLine()" );
			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms.." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}

	}

	@Test public void testGet() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );
			ShardedJedisPipeline pipeline = redis.pipelined();

			long start = System.currentTimeMillis();

			/**
			 * redis.hmset( "ZHIYAN::STATUS::ACCOUNT::SMSID", data );
			 data = new HashMap<String, String>();
			 data.put( "74386746486091", UUID.randomUUID().toString().replace( "-","" ) );
			 data.put( "143867464287031",UUID.randomUUID().toString().replace( "-","" ));
			 redis.hmset( "ZHIYAN::STATUS::UUID::SMSID", data );
			 */
			Response<String> response = pipeline.hget( "ZHIYAN::STATUS::UUID::SMSID", "743867464486091" );
			pipeline.sync();
			String result = response.get();
			System.out.println( "result =" + result + " , " + "current class = RedisOperatorTest.testGet()" );
			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms.." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}

	}

	@Test public void testLen() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );
			ShardedJedisPipeline pipeline = redis.pipelined();

			long start = System.currentTimeMillis();
			Response<Long> response = pipeline.hlen( "account_smsig" );
			pipeline.sync();
			Long result = response.get();
			System.out.println( "result =" + result + " , " + "current class = RedisOperatorTest.testGet()" );
			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms.." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}
	}

	@Test public void testDelete() throws Exception
	{
		ShardedJedis redis = getRedisClient();

		try
		{
			System.out.println( "redis =" + redis + " , " + "current class = RedisOperatorTest.setUp()" );
			ShardedJedisPipeline pipeline = redis.pipelined();
			Map<String, String> data = new HashMap<String, String>();

			//hmset
			long start = System.currentTimeMillis();
			//直接hmset
			for ( int i = 0 ; i < size ; i++ )
			{
				data.clear();
				data.put( "k_" + i, "v_" + i );
				pipeline.del( "key_" + i );
			}
			pipeline.del( "ZHIYAN" );
			pipeline.sync();
			long end = System.currentTimeMillis();
			System.out.println( "hmset without pipeline used [" + ( end - start ) + "] hms .." );

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			redis.disconnect();
		}

	}

	public ShardedJedis getRedisClient()
	{
		try
		{
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}

	public ShardedJedisPool getShardedJedisPool()
	{
		return shardedJedisPool;
	}

	@Resource public void setShardedJedisPool( ShardedJedisPool shardedJedisPool )
	{
		this.shardedJedisPool = shardedJedisPool;
	}
}
