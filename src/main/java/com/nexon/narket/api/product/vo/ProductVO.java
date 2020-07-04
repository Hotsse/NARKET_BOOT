package com.nexon.narket.api.product.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ProductVO {
	
	private int productNo;
	private String stateCd;
	private String stateNm;
	private int categoryNo;
	private String categoryNm;
	private String title;
	private String content;
	private int price;
	private String regEmpNo;
	private String regEmpNm;
	private String regDeptNm;
	private Date regDtt;
	private Date bumpDtt;
	private int viewCnt;
	private int likeCnt;
	private List<Integer> imgNos;
}
