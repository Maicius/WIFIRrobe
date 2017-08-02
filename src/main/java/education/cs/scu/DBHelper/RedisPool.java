package education.cs.scu.DBHelper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by maicius on 2017/7/24.
 */
public class RedisPool {
    private static String URL = "127.0.0.1";
    private static int PORT = 6379;
    private static String PASSOWRD = "110110";

    //连接实例的最啊的数目，默认值为8
    //赋值为-1表示不限制；
    // 如果pool已经分配了maxActive个jedis实例，
    // 则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = -1;

    //控制一个pool最多有多少个状态为空闲的jedis实例，默认值为8
    private static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。
    // 如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；
    // 如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    RedisPool(){
        initRedisPool();
    }

    static void initRedisPool(){
        try{
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config, URL, PORT, TIMEOUT, PASSOWRD);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     */
    public synchronized static Jedis getJedis(){
        try{
            if (jedisPool != null) {
                return  jedisPool.getResource();
            } else {
                initRedisPool();
                return jedisPool.getResource();
            }
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 释放jedis资源
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.close();
        }
    }

}
