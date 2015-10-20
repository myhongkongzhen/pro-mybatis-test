/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import z.z.w.test.dao.es.MerchantUrlIpEntityMapper;
import z.z.w.test.entity.ex.MerchantUrlIpEntity;
import z.z.w.util.EsearchOperator;

import javax.annotation.Resource;
import java.util.List;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.util.EsearchOperatorTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-16 17:30
 *   LastChange: 2015-10-16 17:30
 *      History:
 * </pre>
 *********************************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class ) @ContextConfiguration( locations = { "classpath:spring/spring.xml" } ) public class EsearchOperatorTest
{
	private EsearchOperator           esearchOperator;
	private MerchantUrlIpEntityMapper merchantUrlIpEntityMapper;

	@Test public void testCreateIndex()
	{
		Client client = null;
		try
		{
			client = esearchOperator.getClient();
			BulkRequestBuilder builder = client.prepareBulk();
			List<MerchantUrlIpEntity> entities = merchantUrlIpEntityMapper.selectAll();
			for ( int i = 0 ; i < entities.size() ; i++ )
			{
				MerchantUrlIpEntity entity = entities.get( i );

				String toJson = toJson( entity );
				System.out.println( toJson + "===" );
				IndexRequestBuilder requestBuilder = client.prepareIndex( "MerchantUrlIp", "MerchantUrlIp" ).setSource( toJson ).setId( String.valueOf( i ) );
				builder.add( requestBuilder );
			}

			BulkResponse response = builder.execute().actionGet();
			if ( response.hasFailures() )
			{
				System.out.println( "ERROR:" + response.buildFailureMessage() );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			client.close();
		}

	}

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson( Object o )
	{
		try
		{
			return objectMapper.writeValueAsString( o );
		}
		catch ( JsonProcessingException e )
		{
			e.printStackTrace();
		}
		return "";
	}

	@Test public void testGetClient() throws Exception
	{
		for ( int i = 0 ; i < 10 ; i++ )
		{
			new Thread( new Runnable()
			{
				@Override public void run()
				{
//					System.out.println( esearchOperator + "=++==" + Thread.currentThread().getName() );
					System.out.println( esearchOperator.getClient() + "=**********===" + Thread.currentThread().getName() );
				}
			} ).start();
		}
		Thread.sleep( 4000000 );
	}

	public EsearchOperator getEsearchOperator()
	{
		return esearchOperator;
	}

	@Resource public void setEsearchOperator( EsearchOperator esearchOperator )
	{
		this.esearchOperator = esearchOperator;
	}

	public MerchantUrlIpEntityMapper getMerchantUrlIpEntityMapper()
	{
		return merchantUrlIpEntityMapper;
	}

	@Resource public void setMerchantUrlIpEntityMapper( MerchantUrlIpEntityMapper merchantUrlIpEntityMapper )
	{
		this.merchantUrlIpEntityMapper = merchantUrlIpEntityMapper;
	}
}
