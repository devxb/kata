package karatechop

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

internal class BinaryUtilKarateChopTest : DescribeSpec({
    val karateChop = KarateChopFactory.binaryUtilKarateChop

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
            1 to listOf(3) to false,
            1 to listOf(1) to true,
            3 to listOf(1, 3, 5) to true,
            10 to listOf(10, 9, 8, 5, 4, 3, 2, 1) to true,
            7 to listOf(1, 2, 3, 4, 5, 6) to false
        )
    }
}
