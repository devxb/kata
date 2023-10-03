package redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
internal open class RedisConfig {

    @Bean
    open fun redisTemplate(): RedisTemplate<String, Any> = RedisTemplate<String, Any>()
        .apply {
            this.setConnectionFactory(redisConnectionFactory())
        }

    @Bean
    open
    fun redisConnectionFactory(): RedisConnectionFactory =
        LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6379))

}
