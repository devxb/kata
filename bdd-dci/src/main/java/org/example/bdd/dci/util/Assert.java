package org.example.bdd.dci.util;

import java.util.Objects;
import java.util.function.Supplier;

public final class Assert {

	private Assert() {
		throw new UnsupportedOperationException("Cannot invoke constructor \"Assert()\"");
	}

	public static void nonZero(Number number, Supplier<String> messageSupplier) {
		if("0".equals(number.toString())) {
			throw new IllegalArgumentException(messageSupplier.get());
		}
	}

	public static void nonNull(Object object, Supplier<String> messageSupplier) {
		if(object == null) {
			throw new IllegalArgumentException(messageSupplier.get());
		}
	}

}
