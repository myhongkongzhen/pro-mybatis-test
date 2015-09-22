package z.z.w.test.dao.biz ;

import java.util.List ;
import java.util.Map ;

import org.apache.ibatis.annotations.Param ;

import z.z.w.test.entity.biz.MerchantSmsChannel ;
import z.z.w.test.entity.biz.MerchantSmsSend ;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.persistence.mapper.exmanager.MerchantSmsSendMapper.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2015年9月8日 下午3:08:12 
 *   LastChange: 2015年9月8日 下午3:08:12 
 *      History:
 * </pre>
 **************************************************************************/
public interface MerchantSmsSendMapper
{
	
	Integer getCount( Map< String, Object > params ) ;
	
<<<<<<< HEAD
=======
	/**
	 * Create by : 2015年9月21日 下午4:07:04
	 * 
	 * @return
	 */
	Long getMaxId() ;
	
	/**
	 * Create by : 2015年9月17日 下午2:32:07
	 */
	void insertSelective( /* @Param( value = "merchantSmsSend" ) */MerchantSmsSend merchantSmsSend ) ;
	
>>>>>>> demo-dev
	/**
	 * Create by : 2015年9月22日 下午12:17:51
	 * 
	 * @param lt
	 */
<<<<<<< HEAD
	Long getMaxId() ;
	
	/**
	 * Create by : 2015年9月17日 下午2:32:07
	 */
	void insertSelective( /* @Param( value = "merchantSmsSend" ) */MerchantSmsSend merchantSmsSend ) ;
=======
	void insertSelectiveList( @Param( value = "paramLt" ) List< MerchantSmsSend > lt ) ;
>>>>>>> demo-dev
	
	List< MerchantSmsSend > selectResultByPage( Map< String, Object > param ) ;
	
	MerchantSmsSend selectSmsSendResultByID( @Param( value = "merchantSmsSend" ) MerchantSmsSend merchantSmsSend ) ;
	
	List< MerchantSmsChannel > selectSmsSendResultByIDs( @Param( value = "paramList" ) List< MerchantSmsSend > paramList ) ;
}
