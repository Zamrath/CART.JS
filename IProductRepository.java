package com.example;

import org.springframework.data.repository.CrudRepository;
import com.example.ProductsDB;

public interface IProductRepository extends CrudRepository<ProductsDB, Long> {
	
}
