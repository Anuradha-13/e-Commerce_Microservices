package com.spring.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.product.dto.ProductDto;
import com.spring.product.entity.Product;
import com.spring.product.exception.ProductNotFoundException;
import com.spring.product.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	ProductRepo productRepo;

	@Override
	public Product getProductById(int id) {

		LOGGER.debug("ProductServiceImpl::getProductById");
		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new ProductNotFoundException("Product does not exist");
		}
	}

	@Override
	public List<ProductDto> getProductByName(String productName) {
		return productRepo.findByNameIgnoreCaseContaining(productName).stream()
				.map(prod -> modelMapper.map(prod, ProductDto.class)).collect(Collectors.toList());

	}

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = modelMapper.map(productDto, Product.class);
		Product savedProduct = productRepo.save(product);
		ProductDto productResponse = modelMapper.map(savedProduct, ProductDto.class);
		return productResponse;

	}

	@Override
	public List<ProductDto> getAllProducts() {
		LOGGER.debug("ProductServiceImpl::getAllProducts");
		return productRepo.findAll().stream().map(prod -> modelMapper.map(prod, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto updateProduct(int id, ProductDto productDto) {
		LOGGER.debug("ProductServiceImpl::updateProduct");
		Product product = modelMapper.map(productDto, Product.class);
		Product existingProduct = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product does not exist."));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		productRepo.save(existingProduct);
		ProductDto productResponse = modelMapper.map(existingProduct, ProductDto.class);
		return productResponse;
	}

	@Override
	public void deleteProduct(int id) {
		LOGGER.debug("ProductServiceImpl::deleteProduct");
		Product product = productRepo.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Invalid Product Id."));
		productRepo.delete(product);
	}

}
