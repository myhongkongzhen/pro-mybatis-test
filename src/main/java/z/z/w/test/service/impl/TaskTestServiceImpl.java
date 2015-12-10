/**********************************************************************************************************************
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package z.z.w.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import z.z.w.test.service.IService;
import z.z.w.test.service.biz.MerchantSmsSendService;

/*********************************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.impl.TaskTestServiceImpl
 *         Desc: 測試spring定時器
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-10-10 09:55
 *   LastChange: 2015-10-10 09:55
 *      History:
 * </pre>
 *********************************************************************************************/
public class TaskTestServiceImpl implements IService
{
	final private static Logger logger = LoggerFactory.getLogger( TaskTestServiceImpl.class );
	private MerchantSmsSendService merchantSmsSendService;
	@Value( "${VOS.5000.HTTP.URL}" ) private String httpUrl = "";

	@Override
	public void execute() throws Exception
	{

		logger.info( "httpUrl=={}==", httpUrl );

//		logger.info( "TaskTestServiceImpl execute start ..................................." );
////		Future<String> future = merchantSmsSendService.asyncMethodWithReturnType();
////		if ( null != future ) logger.info( "========{}==|||||||||||||||||||||", future.get() );
//
//		for ( int i = 0 ; i < 200 ; i++ )
//		{
//			merchantSmsSendService.asynchronously( i );
//		}
//
//		logger.info( "TaskTestServiceImpl execute end ................................." );
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
	@Override
	public void run()
	{

	}

	public MerchantSmsSendService getMerchantSmsSendService()
	{
		return merchantSmsSendService;
	}

	public void setMerchantSmsSendService( MerchantSmsSendService merchantSmsSendService )
	{
		this.merchantSmsSendService = merchantSmsSendService;
	}
}
