package com.nexon.narket.api.common.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImgVO {

	private int imgNo;
	private int productNo;
	private String filePath;
	private String fileNm;
	private long fileSize;
	private Date regDtt;
}
