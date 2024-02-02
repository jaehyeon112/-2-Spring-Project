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

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Service; 
import org.springframework.web.multipart.MultipartFile; 

import com.bongsamaru.file.mapper.FileMapper;
import com.bongsamaru.file.service.FilesVO;

@Service
public class FileService {
    
    @Autowired
    private FileMapper fileMapper;

    @Value("${file.upload.path}")
    private String uploadPath;

    public List<String> uploadFiles(MultipartFile[] uploadFiles, String code, String codeNo) throws IOException {
        List<String> imageList = new ArrayList<>();

        for(MultipartFile uploadFile : uploadFiles) {
            if (!isImageFile(uploadFile)) {
                System.err.println("this file is not image type");
                return null;
            }

            String uploadFileName = handleFileUpload(uploadFile, code, codeNo);
            imageList.add("/upload/" + uploadFileName); // 이미지 URL 형식으로 변환하여 추가
        }

        return imageList;
    }

    private boolean isImageFile(MultipartFile file) {
        return file.getContentType() != null && file.getContentType().startsWith("image");
    }

    private String handleFileUpload(MultipartFile uploadFile, String code, String codeNo) throws IOException {
        printFileInfo(uploadFile);
        String uploadFileName = prepareFilePath(uploadFile);
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


    private void saveFileMetadata(MultipartFile uploadFile, String filePath, String code, String codeNo) {
        String originalName = uploadFile.getOriginalFilename();
        long fileSize = uploadFile.getSize();
        String contentType = uploadFile.getContentType();

        FilesVO fileVO = new FilesVO();
        fileVO.setFilePath("/upload/" + filePath); // 웹 URL 형식으로 변환하여 저장
        fileVO.setFileName(originalName);
        fileVO.setFileSize((int) fileSize);
        fileVO.setExtension(contentType);
        fileVO.setCode(code);
        fileVO.setCodeNo(codeNo);
        fileMapper.insertFile(fileVO);
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
    
}

