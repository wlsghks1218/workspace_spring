package org.joonzis.controller;


import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class UploadController {
	
	/*
	 * MultipartFile의메소드
	 * 
	 * String getName()					파라미터의 이름 <input> 태그의 이름
	 * String getOriginalFileName()		업로드 되는 파일의 이름	
	 * boolean isEmpty()				파일이 존재하지 않는 경우 true
	 * long getSize()					업로드 되는 파일의 크기
	 * byte[] getBytes()				byte[]로 파일 데이터 반환
	 * InputStream getInputStream()		파일 데이터와 연결된 InputStream 반환
	 * transferTo(File file)			파일 저장
	 * 
	 */
	
	@GetMapping("/uploadForm")
	public String uploadForm() {
		log.info("upload form");
		return "uploadForm";
	}
	@PostMapping("uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("-------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Name : " + multipartFile.getSize());
		}
	}
	
	@GetMapping("/uploadAsync")
	public String uploadAsync() {
		log.info("upload Async");
		return "uploadAsync";
	}
	
	@PostMapping("uploadAsyncAction")
	public void uploadAsyncPost(MultipartFile[] uploadFile, Model model) {
		log.info("upload Async post...");
		
		String uploadFolder = "C:\\upload";

		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Name : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name : " + uploadFileName);
			
			try {
				File saveFile = new File(uploadFolder, uploadFileName);
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
}
