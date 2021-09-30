package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.controller.BaseController;
import com.data.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author marvin 2021/9/10
 * 客服端 充值接口
 */
@RestController
@RequestMapping("/api/v1")
public class PayController extends BaseController {

    @Autowired
    private PayService payService;

    /**
     * 银行卡充值
     * pay_id	是	string	支付ID
     * way	是	int	充值方式
     * uid	是	string	用户id
     * name	是	string	用户姓名
     * money	是	int	充值金额（元）
     * channel	是	string	包渠道
     * bank_account	是	string	收款银行卡号
     * bank_user_name	是	string	收款持卡人姓名
     */
    @PostMapping(value = "/onebyone/submitUnionCard", produces = "application/json;charset=UTF-8")
    public JSONObject bankReg(@RequestBody JSONObject param) {
        String uid = param.getString("uid");
        String name = param.getString("name");
        Integer money = param.getInteger("money");
        String channel = param.getString("channel");
        JSONObject result = new JSONObject();
        Integer i = payService.saveBankReg(uid, name, money, channel);
        if (i > 0) {
            Map map = new HashMap();
            map.put("code", 200);
            map.put("msg", "提交成功");
            result.put("data", map);
        } else {
            Map map = new HashMap();
            map.put("code", 500);
            map.put("msg", "提交失败");
            result.put("data", map);
        }
        return result;
    }
}
