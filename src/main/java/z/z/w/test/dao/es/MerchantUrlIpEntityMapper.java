package z.z.w.test.dao.es;

import z.z.w.test.entity.ex.MerchantUrlIpEntity;

import java.util.List;

public interface MerchantUrlIpEntityMapper
{
	List<MerchantUrlIpEntity> selectAll();

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	int deleteByPrimaryKey( Long id );

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	int insert( MerchantUrlIpEntity record );

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	int insertSelective( MerchantUrlIpEntity record );

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	MerchantUrlIpEntity selectByPrimaryKey( Long id );

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKeySelective( MerchantUrlIpEntity record );

	/**
	 * This method was generated by MyBatis Generator.
	 * This method corresponds to the database table merchant_url_ip
	 *
	 * @mbggenerated
	 */
	int updateByPrimaryKey( MerchantUrlIpEntity record );
}