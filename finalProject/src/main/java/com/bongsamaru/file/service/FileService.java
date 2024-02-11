package com.bongsamaru.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public List<String> uploadFiles(MultipartFile[] uploadFiles, String code, int codeNo, String user) throws IOException {
        List<String> imageList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles) {
            if (!isImageFile(uploadFile)) {
                System.err.println("this file is not image type");
                return null;
            }

            String uploadFileName = handleFileUpload(uploadFile, code, codeNo, user);
            imageList.add("/upload/" + uploadFileName); // 이미지 URL 형식으로 변환하여 추가
        }

        return imageList;
    }

    private boolean isImageFile(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image");
    }

    private String handleFileUpload(MultipartFile uploadFile, String code, int codeNo, String user) throws IOException {
        printFileInfo(uploadFile);
        String uploadFileName = prepareFilePath(uploadFile);
        saveFileMetadata(uploadFile, uploadFileName, code, codeNo, user);
        return uploadFileName;
    }

    private void printFileInfo(MultipartFile uploadFile) {
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();
        System.out.println("fileSize : " + fileSize);
        System.out.println("contentType : " + contentType);
    }

    private String prepareFilePath(MultipartFile uploadFile) {
        try {
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName != null ? originalName.substring(originalName.lastIndexOf("//") + 1) : "";
            System.out.println("fileName : " + fileName);

            String contentType = uploadFile.getContentType() != null ? uploadFile.getContentType().split("/")[0] : "";
            String folderPath = makeFolder(contentType); // 파일 유형에 따른 폴더 생성
            String uuid = UUID.randomUUID().toString();
            String savedFileName = uuid + "_" + fileName; // 실제 저장되는 파일 이름
            String uploadFileName = folderPath + "/" + savedFileName; // DB에 저장될 경로
            Path savePath = Paths.get(uploadPath + File.separator + folderPath, savedFileName); // 로컬에 저장될 전체 경로
            uploadFile.transferTo(savePath); // 파일 저장
            return uploadFileName; // DB에 저장될 경로 반환
        } catch (IOException e) {
            System.err.println("File upload failed: " + e.getMessage());
            return null;
        }
    }


    private void saveFileMetadata(MultipartFile uploadFile, String filePath, String code, int codeNo, String codeUser) {
        String originalName = uploadFile.getOriginalFilename();
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();
        
        
        FilesVO fileVO = fileMapper.getFileCheck(codeUser);
        System.out.println(code + "파일체크");
        System.out.println(codeUser + "이름");
        System.out.println(fileVO);
        
        if (fileVO != null) {
            // 파일사이즈가 똑같은게 이미 존재하는 경우 업데이트 수행
            fileVO.setFilePath("/upload/" + filePath);
            fileVO.setFileName(originalName);
            fileVO.setFileSize((int) fileSize);
            fileVO.setExtension(contentType);
            fileMapper.updateFile(fileVO);
        } else {
            // 파일이 존재하지 않는 경우 인서트 수행
            fileVO = new FilesVO();
            fileVO.setFilePath("/upload/" + filePath);
            fileVO.setFileName(originalName);
            fileVO.setFileSize((int) fileSize);
            fileVO.setExtension(contentType);
            fileVO.setCode(code);
            fileVO.setCodeNo(codeNo);
            fileVO.setCodeUser(codeUser);
            fileMapper.insertFile(fileVO);
        }
    }
        
        
    private String makeFolder(String contentType) {
        // 기본 경로에 연/월/일 폴더 생성
        String dateFolderPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String baseFolderPath = uploadPath + File.separator + dateFolderPath;
        String subFolderPath = "";

        // 파일 유형에 따라 서브 폴더 생성
        switch (contentType) {
            case "image":
                subFolderPath = "images";
                break;
            case "video":
                subFolderPath = "videos";
                break;
            case "audio":
                subFolderPath = "audios";
                break;
            case "application":
                subFolderPath = "documents";
                break;
            default:
                subFolderPath = "others";
                break;
        }

        // 전체 경로 조합
        String folderPath = baseFolderPath + File.separator + subFolderPath;
        File uploadFolder = new File(folderPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }
        return folderPath.replace(uploadPath + File.separator, ""); // DB에 저장될 경로 반환
    }
    private boolean isAllowedFileType(MultipartFile file) {
        String allowedFileTypes = "image,video,audio,application/pdf"; // 허용된 파일 유형
        return file.getContentType() != null && allowedFileTypes.contains(file.getContentType().split("/")[0]);
    }
    
    //파일 삭제
    public boolean deleteFile(String filePath) {
    	System.out.println("여기까지 오나..?");
        File file = new File("C:\\" + filePath);	//넘어오는 값이 이미 upload를 붙이고 넘어오기 때문에 경로는 그냥 이대로 저장해도 될까..?
        System.out.println("파일경로"+file);
        if (file.exists()) { // 파일이 존재하는지 확인
        	file.delete(); // 파일이 존재하면 삭제하고 결과를 반환
            return true;
        }
        return false; // 파일이 존재하지 않으면 false 반환
    }
    
}

