package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.controller.BaseController;
import com.data.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/25
 * 消息管理 接口
 */
@RestController
@RequestMapping("/api/v1")
public class MailController extends BaseController {

    @Autowired
    private MailService mailService;

    /**
     * 公告列表接口
     */
    @PostMapping("/app/tips")
    public JSONObject tips(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        JSONObject result = new JSONObject();
        List list = mailService.getTips(channelId);
        Map map = new HashMap<>();
        map.put("tips",list);
        result.put("data",map);
        result.put("code",200);
        return result;
    }
}
