package com.bongsamaru.file.service;

import java.io.File; // File 클래스 사용
import java.io.IOException; // IOException 사용
import java.nio.file.Path; // Path 클래스 사용
import java.nio.file.Paths; // Paths 클래스 사용
import java.time.LocalDate; // LocalDate 클래스 사용
import java.time.format.DateTimeFormatter; // DateTimeFormatter 클래스 사용
import java.util.ArrayList; // ArrayList 클래스 사용
import java.util.List; // List 인터페이스 사용
import java.util.UUID; // UUID 클래스 사용

import org.springframework.beans.factory.annotation.Autowired; // @Autowired 어노테이션 사용
import org.springframework.beans.factory.annotation.Value; // @Value 어노테이션 사용
import org.springframework.stereotype.Service; // @Service 어노테이션 사용
import org.springframework.web.multipart.MultipartFile; // MultipartFile 인터페이스 사용

import com.bongsamaru.file.mapper.FileMapper; // FileMapper 사용


@Service
public class FileService {
	
    @Autowired
    private FileMapper fileMapper;

    @Value("${file.upload.path}")
    private String uploadPath;

    public List<String> uploadFiles(MultipartFile[] uploadFiles, String code, String codeNo) throws IOException {
        List<String> imageList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles) {
            // 이미지 파일 확인
            if (!isImageFile(uploadFile)) {
                System.err.println("this file is not image type");
                return null;
            }

            // 파일 정보 출력, 파일 저장, DB에 메타데이터 저장
            String uploadFileName = handleFileUpload(uploadFile, code, codeNo);
            imageList.add(setImagePath(uploadFileName));
        }

        return imageList;
    }

    private boolean isImageFile(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image");
    }

    private String handleFileUpload(MultipartFile uploadFile, String code, String codeNo) throws IOException {
        printFileInfo(uploadFile);
        String uploadFileName = prepareFilePath(uploadFile);
        Path savePath = Paths.get(uploadFileName);
        uploadFile.transferTo(savePath);
        saveFileMetadata(uploadFile, uploadFileName, code, codeNo);
        return uploadFileName;
    }

    private void printFileInfo(MultipartFile uploadFile) {
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();
        System.out.println("fileSize : " + fileSize);
        System.out.println("contentType : " + contentType);
    }

    private String prepareFilePath(MultipartFile uploadFile) {
        String originalName = uploadFile.getOriginalFilename();
        String fileName = originalName != null ? originalName.substring(originalName.lastIndexOf("//") + 1) : "";
        System.out.println("fileName : " + fileName);

        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String uploadFileName = folderPath + "/" + uuid + "_" + fileName;
        return uploadPath + File.separator + uploadFileName;
    }

    private void saveFileMetadata(MultipartFile uploadFile, String filePath, String code, String codeNo) {
        String originalName = uploadFile.getOriginalFilename();
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();

        FilesVO fileVO = new FilesVO();
        fileVO.setFilePath(setImagePath(filePath));
        fileVO.setFileName(originalName);
        fileVO.setFileSize((int) fileSize);
        fileVO.setExtension(contentType);
        fileVO.setCode(code);
        fileVO.setCodeNo(codeNo);
        fileMapper.insertFile(fileVO);
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("\\", "/");
        File uploadPathFoler = new File(uploadPath, folderPath);
        if (!uploadPathFoler.exists()) {
            uploadPathFoler.mkdirs();
        }
        return folderPath;
    }

    private String setImagePath(String uploadFileName) {
        return uploadFileName.replace(File.separator, "/");
    }
}