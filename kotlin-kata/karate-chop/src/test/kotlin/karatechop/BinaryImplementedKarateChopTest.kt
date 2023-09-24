package karatechop

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BinaryImplementedKarateChopTest : DescribeSpec({

    val karateChop = KarateChopFactory.binaryImplementedKarateChop

    describe("chop 메소드는") {

        context("입력값이 주어지면,") {

            it("예상된 결과를 반환한다.") {
                sources.forAll { (source, expected) ->
                    val result = karateChop.chop(source.first, source.second)

                    result shouldBe expected
                }
            }
        }

    }
}) {
    companion object {
        val sources = listOf(
            3 to listOf(1) to false,
            3 to listOf(1, 3) to true,
            10 to listOf(1, 5, 10) to true,
            11 to listOf(11, 3, 5) to true
        )
    }

}
