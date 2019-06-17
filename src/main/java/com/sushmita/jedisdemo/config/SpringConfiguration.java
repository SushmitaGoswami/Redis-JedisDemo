package com.sushmita.jedisdemo.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

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
		
	}
	
	
}
