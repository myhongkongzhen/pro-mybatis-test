/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.machen.Composite;

import java.io.*;

public class FileLeaf extends FileComponent
{

	public FileLeaf( String fileName )
	{
		super( fileName );
	}

	@Override public void displayContent()
	{
		BufferedReader br = null;
		try
		{
			File displayFile = new File( fileName );
			br = new BufferedReader( new InputStreamReader( new BufferedInputStream( new FileInputStream( displayFile ) ) ) );
			String s = null;
			while ( null != ( s = br.readLine() ) )
			{
				System.out.println( s );
			}

		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( null != br )
			{
				try
				{
					br.close();
					br = null;
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
		}
		System.out.println( "------------------------------------" );
	}

	@Override public void showName( int level )
	{
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < level ; i++ )
		{
			sb.append( "-" );
		}
		sb.append( "文件:" ).append( fileName );
		System.out.println( sb.toString() );
	}

	@Override public int getCount( int initNumber )
	{
		return initNumber + 1;
	}

	@Override public int getCountExcludeDirectory( int initNumber )
	{
		return initNumber + 1;
	}

	@Override public void delFileByName( String fileName, boolean compelDel )
	{
		if ( this.fileName.equalsIgnoreCase( fileName ) || compelDel == true )
		{
			File delFile = new File( this.fileName );
			if ( delFile.exists() )
			{
				if ( delFile.delete() )
				{
					System.out.println( "删除" + this.fileName + "文件成功" );
				}
				else
				{
					System.out.println( "删除" + this.fileName + "文件失敗" );
				}
			}
			else
			{
				System.out.println( this.fileName + "文件不存在,删除失败" );
			}
		}
	}
}
