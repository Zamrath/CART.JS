package com.example;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ProductsDB;

@RestController
public class RestServiceController {
	
	@Autowired
	private IProductRepository repo;
	static final Logger logger = LogManager.getLogger(RestServiceController.class.getName());
	
	// REQUEST ALL
	@RequestMapping(value="/products/all", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> createBook(){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			Iterator<ProductsDB> iter = repo.findAll().iterator();
			int iterInt = 0;
			while(iter.hasNext()){
				//System.out.println(iter.next().getName());
				model.put(Integer.toString(iterInt), iter.next());
				iterInt++;
			}
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		model.put("msg", "All the products are successfully loaded");
		return model;
	}	
	
	// PURCHASE
	@RequestMapping(value="/products/purchase/{id}/{quantity}/{stock}", method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateBook(@PathVariable String id, @PathVariable String quantity, @PathVariable String stock){
		Map<String, Object> model = new HashMap<String, Object>();
		ProductsDB product;
		try{
			product = repo.findOne((long)Integer.parseInt(id));
			product.setStock(Integer.toString(Integer.parseInt(stock)-Integer.parseInt(quantity)));
			repo.save(product);
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		model.put("msg", "Your purchase has been successfully completed. Thank you.");
		return model;
	}
	
	// CREATE
	@RequestMapping(value="/products/create/{name}/{price}/{stock}", method=RequestMethod.POST, headers="Accept=application/json")
	@ResponseBody
	public Map<String, Object> createBook(@PathVariable String name, @PathVariable String price, @PathVariable String stock){
		Map<String, Object> model = new HashMap<String, Object>();
		ProductsDB product = new ProductsDB(name, price, stock);
		try{
			repo.save(product);
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		model.put("msg", "Product " + String.valueOf(product.getName()) + " is successfully added");
		return model;
	}
	
	// READ
	@RequestMapping(value="/products/read/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object>  readBook(@PathVariable String id){
		Map<String, Object> model = new HashMap<String, Object>();
		ProductsDB product;
		try {
			product = repo.findOne((long)Integer.parseInt(id));
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		if(product == null){
			String errorMsg = "No product found for id " + id;
			logger.error(errorMsg);
			model.put("msg", errorMsg);
			return model;
		}
		else{
			model.put("msg", product.getName() + " has a stock of " + product.getStock());
			return model;
		}
	}
	
	// UPDATE
	@RequestMapping(value="/products/update/{id}/{name}/{price}/{stock}", method=RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateBook(@PathVariable String id, @PathVariable String name, @PathVariable String price, @PathVariable String stock){
		Map<String, Object> model = new HashMap<String, Object>();
		ProductsDB product;
		try{
			product = repo.findOne((long)Integer.parseInt(id));
			product.setName(name);
			product.setPrice(price);
			product.setStock(stock);
			repo.save(product);
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		model.put("msg", product.getName() + ": Stock = " + product.getStock() + ", Price = " + product.getPrice());
		return model;
	}
	
	// DELETE
	@RequestMapping(value="/products/delete/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String, Object> deleteBook(@PathVariable String id){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			repo.delete((long)Integer.parseInt(id));
		} catch(Exception e){
			logger.error(e.getMessage());
			model.put("msg", e.getMessage());
			return model;
		}
		model.put("msg", "Product with ID " + id + " is successfully removed");
		return model;
	}
	
}
