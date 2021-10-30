package com.data.controller.api;

import com.data.config.GlobalConfig;
import com.manager.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author marvin 2021/9/1
 * 文件下载 接口
 */
@RestController
@Slf4j
public class DownLoadController {
    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 整包更新 文件下载
     */
    @GetMapping("/profile/{url}")
    public void downLoad(HttpServletResponse response, @PathVariable(value = "url", required = false) String url) {
        try {
            String filePath = globalConfig.getProfile() + "/" + url;
            Long length = new File(filePath).length();
            if(length<=0){
                String realPath = filePath.split("_")[0]+".apk";
                String channel = filePath.split("_")[1].split(".a")[0];
                String jarParh = globalConfig.getProfile() + "/walle-cli-all.jar";
                Process process = Runtime.getRuntime().exec("java -jar "+jarParh+" put -c "+channel+" "+realPath);
            }
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Length", new File(filePath).length() + "");
            FileUtils.setAttachmentResponseHeader(response, url);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 热更新 文件下载
     */
    @GetMapping("/profile/hotpackage/**")
    public void hotDownLoad(HttpServletResponse response, HttpServletRequest request) {
        try {
            String url = request.getRequestURI().replaceAll("/profile/hotpackage/", "");
            String filePath = globalConfig.getProfile() + "/hotpackage/" + url;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Length", new File(filePath).length() + "");
            FileUtils.setAttachmentResponseHeader(response, url);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}
