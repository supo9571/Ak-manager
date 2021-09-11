package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.controller.BaseController;
import com.data.service.MonthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/11
 * 月卡 接口
 */
@RestController
@RequestMapping("/api/v1")
public class MonthCardController extends BaseController {

    @Autowired
    private MonthCardService monthCardService;
    /**
     * 月卡接口
     */
    @PostMapping("/pay/get_month_mark")
    public JSONObject month(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        return monthCardService.getMonthConfig(channelId);
    }

    /**
     * 充值 配置接口
     */
    @PostMapping("/pay/get_book_mark")
    public JSONObject book(String data){
        String channelId = getHeader("Client-ChannelId");//渠道id
        return monthCardService.getBookConfig(data,channelId);
    }
}
