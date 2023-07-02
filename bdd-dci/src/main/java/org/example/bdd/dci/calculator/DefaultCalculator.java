package org.example.bdd.dci.calculator;

import org.example.bdd.dci.util.Assert;

public class DefaultCalculator implements Calculator {

	@Override
	public String calculate(String price, int count) {
		Assert.nonNull(price, () -> "price cannot be null");
		Assert.nonZero(count, () -> "count cannot be zero");

		return "$" + (priceToNumber(price) * count);
	}

	private Double priceToNumber(String price) {
		return Double.valueOf(price.substring(1));
	}

}
