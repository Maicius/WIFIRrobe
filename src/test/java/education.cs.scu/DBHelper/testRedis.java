package education.cs.scu.DBHelper;

import redis.clients.jedis.Jedis;

/**
 * Created by maicius on 2017/7/24.
 */
public class testRedis {
    private static Jedis jedis;
    public static void main(String[] args){
        try {
            Jedis jedis = RedisPool.getJedis();
            insert("username", "xiaoming1");
            System.out.println(get("username"));
            delete("username");
            System.out.println(get("username"));
            RedisPool.returnResource(jedis);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static void insert(String key, String value) throws Exception{
        jedis.set(key, value);

    }
    static void delete(String key) throws Exception{
        jedis.del(key);
    }

    static String get(String key) throws Exception{
        return jedis.get(key);
    }
}
