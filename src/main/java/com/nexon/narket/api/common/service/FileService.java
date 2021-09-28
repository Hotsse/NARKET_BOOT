package com.nexon.narket.api.common.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nexon.narket.api.common.dao.FileDao;
import com.nexon.narket.api.common.vo.ImgVO;
@Service
public class FileService {
	
	@Value("${file.path}")
	private String fileUploadPath;
	
	@Value("${file.path-delimeter}")
	private String filePathDelim;
	
	@Autowired
	private FileDao fileDao;
	
	public List<ImgVO> getImgs(int productNo) throws Exception {
		return this.fileDao.getImgs(productNo);
	}
	
	public ImgVO getImg(int productNo, int imgNo) throws Exception {
		return this.fileDao.getImg(productNo, imgNo);
	}
	
	public void downloadImg(int productNo, int imgNo, HttpServletResponse res) throws Exception {
		
		ImgVO img = this.getImg(productNo, imgNo);
		
		if(img != null) {
			String filePath = fileUploadPath + img.getFilePath();
			FileSystemResource imgFile = new FileSystemResource(filePath.replace("\\\\", "/"));
			
			String mediaType = MediaType.ALL_VALUE;
			if(img.getFileNm().contains(".jpg") || img.getFileNm().contains(".JPG")) {
				mediaType = MediaType.IMAGE_JPEG_VALUE;
			}
			else if(img.getFileNm().contains(".png") || img.getFileNm().contains(".PNG")) {
				mediaType = MediaType.IMAGE_PNG_VALUE;
			}
			else if(img.getFileNm().contains(".gif") || img.getFileNm().contains(".gif")) {
				mediaType = MediaType.IMAGE_GIF_VALUE;
			}
			
			res.setContentType(mediaType);
			StreamUtils.copy(imgFile.getInputStream(), res.getOutputStream());
		}
	}
	
	public void uploadImg(int productNo, String empNo, MultipartFile file) {
		
		try {
			
			// 파일 경로 생성
			String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")); // 날짜별 디렉토리 분기		
			UUID uuidFileNm = UUID.randomUUID(); // 파일명 암호화
			String filePath = today + filePathDelim + uuidFileNm;
			File fs = new File(fileUploadPath + filePath);
			
			// 파일 저장
			boolean fileSaved = false;
			if(!fs.exists()) {
				
				if(fs.getParentFile().mkdirs()) {				
					// 파일 빈깡통 생성
					fs.createNewFile();
				}
				
				// 빈깡통에 업로드 파일 덮어쓰기
				file.transferTo(fs);
				fileSaved = true;
			}
			
			// 파일 저장 성공 시, DB 저장
			if(fileSaved) {
				
				// 파일 메타데이터 생성
				String fileNm = file.getOriginalFilename();
				long fileSize = file.getSize();
				
				ImgVO newImg = ImgVO.builder()
						.productNo(productNo)
						.filePath(filePath)
						.fileNm(fileNm)
						.fileSize(fileSize)
						.build();
				
				// 파일 메타데이터 DB 저장
				this.fileDao.insertImg(newImg);
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
