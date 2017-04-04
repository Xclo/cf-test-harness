package org.cf.canary.redis.config.data;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
@Profile("redis-cloud")
public class RedisCloudConfig extends AbstractCloudConfig {

    @Bean
    public RedisConnectionFactory redisConnection() {
        return connectionFactory().redisConnectionFactory();
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
    	return new StringRedisTemplate(redisConnection());
    }

}
