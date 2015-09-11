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
	
	List< MerchantSmsSend > selectResultByPage( Map< String, Object > param ) ;
	
	Integer getCount( Map< String, Object > params ) ;
	
	MerchantSmsSend selectSmsSendResultByID( @Param( value = "merchantSmsSend" ) MerchantSmsSend merchantSmsSend ) ;
	
	List< MerchantSmsChannel > selectSmsSendResultByIDs( @Param( value = "paramList" ) List< MerchantSmsSend > paramList ) ;
}