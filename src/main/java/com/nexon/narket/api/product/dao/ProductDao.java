package com.nexon.narket.api.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nexon.narket.api.product.vo.CategoryVO;
import com.nexon.narket.api.product.vo.ProductVO;
import com.nexon.narket.api.product.vo.StateVO;
import com.nexon.narket.core.base.BaseDao;

@Repository
public class ProductDao extends BaseDao {
	
	public List<ProductVO> getProducts() throws Exception {
		return sqlSession.selectList("product.product.getProducts");
	}
	
	public ProductVO getProduct(int productNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("productNo", productNo);
		
		return sqlSession.selectOne("product.product.getProduct", param);
	}
	
	public int insertProduct(ProductVO product) throws Exception {
		return sqlSession.insert("product.product.insertProduct", product);
	}
	
	public List<CategoryVO> getCategories() throws Exception {
		return sqlSession.selectList("product.product.getCategories");
	}
	
	public List<StateVO> getStates() throws Exception {
		return sqlSession.selectList("product.product.getStates");
	}
	
	public List<ProductVO> getSellProducts(String empNo, String stateCd) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("empNo", empNo);
		param.put("stateCd", stateCd);
		
		return sqlSession.selectList("product.product.getSellProducts", param);
	}
}
