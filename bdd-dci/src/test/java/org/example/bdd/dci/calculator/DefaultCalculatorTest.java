package org.example.bdd.dci.calculator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("FieldCanBeLocal")
@DisplayName("DefaultCalculator 클래스")
class DefaultCalculatorTest {

	@Nested
	@DisplayName("calculate 메소드는")
	class Describe_Calculate {

		private final Calculator calculator = new DefaultCalculator();

		@Nested
		@DisplayName("만약, 상품금액과 상품갯수로 정수가 주어진다면")
		class Context_with_integerPrice {
			private final String goodsPrice = "$1000";
			private final int goodsCount = 3;

			@Test
			@DisplayName("상품금액에 상품갯수를 곱한값을 반환한다.")
			void It_$3000() {
				String result = calculator.calculate(goodsPrice, goodsCount);

				Assertions.assertThat(result).isEqualTo("$3000.0");
			}

		}

		@Nested
		@DisplayName("만약, 상품금액으로 실수, 상품갯수로 정수가 주어진다면")
		class Context_with_doublePrice {

			private final String goodsPrice = "$1000.123";
			private final int goodsCount = 5;

			@Test
			@DisplayName("상품금액에 상품갯수를 곱한값을 반환한다.")
			void It_$2000246() {
				String result = calculator.calculate(goodsPrice, goodsCount);

				Assertions.assertThat(result).isEqualTo("$5000.615");
			}

		}

		@Nested
		@DisplayName("만약, 상품금액으로 정수, 상품갯수로 0이 주어진다면")
		class Context_with_zeroCount {

			private final String goodsPrice = "$3000";
			private final int goodsCount = 0;

			@Test
			@DisplayName("IllegalArgumentException을 던진다.")
			void It_IllegalArgumentException() {
				Throwable result = Assertions.catchException(() -> calculator.calculate(goodsPrice, goodsCount));

				Assertions.assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
			}

		}

		@Nested
		@DisplayName("만약, 상품금액으로 Null, 상품갯수로 정수가 주어진다면")
		class Context_with_NullPrice {

			private final String goodsPrice = null;
			private final int goodsCount = 1;

			@Test
			@DisplayName("IllegalArgumentException을 던진다.")
			void It_IllegalArgumentException() {
				Throwable result = Assertions.catchThrowable(() -> calculator.calculate(goodsPrice, goodsCount));

				Assertions.assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
			}

		}

	}

}
