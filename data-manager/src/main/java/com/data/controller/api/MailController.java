package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.controller.BaseController;
import com.data.service.MailService;
import com.manager.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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
    public JSONObject tips() {
        String channelId = getHeader("Client-ChannelId");
        String uid = getHeader("uid");
        JSONObject result = new JSONObject();
        List list = mailService.getTips(channelId, uid);
        Map map = new HashMap<>();
        map.put("tips", list);
        result.put("data", map);
        result.put("code", 200);
        return result;
    }

    /**
     * 邮件列表
     */
    @PostMapping("/server_api/get_mail_list")
    public JSONObject getMailList() {
        String channelId = getHeader("Client-ChannelId");
        String uid = getHeader("uid");
        JSONObject result = new JSONObject();
        List list = mailService.getMailList(channelId, uid);
        result.put("result", list);
        result.put("code", 200);
        result.put("msg", "OK");
        return result;
    }

    /**
     * 删除 邮件
     */
    @PostMapping("/server_api/del_mail")
    public JSONObject delMail(@RequestBody JSONObject param) {
        Integer mid = param.getInteger("mid");
        JSONObject result = new JSONObject();
        mailService.delMail(mid);
        result.put("del_id", mid);
        result.put("code", 200);
        result.put("msg", "邮件删除成功!");
        return result;
    }

    /**
     * 邮件已读
     */
    @PostMapping("/server_api/req_read_mail")
    public JSONObject readMail(@RequestBody JSONObject param) {
        String ids = param.getString("ids");
        JSONObject result = new JSONObject();
        mailService.readMail(ids);
        result.put("code", 200);
        result.put("msg", "OK");
        result.put("type", ids);
        result.put("read_id", ids);
        return result;
    }

    /**
     * 领取邮件附件
     */
    @PostMapping("/server_api/receive_mail")
    public JSONObject receiveMail(@RequestBody JSONObject param) {
        String id = param.getString("ids");
        return mailService.receiveMail(id);
    }

    /**
     * 轮播图配置
     */
    @PostMapping("/app/advert")
    public JSONObject advert() {
        return null;
    }
}
