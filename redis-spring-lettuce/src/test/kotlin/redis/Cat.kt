package redis

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

@RedisHash("Cat")
data class Cat(@Id val id: Long,
               val name: String,
               val gender: Gender
): Serializable

enum class Gender {
    MALE,
    FEMALE,
    ;
}
