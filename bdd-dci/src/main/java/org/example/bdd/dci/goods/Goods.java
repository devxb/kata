package org.example.bdd.dci.goods;

import org.example.bdd.dci.calculator.Calculator;
import org.example.bdd.dci.util.Assert;

public class Goods {

	private final String price;
	private final Calculator calculator;

	public Goods(String price, Calculator calculator) {
		Assert.nonNull(price, () -> "price cannot be null");
		Assert.nonNull(calculator, () -> "calculator cannot be null");
		this.price = price;
		this.calculator = calculator;
	}

	public String getTotalPrice(int count) {
		Assert.nonZero(count, () -> "count cannot be zero \"" + count + "\"");
		return calculator.calculate(price, count);
	}

}
