package z.z.w.test.entity.biz;

import java.util.Date;

/**************************************************************************
 * <pre>
 *     FileName: com.zy.sms.domain.entity.MerchantSmsChannel.java
 *         Desc:
 *      &#64;author: Z_Z.W - myhongkongzhen@gmail.com
 *     &#64;version: 2015年7月24日 下午6:30:32
 *   LastChange: 2015年7月24日 下午6:30:32
 *      History:
 * </pre>
 **************************************************************************/
public class MerchantSmsChannel
{

	/**
	 * ID bigint(20) not null,
	 * MER_SMS_CHAN_SIG char(20) comment '内部标示符',
	 * MERCHANT_SMS_UID char(10) comment '商户UID',
	 * CHANNEL_SMS_ID char(20) comment '短信发送消息ID',
	 * MERCHANT_SMS_SEND_ID bigint(20) comment '商户发送短信记录ID',
	 */

	private Long id;

	private String merSmsChanSig;

	private String merchantSmsUid;
	private String sendSmsCode;
	private Long   merchantSmsSendId;
	private Date   dateTime;

	public MerchantSmsChannel()
	{
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override public boolean equals( Object obj )
	{
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		MerchantSmsChannel other = ( MerchantSmsChannel ) obj;
		if ( dateTime == null )
		{
			if ( other.dateTime != null ) return false;
		}
		else if ( !dateTime.equals( other.dateTime ) ) return false;
		if ( id == null )
		{
			if ( other.id != null ) return false;
		}
		else if ( !id.equals( other.id ) ) return false;
		if ( merSmsChanSig == null )
		{
			if ( other.merSmsChanSig != null ) return false;
		}
		else if ( !merSmsChanSig.equals( other.merSmsChanSig ) ) return false;
		if ( merchantSmsSendId == null )
		{
			if ( other.merchantSmsSendId != null ) return false;
		}
		else if ( !merchantSmsSendId.equals( other.merchantSmsSendId ) ) return false;
		if ( merchantSmsUid == null )
		{
			if ( other.merchantSmsUid != null ) return false;
		}
		else if ( !merchantSmsUid.equals( other.merchantSmsUid ) ) return false;
		if ( sendSmsCode == null )
		{
			if ( other.sendSmsCode != null ) return false;
		}
		else if ( !sendSmsCode.equals( other.sendSmsCode ) ) return false;
		return true;
	}

	/**
	 * @return the dateTime
	 */
	public Date getDateTime()
	{
		if ( dateTime != null )
		{
			return ( Date ) dateTime.clone();
		}
		return null;
	}

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime( Date dateTime )
	{
		if ( dateTime != null )
		{
			this.dateTime = ( Date ) dateTime.clone();
		}
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id;
	}

	/**
	 * @return the merchantSmsSendId
	 */
	public Long getMerchantSmsSendId()
	{
		return merchantSmsSendId;
	}

	/**
	 * @param merchantSmsSendId the merchantSmsSendId to set
	 */
	public void setMerchantSmsSendId( Long merchantSmsSendId )
	{
		this.merchantSmsSendId = merchantSmsSendId;
	}

	/**
	 * @return the merchantSmsUid
	 */
	public String getMerchantSmsUid()
	{
		return merchantSmsUid;
	}

	/**
	 * @param merchantSmsUid the merchantSmsUid to set
	 */
	public void setMerchantSmsUid( String merchantSmsUid )
	{
		this.merchantSmsUid = merchantSmsUid;
	}

	/**
	 * @return the merSmsChanSig
	 */
	public String getMerSmsChanSig()
	{
		return merSmsChanSig;
	}

	/**
	 * @param merSmsChanSig the merSmsChanSig to set
	 */
	public void setMerSmsChanSig( String merSmsChanSig )
	{
		this.merSmsChanSig = merSmsChanSig;
	}

	/**
	 * @return the sendSmsCode
	 */
	public String getSendSmsCode()
	{
		return sendSmsCode;
	}

	/**
	 * @param sendSmsCode the sendSmsCode to set
	 */
	public void setSendSmsCode( String sendSmsCode )
	{
		this.sendSmsCode = sendSmsCode;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = ( prime * result ) + ( ( dateTime == null ) ? 0 : dateTime.hashCode() );
		result = ( prime * result ) + ( ( id == null ) ? 0 : id.hashCode() );
		result = ( prime * result ) + ( ( merSmsChanSig == null ) ? 0 : merSmsChanSig.hashCode() );
		result = ( prime * result ) + ( ( merchantSmsSendId == null ) ? 0 : merchantSmsSendId.hashCode() );
		result = ( prime * result ) + ( ( merchantSmsUid == null ) ? 0 : merchantSmsUid.hashCode() );
		result = ( prime * result ) + ( ( sendSmsCode == null ) ? 0 : sendSmsCode.hashCode() );
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override public String toString()
	{
		return "MerchantSmsChannel [id="
			   + id
			   + ", merSmsChanSig="
			   + merSmsChanSig
			   + ", merchantSmsUid="
			   + merchantSmsUid
			   + ", sendSmsCode="
			   + sendSmsCode
			   + ", merchantSmsSendId="
			   + merchantSmsSendId
			   + ", dateTime="
			   + dateTime
			   + "]";
	}
}
