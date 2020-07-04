package com.nexon.narket.api.common.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/file")
public class FileController {

	@GetMapping("/img")
	public void getImg(HttpServletResponse res) throws Exception {
		
		FileSystemResource img = new FileSystemResource("C:/testimg.jpg");
		res.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(img.getInputStream(), res.getOutputStream());
	}
}
