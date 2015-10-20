/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.JedisTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-15 16:55
 *   LastChange: 2015-10-15 16:55
 *      History:
 * </pre>
 *********************************************************************************************/
public class JedisTest
{
	public static void main( String[] args )
	{
		JedisShardInfo jedisShardInfo = new JedisShardInfo( "ZY-REDIS-HOST", 6399, 4000000 );
		jedisShardInfo.setPassword( "hello@zhiyan.net" );
		Jedis redis = new Jedis( jedisShardInfo );


		redis.disconnect();

	}

}
