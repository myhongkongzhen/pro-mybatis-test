package z.z.w.test.service ;

import z.z.w.test.dao.RedisTemplateFactory;
import z.z.w.test.entity.TbTestUser;

/**************************************************************************
 * <pre>
 *     FileName: z.z.w.test.service.RedisService
 *         Desc:
 *       author: Z_Z.W - myhongkongzhen@gmail.com
 *      version: 2015-09-30 12:03
 *   LastChange: 2015-09-30 12:03
 *      History:
 * </pre>
 **************************************************************************/
public class RedisService extends RedisTemplateFactory< String, TbTestUser >
{
//	private final static Logger	logger	= LoggerFactory.getLogger( RedisService.class ) ;
//
//	/**
//	 * Create by : 2015年9月30日 下午2:12:52
//	 *
//	 * @param keyId
//	 * @return
//	 */
//	public TbTestUser get( final String keyId )
//	{
//		TbTestUser result = this.redisTemplate.execute( new RedisCallback< TbTestUser >()
//		{
//			@Override
//			public TbTestUser doInRedis( RedisConnection connection ) throws DataAccessException
//			{
//				RedisSerializer< String > serializer = getRedisSerializer() ;
//				byte[] key = serializer.serialize( keyId ) ;
//				byte[] value = connection.get( key ) ;
//				if ( value == null ) { return null ; }
//				String name = serializer.deserialize( value ) ;
//				TbTestUser user = new TbTestUser() ;
//				user.setId( Long.getLong( new String( key ) ) ) ;
//				user.setMerchantSmsUid( name ) ;
//				return user ;
//			}
//		} ) ;
//		return result ;
//	}
//
//	/**
//	 * Create by : 2015年9月30日 下午2:12:46
//	 *
//	 * @param list
//	 * @return
//	 */
//	public boolean add( final List< TbTestUser > list )
//	{
//		int size = list.size() ;
//		logger.info( "-->{}." , size ) ;
//		boolean result = this.redisTemplate.execute( new RedisCallback< Boolean >()
//		{
//			@Override
//			public Boolean doInRedis( RedisConnection connection ) throws DataAccessException
//			{
//				RedisSerializer< String > serializer = getRedisSerializer() ;
//				for ( TbTestUser user : list )
//				{
//					byte[] key = serializer.serialize( String.valueOf( user.getId() ) ) ;
//					byte[] name = serializer.serialize( user.getMerchantSmsUid() ) ;
//					connection.setNX( key , name ) ;
//				}
//				return true ;
//			}
//		} , false , true ) ;
//
//		return result ;
//	}
}
