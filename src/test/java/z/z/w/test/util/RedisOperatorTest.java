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
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	@Test public void testDelKeys() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			ShardedJedisPipeline p = redis.pipelined();

			Response<Long>
					response =
					p.hdel( "ZHIYAN::STATUS::ACCOUNT::SMSID", new String[] { "558a66b1" , "bbdb666f" , "5dd58903" , "3bba6f48" , "c9011e69" } );
			p.syncAndReturnAll();

			Long aLong = response.get();
			System.out.println( "aLong =" + aLong + " , " + "current class = RedisOperatorTest.testDelKeys()" );

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

	@Test public void testPipGetKeysValue() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			ShardedJedisPipeline p = redis.pipelined();

			setValues( p );

//			getValues( p );

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

	private void setValues( ShardedJedisPipeline p )
	{
		Map<String, String> map = new HashMap<>();
		map.put( "1", "1" );
		map.put( "2", "2" );
		map.put( "3", "3" );
		map.put( "4", "4" );
		map.put( "5", "5" );
		p.hmset( "test_123", map );
		p.syncAndReturnAll();
	}

	private void getValues( ShardedJedisPipeline p )
	{
		Response<List<String>> response = p.hmget( "test_123", new String[] { "6" , "2" , "4" ,"9", "3" , "1" , "5" } );
		p.syncAndReturnAll();

		List<String> list = response.get();
		for ( String value : list )
		{
			System.out.println( "value =" + value + " , " + "current class = RedisOperatorTest.testPipGetKeysValue()" );

		}
	}

	@Test public void testSortSet() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			ShardedJedisPipeline p = redis.pipelined();

//			setSortSet( p );

//			delSortSet( p );

			getSortSet( p );

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

	private void getSortSet( ShardedJedisPipeline p )
	{
		/**
		 *  #-inf表示第一个成员，+inf表示最后一个成员，limit后面的参数用于限制返回成员的自己，
		 #2表示从位置索引(0-based)等于2的成员开始，去后面3个成员。
		 redis 127.0.0.1:6379> zrangebyscore myzset -inf +inf limit 2 3
		 */
		Response<Set<Tuple>> ssTest = p.zrangeWithScores( "ss_test", 0, 2 );
		p.syncAndReturnAll();
		Set<Tuple> tuples = ssTest.get();
		for ( Tuple tuple : tuples )
		{
			System.out.println( tuple.getElement() );
		}
	}

	private void delSortSet( ShardedJedisPipeline p )
	{
		Response<Long> ssTest = p.zrem( "ss_test", new String[] { "test0" , "test1" } );
		p.syncAndReturnAll();
		System.out.println( ssTest.get() );
	}

	private void setSortSet( ShardedJedisPipeline p )
	{
		Map<String, Double> scMap = new HashMap<String, Double>();
		for ( int i = 0 ; i < 10 ; i++ )
		{
			scMap.put( "test" + i, i * 10.0 );
		}

		Response<Long> ssTest = p.zadd( "ss_test_05", scMap );
		p.syncAndReturnAll();
		System.out.println( ssTest.get() );
	}

	@Test public void testDelList() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			ShardedJedisPipeline p = redis.pipelined();
			Set<String> keys = redis.getShard( "" ).keys( "*SMSID" );
			for ( String key : keys )
			{
				p.del( key );
			}

//			Response<String> testList = p.ltrim( "test_list", 3, -1 );
			p.syncAndReturnAll();

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

	@Test public void testGetList() throws Exception
	{

		ShardedJedis redis = getRedisClient();
		try
		{
			getListRlt( redis );

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

	private void getListRlt( ShardedJedis redis )
	{
		ShardedJedisPipeline p = redis.pipelined();

		Response<List<String>> testList = p.lrange( "test_list", 0, 2 );
		p.syncAndReturnAll();

		List<String> stringList = testList.get();
		for ( String str : stringList )
		{
			System.out.println( str );
		}
	}

	@Test public void testGetHoshi() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			Set<String> ssTest = redis.getShard( "test_lpush_list_" ).keys( "test_lpush_list_*" );
			for ( String s : ssTest )
			{
				System.out.println( s );
			}

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

	@Test public void testListInsert() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{

			long startTime = System.currentTimeMillis();

//			for ( int i = 0 ; i < 10000 ; i++ )
//			{
//				redis.lpush( "test+hoshi_" + i, new String[] { "test" + i } );
//			}
//
//			System.out.println( "nopipe use : " + ( System.currentTimeMillis() - startTime ) );

			startTime = System.currentTimeMillis();

			ShardedJedisPipeline p = redis.pipelined();
//			insertList( p );
			for ( int i = 200 ; i < 400 ; i++ )
			{
				for ( int j = 0 ; j < 10000 ; j++ )
				{
					p.lpush( "hoshi_" + i, new String[] { "test_" + j } );
				}
			}

			p.syncAndReturnAll();
			System.out.println( "use : " + ( System.currentTimeMillis() - startTime ) );

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

	private void insertList( ShardedJedisPipeline p )
	{
		String[] strings = new String[ 10 ];
		for ( int i = 0 ; i < 10 ; i++ )
		{
			strings[ i ] = "test_" + i;
		}
		p.rpush( "test_lpush_list_05", strings );
	}

	@Test public void testList() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			ShardedJedisPipeline p = redis.pipelined();

			String[] strings = new String[ 10 ];
			for ( int i = 0 ; i < 10 ; i++ )
			{
				strings[ i ] = "test_list_value_list_" + i;
//				strings[ i ] = "test_list_value_" + i;
			}
			// rpush 在列表尾部添加
			Response<Long> response = p.rpush( "test_list", strings );
			// lpush 在列表頭部添加
//			Response<Long> response = p.lpush( "test_list", strings );
			p.syncAndReturnAll();

			System.out.println( "response = " + response.get() );
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

	@Test public void testAll() throws Exception
	{
		ShardedJedis redis = getRedisClient();

		Map<String, String> data = new HashMap<String, String>();
		//hmset
		long start = System.currentTimeMillis();
		//直接hmset
		for ( int i = 0 ; i < 10000 ; i++ )
		{
			data.put( "k_" + i, "v_" + i );
		}
		redis.hmset( "key", data );
		long end = System.currentTimeMillis();
		System.out.println( "dbsize:[" + redis.hlen( "key" ) + "] .. " );
		System.out.println( "hmset without pipeline used [" + ( end - start ) + "] seconds .." );
		//使用pipeline hmset
		ShardedJedisPipeline p = redis.pipelined();
		start = System.currentTimeMillis();
		data.clear();
		for ( int i = 0 ; i < 10000 ; i++ )
		{
			data.put( "k_" + i, "v_" + i );
		}
		p.hmset( "key_pipe", data );
		p.syncAndReturnAll();
		end = System.currentTimeMillis();
		System.out.println( "dbsize:[" + redis.hlen( "key_pipe" ) + "] .. " );
		System.out.println( "hmset with pipeline used [" + ( end - start ) + "] seconds .." );

		//hmget
		//直接使用Jedis hgetall
		start = System.currentTimeMillis();
		Map<String, String> map = redis.hgetAll( "key" );
		end = System.currentTimeMillis();
		System.out.println( "result size:[" + map.size() + "] .." );
		System.out.println( "hgetAll without pipeline used [" + ( end - start ) + "] seconds .." );

		//使用pipeline hgetall
		start = System.currentTimeMillis();
		Response<Map<String, String>> reMap = p.hgetAll( "key_pipe" );
		System.out.println( "=====>>>>" + reMap );
		p.sync();
		map.clear();
		map = reMap.get();
		end = System.currentTimeMillis();
		System.out.println( "result size:[" + map.size() + "] .." );
		System.out.println( "hgetAll with pipeline used [" + ( end - start ) + "] seconds .." );

	}

	@Test public void testGetall() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			//直接使用Jedis hgetall
			long start = System.currentTimeMillis();
			Map<String, String> result = redis.hgetAll( "ZHIYAN::STATUS::ACCOUNT::SMSID" );
			System.out.println( result.size() + "===========" );
			long end = System.currentTimeMillis();
			System.out.println( "result size:[" + result.size() + "] .." );
			System.out.println( "hgetAll without pipeline used [" + ( end - start ) + "] seconds .." );

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

	@Test public void testGetAll() throws Exception
	{
		ShardedJedis redis = getRedisClient();
		try
		{
			ShardedJedisPipeline p = redis.pipelined();
			//使用pipeline hgetall

			long start = System.currentTimeMillis();
			Response<Map<String, String>> responses = p.hgetAll( "ZHIYAN::STATUS::ACCOUNT::SMSID" );
			p.sync();
			Map<String, String> result = responses.get();
			System.out.println( result.size() + "===========" );
			long end = System.currentTimeMillis();
			System.out.println( "result size:[" + result.size() + "] .." );
			System.out.println( "hgetAll with pipeline used [" + ( end - start ) + "] seconds .." );

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

	@Test public void testAllGetTest() throws Exception
	{

		this.testGetall();
		System.out.println( "==================================" );
		this.testGetAll();

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

			ShardedJedisPipeline p = redis.pipelined();

//			data.put( "74386746486091", "743867464486091|13718249651|111|下发失敗|1|2011-03-19 24:50:00" );
//			data.put( "143867464287031","143867464287031|13839399808|0|下发成功|0|2015-03-19 05:50:00" );

//			data.put( "14386746487031", "chuzhong" );
//			data.put( "143867464287031", "meiyjie" );
//			data.put( "74386746486091", "chuzhong" );
//			data.put( "743867464486091", "meiyjie" );
//			p.hmset( "ZHIYAN::STATUS::ACCOUNT::SMSID", data );

//			data = new HashMap<String, String>();
//			data.put( "14386746487031", UUID.randomUUID().toString().replace( "-", "" ) );
//			data.put( "143867464287031", UUID.randomUUID().toString().replace( "-", "" ) );
//			data.put( "74386746486091", UUID.randomUUID().toString().replace( "-", "" ) );
//			data.put( "743867464486091", UUID.randomUUID().toString().replace( "-", "" ) );
//			p.hmset( "ZHIYAN::STATUS::UUID::SMSID", data );

			data = new HashMap<String, String>();
			data.put( "chuzhong", "http://113.31.87.136:9111/status/test/push" );
			data.put( "meiyjie", "http://113.31.87.136:9111/status/test/push" );
			p.hmset( "ZHIYAN::STATUS::ACCOUNT::IP", data );

			data = new HashMap<String, String>();
			data.put( "chuzhong", "http://113.31.87.136:9111/status/test/push" );
			data.put( "meiyjie", "http://113.31.87.136:9111/status/test/push" );
			p.hmset( "ZHIYAN::STATUS::ACCOUNT::IP_VALID", data );

			data = new HashMap<String, String>();
			data.put( "chuzhong", "http://113.31.87.136:9111/status/test/push" );
			data.put( "meiyjie", "http://113.31.87.136:9111/status/test/push" );
			p.hmset( "ZHIYAN::STATUS::ACCOUNT::URL", data );

			p.syncAndReturnAll();

			long end = System.currentTimeMillis();
			System.out.println( "hmset with pipeline used [" + ( end - start ) + "] hms .." );

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
			System.out.println( shardedJedisPool + "====" );
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
