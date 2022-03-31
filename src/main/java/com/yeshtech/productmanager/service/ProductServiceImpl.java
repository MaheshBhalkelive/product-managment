package com.yeshtech.productmanager.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeshtech.productmanager.exception.ResourceNotFoundException;
import com.yeshtech.productmanager.model.Product;
import com.yeshtech.productmanager.repository.ProductRepository;

@Service 		//spring internally scan this class as component scan
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	// Create new product
	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	//Update Product
	@Override
	public Product updateProduct(Product product) {
		Optional<Product> productDb = this.productRepository.findById(product.getProductId());
		if(productDb.isPresent()) {
			Product productUpdate = productDb.get();
			productUpdate.setProductId(product.getProductId());;
			productUpdate.setProductName(product.getProductName());;
			productUpdate.setProductDescription(product.getProductDescription());;
			productUpdate.setPrice(product.getPrice());
			productRepository.save(productUpdate);
			return productUpdate;
		}else {
			throw new ResourceNotFoundException("Product Not fround : " + product.getProductId());
		}

	}
	// Get list of all products
	@Override
	public List<Product> getListOfAllProducts() {
		return this.productRepository.findAll();
	}

	//Get product details by product ID
	@Override
	public Product getProductDetailsByProductId(Long productId) {
		Optional<Product> productDb = this.productRepository.findById(productId);
		if(productDb.isPresent()) {
			return productDb.get();
		}else {
			throw new ResourceNotFoundException("Product not forund : "+ productId);
		}
	}
	
	// Delete Existing product by product ID
	@Override
	public void deleteProduct(Long ProductId) {
		Optional<Product> productDb = this.productRepository.findById(ProductId);
		if(productDb.isPresent()) {
			this.productRepository.delete(productDb.get());
		}else {
			throw new ResourceNotFoundException("product Not Found : " + ProductId);
		}
	}
	
}
