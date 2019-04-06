package com.lanxin.pandora.controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import com.lanxin.pandora.service.ResourceService;
import com.lanxin.pandora.tools.DateTools;
import com.lanxin.pandora.tools.JsonResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Value("${pandora.upload.path}")
    private String uploadPath;

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void uploadFile(HttpServletResponse response, @RequestParam("file") MultipartFile file, @RequestParam("bid") String bid) {
        boolean flag = false;
        String extName = "";
        String savePath = "";
        if (! file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            extName = fileName.substring(fileName.lastIndexOf("."));
            //用年月日作为路径
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd/");
            savePath = dateFormat.format(new Date()) + DateTools.uniqid() + "." + extName;
            File dest = new File(uploadPath + savePath);
            if (dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                flag = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (flag) {
            resourceService.insert(bid, extName, savePath, file.getSize());
            new JsonResponse(response).write(savePath);
        } else {
            new JsonResponse(response).write(JsonResponse.RES_FAIL, null, "上传失败");
        }
    }
}