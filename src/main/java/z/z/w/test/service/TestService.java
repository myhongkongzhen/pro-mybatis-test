/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.TestService.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2014-10-14 上午10:40:20 
 *   LastChange: 2014-10-14 上午10:40:20 
 *      History:
 * </pre>
 **************************************************************************/
package z.z.w.test.service ;

import java.util.List ;

import javax.annotation.Resource ;

import org.springframework.stereotype.Service ;

import z.z.w.test.dao.TestMapper ;
import z.z.w.test.entity.TbTestUser ;

/**
 * z.z.w.test.service.TestService.java
 */
@Service
public class TestService
{
	
	private TestMapper	testMapper	= null ;
	
	public List< TbTestUser > queryAllUsers()
	{
		return testMapper.selectAll() ;
	}
	
	public TbTestUser findUserById( String id )
	{
		return testMapper.selectUserById( id ) ;
	}
	
	/**
	 * <br>
	 * Created on: 2014-10-14 上午11:09:16
	 * 
	 * @return the testMapper
	 */
	public TestMapper getTestMapper()
	{
		return testMapper ;
	}
	
	/**
	 * <br>
	 * Created on: 2014-10-14 上午11:09:16
	 * 
	 * @param testMapper
	 *            the testMapper to set
	 */
	@Resource
	public void setTestMapper( TestMapper testMapper )
	{
		this.testMapper = testMapper ;
	}
	
}
