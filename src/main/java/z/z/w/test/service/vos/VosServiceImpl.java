/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.service.vos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import z.z.w.test.service.IService;
import z.z.w.util.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.vos.VosServiceImpl
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-20 11:48
 *   LastChange: 2015-10-20 11:48
 *      History:
 * </pre>
 *********************************************************************************************/
public class VosServiceImpl implements IService
{

	final static Logger logger = LoggerFactory.getLogger( VosServiceImpl.class );

	@Value( "${VOS.5000.HTTP.URL}" ) private String httpUrl = "";

	@Override public void execute() throws Exception
	{
		logger.info( "vos5000測試代碼...{}.", httpUrl );

		Map<String, String> map = new HashMap<>();
		map.put( "Content-Type", "text/html;charset=UTF-8" );
		HttpClient.INSTANCE.httpPostParticular( httpUrl, null, map );
		String post = HttpClient.INSTANCE.httpPost( httpUrl );

		logger.info( "請求返回結果：{}.", post );
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override public void run()
	{
		try
		{
			execute();
		}
		catch ( Exception e )
		{
			logger.error( "查詢數據出错:" + e.getMessage(), e );
		}
	}
}
