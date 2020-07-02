package com.hsaalen.junit.business;

import java.util.List;

import com.hsaalen.junit.business.exception.DifferentCurrenciesException;
import com.hsaalen.junit.model.Amount;
import com.hsaalen.junit.model.Product;

public interface ClientBO {

	Amount getClientProductsSum(List<Product> products)
			throws DifferentCurrenciesException;

}