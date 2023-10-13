package org.example.sentinel.ha

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisSentinelConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
internal open class SentinelConfig {

    private val sentinels: Set<String> =
        setOf("localhost:26379", "localhost:26380", "localhost:26381")

    @Bean("persistenceRedisTemplate")
    internal open fun redisTemplate(): RedisTemplate<String, Int> =
        RedisTemplate<String, Int>().apply {
            this.connectionFactory = redisConnectionFactory()
        }

    @Bean("persistenceRedisConnectionFactory")
    internal open fun redisConnectionFactory(): RedisConnectionFactory {
        val redisSentinelConfiguration = RedisSentinelConfiguration("mymaster", sentinels)
        return LettuceConnectionFactory(redisSentinelConfiguration)
    }
}
