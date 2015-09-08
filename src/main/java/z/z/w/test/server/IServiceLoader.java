package z.z.w.test.server ;

import org.slf4j.Logger ;
import org.slf4j.LoggerFactory ;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.server.IServiceLoader.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月7日 下午12:13:00 
 *   LastChange: 2015年9月7日 下午12:13:00 
 *      History:
 * </pre>
 **************************************************************************/
public interface IServiceLoader
{
	final static Logger	logger	= LoggerFactory.getLogger( IServiceLoader.class ) ;
	
	void loadService() ;
	
	void destroy() ;
}
