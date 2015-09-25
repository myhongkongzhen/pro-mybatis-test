package z.z.w.test.service.biz;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.pro-module
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-09-25 17:21
 *   LastChange: 2015-09-25 17:21
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class DBDeleteServiceImplTest
{
    
    private final static Logger logger = LoggerFactory.getLogger( DBDeleteServiceImplTest.class );
    
    @Test
    public void testExecute() throws Exception
    {
        logger.info( "開始刪除數據庫數據...." );
    }
}