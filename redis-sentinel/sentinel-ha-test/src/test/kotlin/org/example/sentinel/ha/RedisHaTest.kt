package org.example.sentinel.ha

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [TestRoot::class])
internal class RedisHaTest(private val redisTemplate: RedisTemplate<String, Int>) :
    DescribeSpec({

        describe("RedisTemplate Set 로직은") {

            context("1000개의 데이터을 입력중에 failover가 발생하면") {
                val repeatCount = 1_000;
                val sleepTime = 100L;

                it("데이터가 손실되지 않을것이다.") {
                    repeat(repeatCount) {
                        println("input test:${it} -> ${it}")
                        try {
                            redisTemplate.opsForValue()["test:${it}"] = it
                            Thread.sleep(sleepTime)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    val allSavedKeys =
                        redisTemplate.execute { it.keyCommands().keys("*".toByteArray()) }
                    allSavedKeys!!.size shouldBe repeatCount
                }
            }
        }
    })
