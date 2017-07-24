package education.cs.scu.DBHelper;

import redis.clients.jedis.Jedis;

/**
 * Created by maicius on 2017/7/24.
 */
public class testRedis {
    public static void main(String[] args){
        try {
            insert("username", "xiaoming1");
            System.out.println(get("username"));
            delete("username");
            System.out.println(get("username"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    static void insert(String key, String value) throws Exception{
        Jedis jedis = RedisPool.getJedis();
        jedis.set(key, value);

    }
    static void delete(String key) throws Exception{
        Jedis jedis = RedisPool.getJedis();
        jedis.del(key);
    }

    static String get(String key) throws Exception{
        Jedis jedis = RedisPool.getJedis();
        return jedis.get(key);
    }
}
