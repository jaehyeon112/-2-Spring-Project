package com.bongsamaru.file.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.file.mapper.FileMapper;
import com.bongsamaru.file.service.FilesVO;

@Controller
public class FileController {
	@Autowired
	FileMapper fileMapper;
	
	
	@Value("${file.upload.path}")
	private String uploadPath;
	
	@GetMapping("upload")
	public void getUploadPath() {
	}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles, String code, String codeNo) {
	    System.out.println(code);
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	        
	        // 파일 크기
	        long fileSizeTest = uploadFile.getSize();

	        // 파일 컨텐츠 타입
	        String contentType = uploadFile.getContentType();
	        
	        // 파일 정보 출력
	        System.out.println("fileSize : " + fileSizeTest);
	        System.out.println("contentType : " + contentType);
	        
	        String originalName = uploadFile.getOriginalFilename();
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        FilesVO fileVO = new FilesVO();
	        fileVO.setFilePath(saveName);
	        fileVO.setFileName(originalName);
	        int size = (int)fileSizeTest;
	        fileVO.setFileSize(size);
	        fileVO.setExtension(contentType);
	        fileVO.setCode(code);
	        fileVO.setCodeNo(codeNo);
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        fileMapper.insertFile(fileVO);
	        
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    
	    
	    
	    return imageList;
	}
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
