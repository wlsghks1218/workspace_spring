package org.joonzis.controller;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joonzis.domain.BoardAttachVO;
import org.joonzis.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@Autowired
	private BoardService service;
	
	@GetMapping("/uploadAsync")
	public String uploadAsync() {
		log.info("upload Async");
		return "uploadAsync";
	}
	
	@ResponseBody
	@PostMapping(value = "/uploadAsyncAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<BoardAttachVO>> uploadAsyncPost(MultipartFile[] uploadFile, Model model) {
		
		List<BoardAttachVO> list = new ArrayList<BoardAttachVO>();
		
		log.info("upload Async post...");
		
		String uploadFolder = "C:\\upload";
		
		
		// make folder ---------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("uploadPath : " + uploadPath);
		
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multipartFile : uploadFile) {
			log.info("-------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Name : " + multipartFile.getSize());
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name : " + uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				BoardAttachVO attachDto = new BoardAttachVO();
				attachDto.setUuid(uuid.toString());
				attachDto.setUploadPath(getFolder());
				attachDto.setFileName(multipartFile.getOriginalFilename());
				
				list.add(attachDto);
				
				
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return new ResponseEntity<List<BoardAttachVO>>(list, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> downloadFile(String fileName){
		log.info("download file .... " + fileName);
		Resource resource = new FileSystemResource("C:\\upload\\" + fileName);
		
		log.info("resource : " + resource);
		
		String resourceName = resource.getFilename();
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attach; fileName=" + new String(resourceName.getBytes("utf-8"),"ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(@RequestBody Map<String, String> requestBody){
	    String fileName = requestBody.get("fileName");
	    String uuid = requestBody.get("uuid");
	    log.info(fileName);
	    log.info(uuid);
		
		File file = null;
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));
			file.delete();
			service.deleteAttach(uuid);
			log.info("-------");
			log.info("-------");
		} catch (Exception e) {
			log.info("----catch---");
			log.info("----catch---");
			log.info("-------");
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	// 오늘 날짜 경로를 문자열로 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
		// "-"를 file.separator로 변환하겠다.
		// 2024-09-19 면 2024폴더 안에 09월 폴더 안에 19일 폴더 안으로 됨
	}
	
}