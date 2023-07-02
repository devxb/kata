package org.example.bdd.dci.goods;

import org.assertj.core.api.Assertions;
import org.example.bdd.dci.calculator.Calculator;
import org.example.bdd.dci.calculator.DefaultCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("FieldCanBeLocal")
@DisplayName("Goods 클래스")
class GoodsTest {

	@Nested
	@DisplayName("생성자는")
	class Describe_of {

		@Nested
		@DisplayName("가격과, Calculator을 받으면")
		class Context_integerPrice_calculator {

			private final String price = "$1000";
			private final Calculator calculator = new DefaultCalculator();

			@Test
			@DisplayName("해당 가격과, Calculator가 포함된 Goods를 생성한다.")
			void It_Create_Goods() {
				Throwable result = Assertions.catchThrowable(() -> new Goods(price, calculator));

				Assertions.assertThat(result).isNull();
			}

		}

		@Nested
		@DisplayName("가격으로 null을 받으면")
		class Context_null_price {

			private final String price = null;
			private final Calculator calculator = new DefaultCalculator();

			@Test
			@DisplayName("IllegalArgumentException을 던진다.")
			void It_Throw_IllegalArgumentException() {
				Throwable result = Assertions.catchThrowable(() -> new Goods(price, calculator));

				Assertions.assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
			}

		}

		@Nested
		@DisplayName("Calculator로 null을 받으면")
		class Context_null_calculator {

			private final String price = "$1000";
			private final Calculator calculator = null;

			@Test
			@DisplayName("IllegalArgumentException을 던진다.")
			void It_Throw_IllegalArgumentException() {
				Throwable result = Assertions.catchThrowable(() -> new Goods(price, calculator));

				Assertions.assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
			}

		}

	}

	@Nested
	@DisplayName("getTotalPrice 메소드는")
	class Describe_getTotalPrice {

		private final Calculator calculator = new DefaultCalculator();
		private final String goodsPrice = "$123";
		private final Goods goods = new Goods(goodsPrice, calculator);

		@Nested
		@DisplayName("정수를 받으면")
		class Context_integerCount {

			private final int count = 3;

			@Test
			@DisplayName("상품의 가격에 정수를 곱한값을 반환한다.")
			void It_Return_Price() {
				String result = goods.getTotalPrice(count);

				Assertions.assertThat(result).isEqualTo("$369.0");
			}

		}

		@Nested
		@DisplayName("0을 받으면")
		class Context_zeroCount {

			private final int zeroCount = 0;

			@Test
			@DisplayName("IllegalArgumentException을 던진다.")
			void It_Throw_IllegalArgumentException() {
				Throwable result = Assertions.catchThrowable(() -> goods.getTotalPrice(zeroCount));

				Assertions.assertThat(result.getClass()).isEqualTo(IllegalArgumentException.class);
			}

		}

	}

}
