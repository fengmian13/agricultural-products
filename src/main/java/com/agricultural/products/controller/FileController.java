package com.agricultural.products.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 *
 * 文件信息相关接口控制器
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @Value("${files.upload.path}")
    private String fileUploadPath;

    /**
     * 文件上传接口
     * @param file 前端传递过来的文件
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;
        if (StringUtils.isBlank(fileUploadPath)){
            fileUploadPath = System.getProperty("user.dir")+ java.io.File.separator + "files" + java.io.File.separator;
        }
        java.io.File uploadFile = new java.io.File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        java.io.File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 上传文件到磁盘
        file.transferTo(uploadFile);
        // 数据库若不存在重复文件，则不删除刚才上传的文件
        String url = "http://localhost:8085/file/" + fileUUID;
        return url;
    }

    /**
     * 文件下载接口   http://localhost:8085/file/{fileUUID}
     * @param fileUUID
     * @param response
     * @throws IOException
     */
    @GetMapping("/{fileUUID:.+}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(fileUploadPath)){
            fileUploadPath = System.getProperty("user.dir")+ java.io.File.separator + "files" + java.io.File.separator;
        }
        // 根据文件的唯一标识码获取文件
        java.io.File uploadFile = new java.io.File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        try {
            os.write(FileUtil.readBytes(uploadFile));
        } catch (Exception e) {
            System.err.println("文件下载失败，文件不存在");
        }
        os.flush();
        os.close();
    }

}

