package com.example.amazon_agency_task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Objects;

import static com.example.amazon_agency_task.constants.NumberConstants.CACHE_STORAGE_TIME_IN_MINUTE;

@Configuration
public class CacheConfig {



    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        var config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CACHE_STORAGE_TIME_IN_MINUTE))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return RedisCacheManager.builder(Objects.requireNonNull(connectionFactory))
                .cacheDefaults(config)
                .build();
    }
}
