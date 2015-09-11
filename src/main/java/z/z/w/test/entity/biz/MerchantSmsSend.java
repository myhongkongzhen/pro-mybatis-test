package z.z.w.test.entity.biz ;

import java.util.Date ;

public class MerchantSmsSend
{
	private Long	id ;
	
	private String	merchantAccount ;
	
	private Integer	smsSignerId ;
	
	private String	smsTemplateId ;
	
	private String	smsContent ;
	
	private String	smsChannelCode ;
	
	private Date	createTime ;
	
	private String	receiveMobile ;
	
	private Date	receiveTime ;
	
	private Short	sendResult ;
	
	private Integer	resource ;
	
	private Integer	receiveStatus ;
	/**
	 * -1表示无appId
	 */
	private Integer	appId ;
	
	private Integer	smsType ;
	
	private String	province ;
	
	private String	city ;
	
	private String	carriers ;
	
	/***********************************************/
	private String	merchantSmsUid ;
	
	private String	channelSmsId ;
	
	private Date	dataTime ;
	private Integer	receiveStatusChannel ;
	private String	receiveStatusDesc ;
	
	private String	sendTime ;
	private String	statusTime ;
	
	public MerchantSmsSend()
	{
		super() ;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "MerchantSmsSend [id=" + id + ", merchantAccount=" + merchantAccount + ", smsSignerId=" + smsSignerId + ", smsTemplateId=" + smsTemplateId + ", smsContent=" + smsContent + ", smsChannelCode=" + smsChannelCode + ", createTime=" + createTime + ", receiveMobile=" + receiveMobile + ", receiveTime=" + receiveTime + ", sendResult=" + sendResult + ", resource=" + resource + ", receiveStatus=" + receiveStatus + ", appId=" + appId + ", smsType=" + smsType + ", province=" + province + ", city=" + city + ", carriers=" + carriers + ", merchantSmsUid=" + merchantSmsUid + ", channelSmsId=" + channelSmsId + ", dataTime=" + dataTime + ", receiveStatusChannel=" + receiveStatusChannel + ", receiveStatusDesc=" + receiveStatusDesc + ", sendTime=" + sendTime + ", statusTime=" + statusTime + "]" ;
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
		result = prime * result + ( ( appId == null ) ? 0 : appId.hashCode() ) ;
		result = prime * result + ( ( carriers == null ) ? 0 : carriers.hashCode() ) ;
		result = prime * result + ( ( channelSmsId == null ) ? 0 : channelSmsId.hashCode() ) ;
		result = prime * result + ( ( city == null ) ? 0 : city.hashCode() ) ;
		result = prime * result + ( ( createTime == null ) ? 0 : createTime.hashCode() ) ;
		result = prime * result + ( ( dataTime == null ) ? 0 : dataTime.hashCode() ) ;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() ) ;
		result = prime * result + ( ( merchantAccount == null ) ? 0 : merchantAccount.hashCode() ) ;
		result = prime * result + ( ( merchantSmsUid == null ) ? 0 : merchantSmsUid.hashCode() ) ;
		result = prime * result + ( ( province == null ) ? 0 : province.hashCode() ) ;
		result = prime * result + ( ( receiveMobile == null ) ? 0 : receiveMobile.hashCode() ) ;
		result = prime * result + ( ( receiveStatus == null ) ? 0 : receiveStatus.hashCode() ) ;
		result = prime * result + ( ( receiveStatusChannel == null ) ? 0 : receiveStatusChannel.hashCode() ) ;
		result = prime * result + ( ( receiveStatusDesc == null ) ? 0 : receiveStatusDesc.hashCode() ) ;
		result = prime * result + ( ( receiveTime == null ) ? 0 : receiveTime.hashCode() ) ;
		result = prime * result + ( ( resource == null ) ? 0 : resource.hashCode() ) ;
		result = prime * result + ( ( sendResult == null ) ? 0 : sendResult.hashCode() ) ;
		result = prime * result + ( ( sendTime == null ) ? 0 : sendTime.hashCode() ) ;
		result = prime * result + ( ( smsChannelCode == null ) ? 0 : smsChannelCode.hashCode() ) ;
		result = prime * result + ( ( smsContent == null ) ? 0 : smsContent.hashCode() ) ;
		result = prime * result + ( ( smsSignerId == null ) ? 0 : smsSignerId.hashCode() ) ;
		result = prime * result + ( ( smsTemplateId == null ) ? 0 : smsTemplateId.hashCode() ) ;
		result = prime * result + ( ( smsType == null ) ? 0 : smsType.hashCode() ) ;
		result = prime * result + ( ( statusTime == null ) ? 0 : statusTime.hashCode() ) ;
		return result ;
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
		MerchantSmsSend other = ( MerchantSmsSend ) obj ;
		if ( appId == null )
		{
			if ( other.appId != null ) return false ;
		}
		else if ( !appId.equals( other.appId ) ) return false ;
		if ( carriers == null )
		{
			if ( other.carriers != null ) return false ;
		}
		else if ( !carriers.equals( other.carriers ) ) return false ;
		if ( channelSmsId == null )
		{
			if ( other.channelSmsId != null ) return false ;
		}
		else if ( !channelSmsId.equals( other.channelSmsId ) ) return false ;
		if ( city == null )
		{
			if ( other.city != null ) return false ;
		}
		else if ( !city.equals( other.city ) ) return false ;
		if ( createTime == null )
		{
			if ( other.createTime != null ) return false ;
		}
		else if ( !createTime.equals( other.createTime ) ) return false ;
		if ( dataTime == null )
		{
			if ( other.dataTime != null ) return false ;
		}
		else if ( !dataTime.equals( other.dataTime ) ) return false ;
		if ( id == null )
		{
			if ( other.id != null ) return false ;
		}
		else if ( !id.equals( other.id ) ) return false ;
		if ( merchantAccount == null )
		{
			if ( other.merchantAccount != null ) return false ;
		}
		else if ( !merchantAccount.equals( other.merchantAccount ) ) return false ;
		if ( merchantSmsUid == null )
		{
			if ( other.merchantSmsUid != null ) return false ;
		}
		else if ( !merchantSmsUid.equals( other.merchantSmsUid ) ) return false ;
		if ( province == null )
		{
			if ( other.province != null ) return false ;
		}
		else if ( !province.equals( other.province ) ) return false ;
		if ( receiveMobile == null )
		{
			if ( other.receiveMobile != null ) return false ;
		}
		else if ( !receiveMobile.equals( other.receiveMobile ) ) return false ;
		if ( receiveStatus == null )
		{
			if ( other.receiveStatus != null ) return false ;
		}
		else if ( !receiveStatus.equals( other.receiveStatus ) ) return false ;
		if ( receiveStatusChannel == null )
		{
			if ( other.receiveStatusChannel != null ) return false ;
		}
		else if ( !receiveStatusChannel.equals( other.receiveStatusChannel ) ) return false ;
		if ( receiveStatusDesc == null )
		{
			if ( other.receiveStatusDesc != null ) return false ;
		}
		else if ( !receiveStatusDesc.equals( other.receiveStatusDesc ) ) return false ;
		if ( receiveTime == null )
		{
			if ( other.receiveTime != null ) return false ;
		}
		else if ( !receiveTime.equals( other.receiveTime ) ) return false ;
		if ( resource == null )
		{
			if ( other.resource != null ) return false ;
		}
		else if ( !resource.equals( other.resource ) ) return false ;
		if ( sendResult == null )
		{
			if ( other.sendResult != null ) return false ;
		}
		else if ( !sendResult.equals( other.sendResult ) ) return false ;
		if ( sendTime == null )
		{
			if ( other.sendTime != null ) return false ;
		}
		else if ( !sendTime.equals( other.sendTime ) ) return false ;
		if ( smsChannelCode == null )
		{
			if ( other.smsChannelCode != null ) return false ;
		}
		else if ( !smsChannelCode.equals( other.smsChannelCode ) ) return false ;
		if ( smsContent == null )
		{
			if ( other.smsContent != null ) return false ;
		}
		else if ( !smsContent.equals( other.smsContent ) ) return false ;
		if ( smsSignerId == null )
		{
			if ( other.smsSignerId != null ) return false ;
		}
		else if ( !smsSignerId.equals( other.smsSignerId ) ) return false ;
		if ( smsTemplateId == null )
		{
			if ( other.smsTemplateId != null ) return false ;
		}
		else if ( !smsTemplateId.equals( other.smsTemplateId ) ) return false ;
		if ( smsType == null )
		{
			if ( other.smsType != null ) return false ;
		}
		else if ( !smsType.equals( other.smsType ) ) return false ;
		if ( statusTime == null )
		{
			if ( other.statusTime != null ) return false ;
		}
		else if ( !statusTime.equals( other.statusTime ) ) return false ;
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
	 * @param id the id to set
	 */
	public void setId( Long id )
	{
		this.id = id ;
	}
	
	/**
	 * @return the merchantAccount
	 */
	public String getMerchantAccount()
	{
		return merchantAccount ;
	}
	
	/**
	 * @param merchantAccount the merchantAccount to set
	 */
	public void setMerchantAccount( String merchantAccount )
	{
		this.merchantAccount = merchantAccount ;
	}
	
	/**
	 * @return the smsSignerId
	 */
	public Integer getSmsSignerId()
	{
		return smsSignerId ;
	}
	
	/**
	 * @param smsSignerId the smsSignerId to set
	 */
	public void setSmsSignerId( Integer smsSignerId )
	{
		this.smsSignerId = smsSignerId ;
	}
	
	/**
	 * @return the smsTemplateId
	 */
	public String getSmsTemplateId()
	{
		return smsTemplateId ;
	}
	
	/**
	 * @param smsTemplateId the smsTemplateId to set
	 */
	public void setSmsTemplateId( String smsTemplateId )
	{
		this.smsTemplateId = smsTemplateId ;
	}
	
	/**
	 * @return the smsContent
	 */
	public String getSmsContent()
	{
		return smsContent ;
	}
	
	/**
	 * @param smsContent the smsContent to set
	 */
	public void setSmsContent( String smsContent )
	{
		this.smsContent = smsContent ;
	}
	
	/**
	 * @return the smsChannelCode
	 */
	public String getSmsChannelCode()
	{
		return smsChannelCode ;
	}
	
	/**
	 * @param smsChannelCode the smsChannelCode to set
	 */
	public void setSmsChannelCode( String smsChannelCode )
	{
		this.smsChannelCode = smsChannelCode ;
	}
	
	/**
	 * @return the createTime
	 */
	public Date getCreateTime()
	{
		return createTime ;
	}
	
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime( Date createTime )
	{
		this.createTime = createTime ;
	}
	
	/**
	 * @return the receiveMobile
	 */
	public String getReceiveMobile()
	{
		return receiveMobile ;
	}
	
	/**
	 * @param receiveMobile the receiveMobile to set
	 */
	public void setReceiveMobile( String receiveMobile )
	{
		this.receiveMobile = receiveMobile ;
	}
	
	/**
	 * @return the receiveTime
	 */
	public Date getReceiveTime()
	{
		return receiveTime ;
	}
	
	/**
	 * @param receiveTime the receiveTime to set
	 */
	public void setReceiveTime( Date receiveTime )
	{
		this.receiveTime = receiveTime ;
	}
	
	/**
	 * @return the sendResult
	 */
	public Short getSendResult()
	{
		return sendResult ;
	}
	
	/**
	 * @param sendResult the sendResult to set
	 */
	public void setSendResult( Short sendResult )
	{
		this.sendResult = sendResult ;
	}
	
	/**
	 * @return the resource
	 */
	public Integer getResource()
	{
		return resource ;
	}
	
	/**
	 * @param resource the resource to set
	 */
	public void setResource( Integer resource )
	{
		this.resource = resource ;
	}
	
	/**
	 * @return the receiveStatus
	 */
	public Integer getReceiveStatus()
	{
		return receiveStatus ;
	}
	
	/**
	 * @param receiveStatus the receiveStatus to set
	 */
	public void setReceiveStatus( Integer receiveStatus )
	{
		this.receiveStatus = receiveStatus ;
	}
	
	/**
	 * @return the appId
	 */
	public Integer getAppId()
	{
		return appId ;
	}
	
	/**
	 * @param appId the appId to set
	 */
	public void setAppId( Integer appId )
	{
		this.appId = appId ;
	}
	
	/**
	 * @return the smsType
	 */
	public Integer getSmsType()
	{
		return smsType ;
	}
	
	/**
	 * @param smsType the smsType to set
	 */
	public void setSmsType( Integer smsType )
	{
		this.smsType = smsType ;
	}
	
	/**
	 * @return the province
	 */
	public String getProvince()
	{
		return province ;
	}
	
	/**
	 * @param province the province to set
	 */
	public void setProvince( String province )
	{
		this.province = province ;
	}
	
	/**
	 * @return the city
	 */
	public String getCity()
	{
		return city ;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity( String city )
	{
		this.city = city ;
	}
	
	/**
	 * @return the carriers
	 */
	public String getCarriers()
	{
		return carriers ;
	}
	
	/**
	 * @param carriers the carriers to set
	 */
	public void setCarriers( String carriers )
	{
		this.carriers = carriers ;
	}
	
	/**
	 * @return the merchantSmsUid
	 */
	public String getMerchantSmsUid()
	{
		return merchantSmsUid ;
	}
	
	/**
	 * @param merchantSmsUid the merchantSmsUid to set
	 */
	public void setMerchantSmsUid( String merchantSmsUid )
	{
		this.merchantSmsUid = merchantSmsUid ;
	}
	
	/**
	 * @return the channelSmsId
	 */
	public String getChannelSmsId()
	{
		return channelSmsId ;
	}
	
	/**
	 * @param channelSmsId the channelSmsId to set
	 */
	public void setChannelSmsId( String channelSmsId )
	{
		this.channelSmsId = channelSmsId ;
	}
	
	/**
	 * @return the dataTime
	 */
	public Date getDataTime()
	{
		return dataTime ;
	}
	
	/**
	 * @param dataTime the dataTime to set
	 */
	public void setDataTime( Date dataTime )
	{
		this.dataTime = dataTime ;
	}
	
	/**
	 * @return the receiveStatusChannel
	 */
	public Integer getReceiveStatusChannel()
	{
		return receiveStatusChannel ;
	}
	
	/**
	 * @param receiveStatusChannel the receiveStatusChannel to set
	 */
	public void setReceiveStatusChannel( Integer receiveStatusChannel )
	{
		this.receiveStatusChannel = receiveStatusChannel ;
	}
	
	/**
	 * @return the receiveStatusDesc
	 */
	public String getReceiveStatusDesc()
	{
		return receiveStatusDesc ;
	}
	
	/**
	 * @param receiveStatusDesc the receiveStatusDesc to set
	 */
	public void setReceiveStatusDesc( String receiveStatusDesc )
	{
		this.receiveStatusDesc = receiveStatusDesc ;
	}
	
	/**
	 * @return the sendTime
	 */
	public String getSendTime()
	{
		return sendTime ;
	}
	
	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime( String sendTime )
	{
		this.sendTime = sendTime ;
	}
	
	/**
	 * @return the statusTime
	 */
	public String getStatusTime()
	{
		return statusTime ;
	}
	
	/**
	 * @param statusTime the statusTime to set
	 */
	public void setStatusTime( String statusTime )
	{
		this.statusTime = statusTime ;
	}
	
}
