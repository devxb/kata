package org.example.sentinel.ha

import io.kotest.core.spec.style.DescribeSpec
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [TestRoot::class])
internal class RedisHaTest(private val redisTemplate: RedisTemplate<String, Int>) :
    DescribeSpec({

        describe("Sentinel failover시 데이터 유실 테스트") {

            context("데이터를 보내는중에 failover가 발생하면,") {

                it("데이터는 손실될것이다.") {
                    val repeatCount = 1_000;

                    repeat(repeatCount) {
                        println("input ${it} -> ${it}")
                        redisTemplate.opsForValue()[it.toString()] = it
                        Thread.sleep(100)
                    }
                }
            }
        }
    })
