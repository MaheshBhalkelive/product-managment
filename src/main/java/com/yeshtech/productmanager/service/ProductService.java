package com.yeshtech.productmanager.service;

import java.util.List;

import com.yeshtech.productmanager.model.Product;

public interface ProductService {
	
	public Product createProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public List<Product> getListOfAllProducts();
	
	public Product getProductDetailsByProductId(Long productId);

	public void deleteProduct(Long ProductId);
}
