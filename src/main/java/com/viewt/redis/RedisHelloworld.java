package com.viewt.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisHelloworld {
	
	public static void main(String[] args) {
		
		Jedis jedis = new Jedis("localhost",6379);
		testPress(jedis);
//		testString(jedis);
//		testMap(jedis);
		jedis.close();
	}
	//控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;
	//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;
	//在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;
	private static int TIMEOUT = 10000;
	////访问密码
	private static String AUTH = "admin";
	private static String ADDR = "192.168.0.100";
	private static int PORT = 6379;
	public static void JedisPool(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(MAX_IDLE);
//		config.setMaxWait(MAX_WAIT);
		config.setMaxWaitMillis(MAX_WAIT*1000);
		config.setTestOnBorrow(TEST_ON_BORROW);
		JedisPool jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		Jedis resource = jedisPool.getResource(); //获取资源
		jedisPool.returnResourceObject(resource); //释放资源
		jedisPool.close(); //关闭
	}
	public static void testMap(Jedis jedis){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		jedis.hmset("user",map);
		System.out.println(jedis.hmget("user","name","age"));
//		jedis.hdel("user","age");
//		System.out.println(jedis.hmget("user","name","111"));
//		System.out.println(jedis.hlen("user"));
//		System.out.println(jedis.hkeys("user"));
//		System.out.println(jedis.hvals("user"));
		Set<String> set = jedis.hkeys("user");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+":"+jedis.hmget("user",key));
		}
		
	}
	public static void testString(Jedis jedis){
		//jedis.auth("admin"); 
		jedis.set("foo", "bar");
		jedis.append("foo", " is my lover"); //拼接
		System.out.println(jedis.get("foo"));
		jedis.del("foo");  //删除某个键
		System.out.println(jedis.get("foo"));
		
		jedis.mset("name","liuling","age","23","qq","476777389");
		jedis.incr("age"); //进行加1操作  原子性加1
		System.out.println(jedis.get("name")+"--"+jedis.get("age")+"--"+jedis.get("qq"));
	}
	
	public static void testPress(Jedis jedis){
//		jedis.flushAll();
		jedis.set("foo", "bar");
//		System.out.println(jedis.clientList());
//		long start = System.currentTimeMillis();
//		int i = 0 ;
//		while((System.currentTimeMillis() - start) < 1000*10){
//			jedis.set(i++ + "", "Hellowrold");
//		}
		System.out.println(jedis.keys("*").iterator().next());
		
	}

}
