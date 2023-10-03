package redis

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.test.context.ContextConfiguration


@SpringBootTest
@ContextConfiguration(classes = [TestRoot::class])
internal class RedisTest(private val redisTemplate: RedisTemplate<String, Any>) : AnnotationSpec() {

    @Test
    fun `Redis 직접 사용 테스트`() {
        // given
        val cat = Cat(1L, "치삼이", Gender.FEMALE)

        val valueOperation: ValueOperations<String, Any> = redisTemplate.opsForValue()
        valueOperation.set("cat:${cat.id}", cat)

        // when
        val result = valueOperation.get("cat:${cat.id}") as Cat

        // then
        result shouldBe cat
            .also { println(it) }
    }
}
