package com.nexon.narket.api.product.service;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

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
	
	/**
	 * 상품 리스트 조회
	 * 
	 * @return ProductVO 의 리스트
	 * @throws Exception
	 */
	public List<ProductVO> getProducts() throws Exception {		
		return Optional.ofNullable(this.productDao.getProducts())
			.map(products -> products.stream()
					.map(이미지번호_리스트_조회)
					.collect(Collectors.toList()))
			.orElseGet(null);
	}
	
	public ProductVO getProduct(int productNo) throws Exception {		
		return Optional.ofNullable(this.productDao.getProduct(productNo))
				.map(이미지번호_리스트_조회)
				.orElseGet(null);
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
	
	public List<ProductVO> getSellProducts(String empNo) throws Exception {		
		return Optional.ofNullable(this.productDao.getSellProducts(empNo))
				.map(products -> products.stream()
						.map(이미지번호_리스트_조회)
						.collect(Collectors.toList()))
				.orElseGet(null); 
	}
	
	private UnaryOperator<ProductVO> 이미지번호_리스트_조회 = (product) -> {
		
		try {
			List<ImgVO> imgs = this.fileService.getImgs(product.getProductNo());
			List<Integer> imgNos = imgs.stream()
					.map(img -> img.getImgNo())
					.collect(Collectors.toList());
			
			product.setImgNos(imgNos);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return product;
	};

}
