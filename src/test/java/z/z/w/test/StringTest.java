/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.StringTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-22 10:01
 *   LastChange: 2015-10-22 10:01
 *      History:
 * </pre>
 *********************************************************************************************/
public class StringTest
{

	@Test public void testSplit()
	{
		String ss = "df==";
		String[] s = StringUtils.splitByWholeSeparator( ss, "==" );
//		String[] s = StringUtils.splitPreserveAllTokens( ss, "==" );

		System.out.println( s.length );
		System.out.println( s[ 0 ] );
		System.out.println( s[ 1 ] );
	}

	@Test public void testMap() throws Exception
	{
//		for ( int i = 0 ; i < 10 ; i++ )
//		{
//			new Thread( new Runnable()
//			{
//				@Override public void run()
//				{
		Map<String, String> monitorData = new LinkedHashMap<String, String>();
		Map<String, String> map = Collections.synchronizedMap( monitorData );

		for ( int i = 0 ; i < 24 ; i++ )
		{
			String key = "";
			if ( i < 10 ) key = "0" + i + ":";
			else key = i + ":";

			for ( int j = 0 ; j < 51 ; j++ )
			{
				if ( j % 10 == 0 )
				{
					if ( j == 0 ) map.put( key + j + "0:00", "" );
					else map.put( key + j + ":00", "" );
				}
			}

		}

		System.out.println( map.size() );

		for ( Map.Entry<String, String> entry : map.entrySet() )
		{
			System.out.println( entry.getKey() + "===" + entry.getValue() + "-------------" + Thread.currentThread().getName() );
		}
//				}
//			} ).start();
//		}
//
//		Thread.sleep( 400000000 );

	}

	@Test public void testString() throws Exception
	{

		String str = "09:00==1351,";
//		String str = "09:00==1351,10:00==1277,11:00==1252,12:00==1227,13:00==1068";
//		System.out.println( StringUtils.containsIgnoreCase( str, ":" ) );
		String[] array = StringUtils.splitByWholeSeparator( str, "," );
		System.out.println( array.length );
		System.out.println( ArrayUtils.toString( array ) );

	}
}
