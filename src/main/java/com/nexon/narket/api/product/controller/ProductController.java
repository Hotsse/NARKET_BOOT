package com.nexon.narket.api.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.nexon.narket.api.common.service.FileService;
import com.nexon.narket.api.product.constants.StateEnum;
import com.nexon.narket.api.product.service.ProductService;
import com.nexon.narket.api.product.vo.CategoryVO;
import com.nexon.narket.api.product.vo.ProductVO;
import com.nexon.narket.api.product.vo.StateVO;
import com.nexon.narket.core.base.BaseController;

@RestController
@RequestMapping(value = "/api/product", produces = "application/json;charset=utf-8")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/list")
	public ResponseEntity<List<ProductVO>> list() throws Exception {
		
		List<ProductVO> products = this.productService.getProducts();		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/list/{productNo}")
	public ResponseEntity<ProductVO> detail(@PathVariable(name = "productNo", required = true) int productNo) throws Exception {
		
		ProductVO product = this.productService.getProduct(productNo);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/write")
	public ResponseEntity<Void> write(ProductVO product, HttpServletRequest req, MultipartRequest mreq) throws Exception {
		
		// 기본 데이터 추가
		product.setStateCd(StateEnum.INPROGRESS.getCode());
		// 작성자 데이터 추가
		HttpSession session = req.getSession();
		String empNo = session.getAttribute("empNo").toString();
		product.setRegEmpNo(empNo);
		
		this.log.debug(product.toString());
		int productNo = this.productService.insertProduct(product);
		
		if(productNo > 0) {
			List<MultipartFile> files = mreq.getFiles("imgs");
			for(MultipartFile file : files) {
				this.fileService.uploadImg(productNo, empNo, file);
			}
		}
		
		return ResponseEntity.created(null).build();
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<CategoryVO>> getCategories() throws Exception {
		
		List<CategoryVO> categories = this.productService.getCategories();
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/state")
	public ResponseEntity<List<StateVO>> getStates() throws Exception {
		
		List<StateVO> states = this.productService.getStates();
		return ResponseEntity.ok(states);
	}
	
	@GetMapping("/img/{productNo}/{imgNo}")
	public void getImg(@PathVariable(name = "productNo", required = true) int productNo
			, @PathVariable(name = "imgNo", required = true) int imgNo
			, HttpServletResponse res) throws Exception {
		
		this.fileService.downloadImg(productNo, imgNo, res);
	}
	
	@GetMapping("/list/sell")
	public List<ProductVO> getSellProducts(HttpServletRequest req) throws Exception {
		// 작성자 데이터 추가
		HttpSession session = req.getSession();
		String empNo = session.getAttribute("empNo").toString();
		
		return this.productService.getSellProducts(empNo, StateEnum.INPROGRESS.getCode());
	}
}
