package z.z.w.test.server.impl ;

import org.springframework.beans.BeansException ;

import z.z.w.test.server.IServiceLoader ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.impl.BizServiceImpl.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月10日 下午4:31:30 
 *   LastChange: 2015年9月10日 下午4:31:30 
 *      History:
 * </pre>
 **************************************************************************/
public class BizServiceImpl implements IServiceLoader
{
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#loadService()
	 */
	public void loadService()
	{
		logger.info( "Starting bussiness service....." ) ;
		try
		{	
			
		}
		catch ( BeansException e )
		{
			logger.error(	"Loading bussiness error : {}.",
							e.getMessage(),
							e ) ;
		}
		logger.info( "Loaded bussiness service." ) ;
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see z.z.w.test.server.IServiceLoader#destroy()
	 */
	public void destroy()
	{
		// TODO 2015年9月10日 下午4:31:30
		Thread.currentThread().interrupt() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		loadService() ;
	}
	
}
