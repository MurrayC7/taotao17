package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(JedisTest.class);

	@Test
	public void testJediSingle() {
		// 创建一个jedis的对象
		Jedis jedis = new Jedis("192.168.244.128", 6379);
		// 调用jedis对象的方法,方法名称和redis的命令一致
		jedis.set("key1", "jedis test");
		System.out.println(jedis.get("key1"));
		// 关闭jedis
		jedis.close();
	}

	/**
	 * 使用jedis连接池
	 */
	@Test
	public void testJedisPool() {
		// 创建一个jedis连接池对象
		JedisPool pool = new JedisPool("192.168.244.128", 6379);
		// 从连接池中获得jedis对象
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		// 关闭jedis
		jedis.close();
		pool.close();
	}

	/**
	 * 集群版测试 集群自带连接池
	 */
	@Test
	public void testJedisCluster() {
		LOGGER.debug("调用redisCluster开始");

		HashSet<HostAndPort> nodes = new HashSet<>();
		for (int i = 7001; i <= 7006; i++) {
			nodes.add(new HostAndPort("192.168.244.128", i));
		}

		LOGGER.info("创建一个JedisCluster对象");
		JedisCluster cluster = new JedisCluster(nodes);

		LOGGER.debug("设置key1的值为1000");
		cluster.set("key1", "1000");

		LOGGER.debug("从redis中取key1的值");
		System.out.println(cluster.get("key1"));

		LOGGER.debug("关闭连接");
		cluster.close();
		try {
			int a = 1 / 0;
		} catch (Exception e) {
			LOGGER.error("系统发生异常", e);
		}
	}

	/**
	 * 单机版配置
	 */
	@Test
	public void testSpringJedisSingle() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		jedis.close();
		pool.close();
		((ClassPathXmlApplicationContext) applicationContext).close();
	}

	/**
	 * 集群版配置
	 */
	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		JedisCluster cluster = (JedisCluster) applicationContext.getBean("redisClient");
		System.out.println(cluster.get("key1"));
		cluster.close();
		((ClassPathXmlApplicationContext) applicationContext).close();
	}
}
