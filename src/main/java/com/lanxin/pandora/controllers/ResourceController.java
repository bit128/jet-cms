package com.lanxin.pandora.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 上传文件
     * @param response
     * @param file
     * @param bid
     */
    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public void uploadFile(HttpServletResponse response, @RequestParam("file") MultipartFile file, @RequestParam(value = "bid", required = false) String bid) {
        boolean flag = false;
        JsonResponse jr = new JsonResponse(response);
        String extName = "";
        String savePath = "";
        if (! file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            extName = fileName.substring(fileName.lastIndexOf(".")+1);
            //用年月日作为路径
            SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd/");
            savePath = dateFormat.format(new Date()) + DateTools.uniqid() + "." + extName;
            File dest = new File(uploadPath + savePath);
            if (!dest.getParentFile().exists()) {
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
            if (bid == null) {
                bid = "";
            }
            resourceService.insert(bid, extName, savePath, file.getSize());
            jr.write(savePath);
        } else {
            jr.write(JsonResponse.RES_FAIL, null, "上传失败");
        }
    }

    /**
     * 渲染图片
     * @param request
     * @param response
     */
    @RequestMapping("/image/**")
    public void image(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        if (uri.length() > 16) {
            String path = uri.substring(16);
            String extName = path.substring(path.lastIndexOf(".") + 1);
            File file = new File(uploadPath + path);
            if (file.exists()) {
                if (extName.equals("jpg") || extName.equals("jpeg")) {
                    response.setContentType("image/jpeg");
                } else if (extName.equals("png")) {
                    response.setContentType("image/png");
                } else {
                    response.setContentType("application/octet-stream");
                }
                byte[] buffer = new byte[1024];
                FileInputStream fileInputStream;
                BufferedInputStream bufferedInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(fileInputStream);
                    OutputStream outputStream = response.getOutputStream();
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        outputStream.write(buffer);
                        i = bufferedInputStream.read(buffer);
                    }
                    fileInputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据bid获取资源文件列表
     * @param resource
     * @param bid
     * @param offset
     * @param limit
     */
    @RequestMapping(value = "/getList.do", method = RequestMethod.POST)
    public void getList(HttpServletResponse response, String bid, int offset, int limit) {
        new JsonResponse(response).write(resourceService.getList(offset, limit, bid));
    }
}