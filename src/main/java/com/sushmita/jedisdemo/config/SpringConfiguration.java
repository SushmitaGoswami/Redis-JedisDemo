package com.sushmita.jedisdemo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import com.sushmita.jedisdemo.model.Programmer;


@Configuration
public class SpringConfiguration {

	@Value("${redis.host}")
	private String redisHost;
	
	@Value("${redis.port}")
	private int redisPort;
	
	@Value("${redis.password}")
	private String redisPassword;
	
	
	
	@Value("${redis.jedis.pool.max.total}")
	private int redisJedisPoolMax;
	
	@Value("${redis.jedis.pool.max.idle}")
	private int redisJedisPoolMaxIdle;
	
	@Value("${redis.jedis.pool.min.idle}")
	private int redisJedisPoolMinIdle;
	
	@Bean
	public JedisClientConfiguration getJedisClientConfiguration() {
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(redisJedisPoolMax);
		poolConfig.setMaxTotal(redisJedisPoolMaxIdle);
		poolConfig.setMaxTotal(redisJedisPoolMinIdle);
		return builder.poolConfig(poolConfig).build();
	}
	
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		configuration.setHostName(redisHost);
		if(!redisPassword.isEmpty() && redisPassword !=null) {
			configuration.setPassword(RedisPassword.of(redisPassword));
		}
		configuration.setPort(redisPort);
		return new JedisConnectionFactory(configuration, getJedisClientConfiguration());
		
	}
	
	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		return redisTemplate;
	}
	
	@Bean
	@Qualifier("listOperations")
	public ListOperations<String, Programmer> listOperations(RedisTemplate<String, Programmer> template) {
		return template.opsForList();
	}
	
	@Bean
	@Qualifier("setOperations")
	public SetOperations<String, Programmer> setOperations(RedisTemplate<String, Programmer> template) {
		return template.opsForSet();
	}
	
	@Bean
	@Qualifier("hashOperations")
	public HashOperations<String, String, Programmer> hashOperations(RedisTemplate<String, Programmer> template) {
		return template.opsForHash();
	}
}
