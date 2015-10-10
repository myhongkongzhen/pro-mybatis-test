package z.z.w.test.service.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.biz.pro-module
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-09-25 17:16
 *   LastChange: 2015-09-25 17:16
 *      History:
 * </pre>
 **************************************************************************/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:spring/spring.xml" } )
public class DBSelectServcieImplTest
{
    final static Logger logger = LoggerFactory.getLogger( DBSelectServcieImplTest.class );

    public DBSelectServcieImpl getDbSelectServcie()
    {
        return dbSelectServcie;
    }

    @Resource
    public void setDbSelectServcie( DBSelectServcieImpl dbSelectServcie )
    {
        this.dbSelectServcie = dbSelectServcie;
    }

    private DBSelectServcieImpl dbSelectServcie;

    @Test public void testExecute() throws Exception
    {
        logger.info( "開始查詢數據庫數據...." );
        try
        {
            dbSelectServcie.execute();
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}
