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
import z.z.w.test.service.IService;

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
public class TaskTestTwoServiceImpl implements IService
{
	final private static Logger logger = LoggerFactory.getLogger( TaskTestTwoServiceImpl.class );

	@Override public void execute() throws Exception
	{
		logger.info( "TaskTestTwoServiceImpl execute start ...===============================...." );
		Thread.sleep( 6000 );
		logger.info( "TaskTestTwoServiceImpl execute end ...==================================.." );
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

	}
}
