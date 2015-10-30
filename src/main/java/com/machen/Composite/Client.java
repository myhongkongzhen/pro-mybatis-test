package com.machen.Composite;

public class Client
{
	public static void main( String[] args ) throws Exception
	{
		String fileName = "E:/[书法字典]/zidian";
		if ( ( args != null && args.length > 0 ) ) fileName = args[ 0 ];// "E:/[书法字典]/zidian";

		FileComponent root = new FileComposite( fileName );
		root.showName( 0 );
		root.displayContent();
//		root.toString();
		System.out.println( root.getCount( 0 ) );
		System.out.println( root.getCountExcludeDirectory( 0 ) );
//		root.delFileByName("E:/DVD/1/2/3/4/5");
	}
}
