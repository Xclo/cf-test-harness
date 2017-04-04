package org.cf.canary.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.cf.canary.redis.config.SpringApplicationContextInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CanaryRedisApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(CanaryRedisApplication.class).
						initializers(new SpringApplicationContextInitializer())
						.application()
						.run(args);
	}
}
