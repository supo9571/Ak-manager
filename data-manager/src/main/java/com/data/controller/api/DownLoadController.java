package com.data.controller.api;

import com.data.config.GlobalConfig;
import com.manager.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author marvin 2021/9/1
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
    public void downLoad(HttpServletResponse response, @PathVariable(value = "url",required = false) String url) {
        try {
            String realFileName = System.currentTimeMillis() + url.substring(url.indexOf("_") + 1);
            String filePath = globalConfig.getProfile() + "/" + url;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}
