/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.ListTest
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-09 15:38
 *   LastChange: 2015-10-09 15:38
 *      History:
 * </pre>
 *********************************************************************************************/
public class ListTest
{
	private int multSize = 10;
	private int idx      = 34;

	@Test public void testSubList() throws Exception
	{

		List<String> rsList = new ArrayList<String>();

		for ( int i = 0 ; i < idx ; i++ )
		{
			rsList.add( i + "" );
		}

		System.out.println( "---------------------------" );
		List<String> subList = rsList.subList( 0, 2 );
		for ( int i = 0 ; i < subList.size() ; i++ )
		{
			System.out.println( "subList.get(i) =" + subList.get( i ) + " , " + "current class = ListTest.testSubList()" );
		}
		System.out.println( "---------------------------" );

		for ( int i = 0 ; i < idx ; i++ )
		{
			System.out.println( "rsList.get(i) =" + rsList.get( i ) + " , " );
		}

		System.out.println();

		int endIdx = rsList.size();
		updateCache( null, rsList, endIdx );

	}

	private void updateCache( List<String> orsList, List<String> rsList, int endIdx )
	{
		if ( endIdx <= multSize )
		{

			for ( int i = 0 ; i < endIdx ; i++ )
			{
				String value = rsList.get( i );
				System.out.println( "value =" + value + " , " + "current class = ListTest.updateCache()" );
			}
			if ( null == orsList || orsList.isEmpty() ) return;
			else rsList = orsList;
		}

		if ( multSize < rsList.size() )
		{
			orsList = new ArrayList<String>( rsList );
			rsList = new ArrayList<String>( rsList.subList( 0, multSize - 1 ) );
			orsList.removeAll( rsList );
			endIdx = rsList.size();
		}
		else
		{
			endIdx = rsList.size();
			orsList = null;
		}

		updateCache( orsList, rsList, endIdx );

	}
}
