package tk.jingzing.redis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ConcurrentHashMap;

@Service("redisCacheManager")
// @Slf4j
public class RedisCacheManager {
	@Value("${redisdbtype}")
	private String redisdbtype;
	
	@Value("${redisdbnumber}")
	private String redisdbnumber;
	
	@Value("${host}")
	private String host;
	@Value("${port}")
	private int port;
	@Value("${timeout}")
	private int timeout;
	@Value("${passwords}")
	private String passwords;
	
	@Value("${maxtotal}")
	private String maxtotal;
	@Value("${maxidle}")
	private String maxidle;
	@Value("${maxActive}")
	private String maxActive;
	@Value("${minidle}")
	private String minidle;
	@Value("${maxwaitmillis}")
	private String maxwaitmillis;
	@Value("${testonborrow}")
	private String testonborrow;
	@Value("${testwhileidle}")
	private String testwhileidle;
	
	private static JedisPoolConfig poolConfig = null;
	
	// 保存不同的数据库连接
	private ConcurrentHashMap<String, RedisCachePool> redisPoolMap = new ConcurrentHashMap<String, RedisCachePool>();
	
	public ConcurrentHashMap<String, RedisCachePool> getRedisPoolMap() {
		if (redisPoolMap.size() < 1) {
			initConfig();
			initPoolMap();
		}
		return redisPoolMap;
	}
	
	/**
	 * @Description:共享的poolconfig
	 * @return:void
	 */
	private void initConfig() {
		poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(testwhileidle.equals("true") ? true : false);
		poolConfig.setTestWhileIdle(testonborrow.equals("true") ? true : false);
		poolConfig.setMaxIdle(Integer.parseInt(maxidle));
		poolConfig.setMaxTotal(Integer.parseInt(maxtotal));
		poolConfig.setMinIdle(Integer.parseInt(minidle));
		poolConfig.setMaxWaitMillis(Integer.parseInt(maxwaitmillis));
		//连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
	//	poolConfig.setBlockWhenExhausted(true);
		//设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
	//	poolConfig.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
	}
	
	private void initPoolMap() {
		try {
			if (null != redisdbtype && null != redisdbnumber) {
				String[] dbs = redisdbtype.split(",");
				String[] numbers = redisdbnumber.split(",");
				for (int i = 0; i < dbs.length; i++) {
					// 得到redis连接池对象
					JedisPool jedisPool = new JedisPool(poolConfig, host, port, timeout);
					// 存放不同redis数据库
					redisPoolMap.put(dbs[i], new RedisCachePool(Integer.parseInt(numbers[i]), jedisPool));
				}
			}
		} catch (Exception e) {
			// log.error("redisCacheManager初始化失败！" + e.getLocalizedMessage());
		}
	}
	
	/**
	 * @Description: 得到jedis连接
	 * @param dbtypeName
	 * @return:Jedis
	 */
	public Jedis getResource(RedisDataBaseType dbtypeName) {
		Jedis jedisResource = null;
		RedisCachePool pool = redisPoolMap.get(dbtypeName.toString());
		if (pool != null) {
			jedisResource = pool.getResource();
		}
		return jedisResource;
	}
	
	/**
	 * @Description: 返回连接池
	 * @param dbtypeName
	 * @param jedis
	 * @return:void
	 */
	public void returnResource(RedisDataBaseType dbtypeName, Jedis jedis) {
		RedisCachePool pool = redisPoolMap.get(dbtypeName.toString());
		if (pool != null)
			pool.returnResource(jedis);
	}
}
