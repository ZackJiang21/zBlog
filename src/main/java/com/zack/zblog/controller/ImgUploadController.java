package com.zack.zblog.controller;

import com.zack.zblog.aop.Privilege;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZackJiang on 2018/6/3.
 */
@RestController
@RequestMapping("/upload/image")
public class ImgUploadController {

    @Value("${img.location}")
    private String UPLOADED_FOLDER;

    @PostMapping
    @Privilege
    public Map<String,Object> uploadImg(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + System.currentTimeMillis() + "." + getExtendName(fileName));
            Files.write(path, bytes);
            resultMap.put("success", 1);
            resultMap.put("message", "上传成功！");
            resultMap.put("url","/" + fileName);
        } catch (Exception e) {
            resultMap.put("success", 0);
            resultMap.put("message", "上传失败！");
        }
        return resultMap;
    }

    private String getExtendName(String fileName) {
        String[] temp = fileName.split("\\.");
        return temp[temp.length - 1];
    }
}
