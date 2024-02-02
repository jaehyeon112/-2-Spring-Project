package com.bongsamaru.file.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles, String code, String codeNo) {
        try {
        	System.out.println("업로드 컨트롤러상의~?");
        	System.out.println(uploadFiles);
        	System.out.println(code);
        	System.out.println(codeNo);
        	System.out.println("업로드 컨트롤러상의~?");
            return fileService.uploadFiles(uploadFiles, code, codeNo);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 혹은 적절한 에러 메시지와 함께 응답을 반환
        }
        
    }
}
