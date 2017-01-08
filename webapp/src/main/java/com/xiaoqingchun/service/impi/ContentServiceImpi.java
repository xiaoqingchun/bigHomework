package com.xiaoqingchun.service.impi;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoqingchun.dao.ContentDao;
import com.xiaoqingchun.dao.TrxDao;
import com.xiaoqingchun.model.Content;
import com.xiaoqingchun.model.Trx;
import com.xiaoqingchun.model.common.Product;
import com.xiaoqingchun.model.common.ProductList;
import com.xiaoqingchun.service.ContentService;

@Component
public class ContentServiceImpi implements ContentService {

	@Autowired
	private ContentDao contentdao;

	@Autowired
	private TrxDao trxdao;

	public boolean deleteByPrimaryKey(Integer id) {
		List<Trx> list = trxdao.getTrxListByContentId(id);
		for (Trx trx : list) {
			if (trx.getContentId() == id) {
				return false;
			}
		}
		contentdao.delete(id);
		return true;
	}

	public List<ProductList> getProductList(Integer userId) {
		List<Trx> record = trxdao.getTrxListByPersonId(userId);
		List<ProductList> productList = new LinkedList<ProductList>();
		if (record.isEmpty() || record == null) {
			for (ProductList plist : getAllProductList()) {
				List<Trx> list = trxdao.getTrxListByContentId(plist.getId());
				for (Trx trx : list) {
					if (plist.getId() == trx.getContentId()) {
						plist.setBuy(true);
						plist.setSell(true);
					}
				}
				productList.add(plist);
			}
			return productList;
		} else {
			for (ProductList plist : getAllProductList()) {
				for (Trx trx : record) {
					if (plist.getId() == trx.getContentId()) {
						plist.setBuy(true);
						plist.setSell(true);
					}
				}
				productList.add(plist);
			}
		}
		return productList;
	}

	public List<ProductList> getAllProductList() {
		List<ProductList> list = new LinkedList<ProductList>();
		List<Content> contentList = contentdao.getAllContent();
		for (Content content : contentList) {
			ProductList productl = new ProductList();
			productl.setId(content.getId());
			try {
				productl.setImage(new String(content.getIcon(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			productl.setPrice(content.getPrice());
			productl.setTitle(content.getTitle());
			list.add(productl);
		}
		return list;
	}

	public Product getProduct(Integer contentId) {
		Product product = new Product();
		Content content = contentdao.getContent(contentId);
		List<Trx> list= trxdao.getTrxListByContentId(contentId);
		if(list!=null && !list.isEmpty()){
			product.setBuy(true);
			product.setSell(true);
		}
		else{
			product.setBuy(false);
			product.setSell(false);
		}
		try {
			product.setId(content.getId());
			product.setImage(new String(content.getIcon(), "utf-8"));
			product.setPrice(content.getPrice());
			product.setTitle(content.getTitle());
			product.setSummary(content.getAbstract_());
			product.setDetail(new String(content.getText(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return product;
	}

	public Product insertProduct(Product product) {
		int id= 1;
		try {
			 id=contentdao.insertContent(product.getPrice(), product.getTitle(), product.getImage().getBytes("utf-8"),
					product.getSummary(), product.getDetail().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		int id = contentdao.getLastId();
		product.setId(id);
		return product;
	}

	public Product updateProduct(Product product) {
		try {
			contentdao.updateContent(product.getPrice(), product.getTitle(), product.getImage().getBytes("utf-8"),
					product.getSummary(), product.getDetail().getBytes("utf-8"), product.getId());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Content content = contentdao.getContent(product.getId());
		if (content != null) {
			product.setId(content.getId());
			product.setPrice(content.getPrice());
			try {
				product.setDetail(new String(content.getText(), "utf-8"));
				product.setImage(new String(content.getIcon(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			product.setTitle(content.getTitle());
			product.setBuy(true);
			product.setSell(true);
			return product;
		}
		return product;
	}

}
