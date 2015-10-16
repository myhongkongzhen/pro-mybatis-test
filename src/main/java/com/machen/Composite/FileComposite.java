package com.machen.Composite;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class FileComposite extends FileComponent
{
	private List<FileComponent> children = new LinkedList<FileComponent>();

	//	private FileComposite parent;
	public FileComposite( String fileName )
	{
		super( fileName );
		File root = new File( fileName );
		if ( root.isFile() )
		{
			return;
		}
		String[] fileNames = root.list();
		if ( !ArrayUtils.isEmpty( fileNames ) )
		{
			for ( String fileNameStr : fileNames )
			{
				if ( new File( fileName + "/" + fileNameStr ).isFile() )
				{
					children.add( new FileLeaf( fileName + "/" + fileNameStr ) );
				}
				else if ( new File( fileName + "/" + fileNameStr ).isDirectory() ) children.add( new FileComposite( fileName + "/" + fileNameStr ) );
			}
		}
		// }
		// } else
		// {
		// // Iterator<FileComponent> iterator= this.parent.children.iterator();
		// this.parent.children.remove(this);
		// this.parent.children.add(new FileLeaf(fileName));
		//			
		// }
	}

	// public static FileComposite getComposite(String fileName){
	// return new FileComposite(fileName,new FileComposite(fileName));
	//	}
	@Override public void displayContent() throws Exception
	{
//		File file = new File(fileName);
//		assert file.isDirectory();
		for ( FileComponent component : children )
		{
//			File f = new File(component.fileName);
//			if (f.isDirectory())
//			{
//				File[] files = f.listFiles();
//				if (files == null || files.length <= 0)
//				{
//					System.out.println("此目录中无文件");
//				} else
//				{
//					component.displayContent();
//				}
//			} else
//			{
//				component.displayContent();
//			}
			component.displayContent();

		}
	}

	@Override public void showName( int level )
	{
		StringBuilder sb = new StringBuilder();
		for ( int i = 0 ; i < level ; i++ )
		{
			sb.append( "-" );
		}
		sb.append( "目录:" ).append( fileName );
		System.out.println( sb.toString() );
		for ( FileComponent component : children )
		{
			component.showName( level + 1 );
		}
	}

	@Override public String toString()
	{
		for ( FileComponent component : children )
		{
			System.out.println( component.toString() );
		}
		return ToStringBuilder.reflectionToString( this, ToStringStyle.SHORT_PREFIX_STYLE );

	}

	public int getCount( int initNumber )
	{
		int childrenNumber = 0;
		for ( FileComponent component : children )
		{
			childrenNumber += component.getCount( initNumber );
		}
		initNumber++;
		return childrenNumber + initNumber;
	}

	public int getCountExcludeDirectory( int initNumber )
	{
		int childrenNumber = 0;
		for ( FileComponent component : children )
		{
			childrenNumber += component.getCountExcludeDirectory( initNumber );
		}
		return childrenNumber;
	}

	@Override public void delFileByName( String fileName, boolean compelDel )
	{
		if ( this.fileName.equalsIgnoreCase( fileName ) || compelDel == true )
		{
			File delFile = new File( this.fileName );
			if ( delFile.exists() )
			{
				for ( FileComponent component : children )
				{
					component.delFileByName( this.fileName, true );
				}
				if ( delFile.delete() == true )
				{
					System.out.println( "删除" + this.fileName + "目录成功" );
				}
				else
				{
					System.out.println( this.fileName + "目录删除失败" );
				}
			}
			else
			{
				System.out.println( this.fileName + "目录不存在,删除失败" );
			}
		}
		else
		{
			for ( FileComponent component : children )
			{
				component.delFileByName( fileName, false );
			}
		}
	}

	public void delFileByName( String fileName )
	{
		delFileByName( fileName, false );
	}
}
