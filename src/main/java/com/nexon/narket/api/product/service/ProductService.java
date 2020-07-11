package com.nexon.narket.api.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexon.narket.api.common.service.FileService;
import com.nexon.narket.api.common.vo.ImgVO;
import com.nexon.narket.api.product.dao.ProductDao;
import com.nexon.narket.api.product.vo.CategoryVO;
import com.nexon.narket.api.product.vo.ProductVO;
import com.nexon.narket.api.product.vo.StateVO;
import com.nexon.narket.core.base.BaseService;

@Service
public class ProductService extends BaseService {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private FileService fileService;
	
	public List<ProductVO> getProducts() throws Exception {
		List<ProductVO> products = this.productDao.getProducts();
		
		if(products != null) {
			for(ProductVO product : products) {
				ProductVO origin = product;
				
				List<ImgVO> imgs = this.fileService.getImgs(product.getProductNo());
				List<Integer> imgNos = this.convertImgsToIntList(imgs);
				product.setImgNos(imgNos);
				products.set(products.indexOf(origin), product);
			}
			
		}
		
		return products; 
	}
	
	public ProductVO getProduct(int productNo) throws Exception {
		ProductVO product = this.productDao.getProduct(productNo);
		
		if(product != null) {
			List<ImgVO> imgs = this.fileService.getImgs(productNo);
			List<Integer> imgNos = this.convertImgsToIntList(imgs);
			product.setImgNos(imgNos);
		}
		
		return product;
	}
	
	public int insertProduct(ProductVO product) throws Exception {
		return (this.productDao.insertProduct(product) > 0) ? product.getProductNo() : -1;
	}
	
	public int updateProduct(ProductVO product) throws Exception {
		return this.productDao.updateProduct(product);
	}
	
	public int deleteProduct(int productNo) throws Exception {
		return this.productDao.deleteProduct(productNo);
	}
	
	public List<CategoryVO> getCategories() throws Exception {
		return this.productDao.getCategories();
	}
	
	public List<StateVO> getStates() throws Exception {
		return this.productDao.getStates();
	}
	

	private List<Integer> convertImgsToIntList(List<ImgVO> imgs) throws Exception {
		List<Integer> result = null;
		if(imgs != null) {
			result = new ArrayList<Integer>();
			for(ImgVO img : imgs) result.add(img.getImgNo());
		}
		
		return result;
	}
	
	public List<ProductVO> getSellProducts(String empNo) throws Exception {
		List<ProductVO> products = this.productDao.getSellProducts(empNo);
		
		if(products != null) {
			for(ProductVO product : products) {
				ProductVO origin = product;
				
				List<ImgVO> imgs = this.fileService.getImgs(product.getProductNo());
				List<Integer> imgNos = this.convertImgsToIntList(imgs);
				product.setImgNos(imgNos);
				products.set(products.indexOf(origin), product);
			}
			
		}
		
		return products; 
	}

}
