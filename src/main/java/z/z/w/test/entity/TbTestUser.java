/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.entity.TbTestUser.java
 *         Desc: 
 *      @author: Z_Z.W - myhongkongzhen@gmail.com
 *     @version: 2014-10-14 上午10:31:40
 *   LastChange: 2014-10-14 上午10:31:40
 *      History:
 * </pre>
 **************************************************************************/
package z.z.w.test.entity ;

/**
 * z.z.w.test.entity.TbTestUser.java
 */

public class TbTestUser
{

	private Long	id ;
	private String	merSmsChanSig ;
	private String	merchantSmsUid ;
	private String	sendSmsCode ;
	private Integer	merchantSmsSendId ;

	public TbTestUser()
	{
		super() ;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj ) return true ;
		if ( obj == null ) return false ;
		if ( getClass() != obj.getClass() ) return false ;
		TbTestUser other = ( TbTestUser ) obj ;
		if ( id == null )
		{
			if ( other.id != null ) return false ;
		}
		else if ( !id.equals( other.id ) ) return false ;
		if ( merSmsChanSig == null )
		{
			if ( other.merSmsChanSig != null ) return false ;
		}
		else if ( !merSmsChanSig.equals( other.merSmsChanSig ) ) return false ;
		if ( merchantSmsSendId == null )
		{
			if ( other.merchantSmsSendId != null ) return false ;
		}
		else if ( !merchantSmsSendId.equals( other.merchantSmsSendId ) ) return false ;
		if ( merchantSmsUid == null )
		{
			if ( other.merchantSmsUid != null ) return false ;
		}
		else if ( !merchantSmsUid.equals( other.merchantSmsUid ) ) return false ;
		if ( sendSmsCode == null )
		{
			if ( other.sendSmsCode != null ) return false ;
		}
		else if ( !sendSmsCode.equals( other.sendSmsCode ) ) return false ;
		return true ;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id ;
	}

	/**
	 * @return the merchantSmsUid
	 */
	public String getMerchantSmsUid()
	{
		return merchantSmsUid ;
	}

	/**
	 * @return the merSmsChanSig
	 */
	public String getMerSmsChanSig()
	{
		return merSmsChanSig ;
	}

	/**
	 * @return the sendSmsCode
	 */
	public String getSendSmsCode()
	{
		return sendSmsCode ;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31 ;
		int result = 1 ;
		result = ( prime * result ) + ( ( id == null ) ? 0 : id.hashCode() ) ;
		result = ( prime * result ) + ( ( merSmsChanSig == null ) ? 0 : merSmsChanSig.hashCode() ) ;
		result = ( prime * result ) + ( ( merchantSmsSendId == null ) ? 0 : merchantSmsSendId.hashCode() ) ;
		result = ( prime * result ) + ( ( merchantSmsUid == null ) ? 0 : merchantSmsUid.hashCode() ) ;
		result = ( prime * result ) + ( ( sendSmsCode == null ) ? 0 : sendSmsCode.hashCode() ) ;
		return result ;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId( Long id )
	{
		this.id = id ;
	}

	/**
	 * @param merchantSmsSendId
	 *            the merchantSmsSendId to set
	 */
	public void setMerchantSmsSendId( Integer merchantSmsSendId )
	{
		this.merchantSmsSendId = merchantSmsSendId ;
	}

	/**
	 * @param merchantSmsUid
	 *            the merchantSmsUid to set
	 */
	public void setMerchantSmsUid( String merchantSmsUid )
	{
		this.merchantSmsUid = merchantSmsUid ;
	}

	/**
	 * @param merSmsChanSig
	 *            the merSmsChanSig to set
	 */
	public void setMerSmsChanSig( String merSmsChanSig )
	{
		this.merSmsChanSig = merSmsChanSig ;
	}

	/**
	 * @param sendSmsCode
	 *            the sendSmsCode to set
	 */
	public void setSendSmsCode( String sendSmsCode )
	{
		this.sendSmsCode = sendSmsCode ;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TbTestUser [id=" + id
				+ ", merSmsChanSig="
				+ merSmsChanSig
				+ ", merchantSmsUid="
				+ merchantSmsUid
				+ ", sendSmsCode="
				+ sendSmsCode
				+ ", merchantSmsSendId="
				+ merchantSmsSendId
				+ "]" ;
	}
}
