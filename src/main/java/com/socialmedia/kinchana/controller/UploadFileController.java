package com.socialmedia.kinchana.controller;

import com.google.gson.Gson;
import com.socialmedia.kinchana.exception.CustomException;
import com.socialmedia.kinchana.exception.CustomFileNotFoundException;
import com.socialmedia.kinchana.payload.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("")
@CrossOrigin(value = "*")
public class UploadFileController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private Gson gson = new Gson();
    //Path: chứa toàn bộ hàm hỗ trợ sẵn liên quan tới đường dẫn
    @Value("${path.root}")
    private String spath;

    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file) {
        logger.info("request:MultipartFile " + file);
        //Định nghĩa đường dẫn
        Path rootPath = Paths.get(spath);//nio library
        try {
            if (!Files.exists(rootPath)) {// nếu đường dẫn không tồn tại, tạo folder ứng với
                Files.createDirectory(rootPath);
            }
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(), rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new CustomException("Error uploadFile " + e.getLocalizedMessage());
        }
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(true);
        response.setMessage("uploadFile");
        logger.info("response:" + gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/downloadfile/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path rootPath = Paths.get(spath);
            Path filePath = rootPath.resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                throw new CustomFileNotFoundException(200, "File not found");
            }
        } catch (MalformedURLException e) {
            throw new CustomFileNotFoundException(200, "File not found");
        } catch (IOException e) {
            throw new CustomFileNotFoundException(200, "Error reading file");
        }
    }
}
