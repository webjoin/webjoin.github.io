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
	//����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����Ĭ��ֵҲ��8��
	private static int MAX_IDLE = 200;
	//�ȴ��������ӵ����ʱ�䣬��λ���룬Ĭ��ֵΪ-1����ʾ������ʱ����������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
	private static int MAX_WAIT = 10000;
	//��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
	private static boolean TEST_ON_BORROW = true;
	private static int TIMEOUT = 10000;
	////��������
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
		Jedis resource = jedisPool.getResource(); //��ȡ��Դ
		jedisPool.returnResourceObject(resource); //�ͷ���Դ
		jedisPool.close(); //�ر�
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
		jedis.append("foo", " is my lover"); //ƴ��
		System.out.println(jedis.get("foo"));
		jedis.del("foo");  //ɾ��ĳ����
		System.out.println(jedis.get("foo"));
		
		jedis.mset("name","liuling","age","23","qq","476777389");
		jedis.incr("age"); //���м�1����  ԭ���Լ�1
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
