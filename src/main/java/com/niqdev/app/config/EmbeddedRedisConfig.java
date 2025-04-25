package com.niqdev.app.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import redis.embedded.RedisServer;

@Configuration
public class EmbeddedRedisConfig {
	
    private RedisServer redisServer;

    @Bean
    RedisServer redisServer() throws Exception {
        return redisServer;
    }
    
    @PostConstruct
    public void startRedis() throws IOException {
        if (redisServer == null || !redisServer.isActive()) {
            redisServer = new RedisServer(6379);
            redisServer.start();
        }
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null && redisServer.isActive()) {
            redisServer.stop();
        }
    }    
}
