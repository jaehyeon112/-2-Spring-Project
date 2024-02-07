package com.bongsamaru.file.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bongsamaru.file.service.FileService;

@Controller
public class FileController {
	
    @Autowired
    private FileService fileService;
    
    
    @PostMapping("/uploadsAjax")
    @ResponseBody
    public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles, String code, int codeNo, String user) {
        try {
            return fileService.uploadFiles(uploadFiles, code, codeNo, user);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 혹은 적절한 에러 메시지와 함께 응답을 반환
        }
        
    }
    
    @GetMapping("/files/delete/{fileName}")
    public String deleteFile(@PathVariable String fileName) {
        String uploadDir = "/path/to/uploaded/files"; // 실제 파일이 저장된 디렉터리 경로
        String fullPath = uploadDir + File.separator + fileName;

        boolean isDeleted = fileService.deleteFile(fullPath);

        if (isDeleted) {
            return "파일이 성공적으로 삭제되었습니다.";
        } else {
            return "파일 삭제에 실패했습니다. 파일이 존재하지 않거나 삭제할 수 없습니다.";
        }
    }
}
