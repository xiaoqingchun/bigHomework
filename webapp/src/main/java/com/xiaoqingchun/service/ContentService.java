package com.xiaoqingchun.service;

import java.util.List;

import com.xiaoqingchun.model.common.Product;
import com.xiaoqingchun.model.common.ProductList;

public interface ContentService {

	boolean deleteByPrimaryKey(Integer id);

	List<ProductList> getProductList(Integer userId) ;

    List<ProductList> getAllProductList();

	Product getProduct(Integer contentId);

	Product insertProduct(Product product);

	Product updateProduct(Product product);

}
