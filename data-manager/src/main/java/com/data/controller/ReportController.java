package com.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/10
 * 游戏服 通讯
 */
@RestController
@Slf4j
@RequestMapping("/data")
public class ReportController {

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 操作用户 金币
     * data={“cmd”:”addcoins”,”value”:1000001,”uid”:105519}
     */
    @PostMapping("/coins/edit")
    public AjaxResult editCoins(String cmd,Integer value,Integer uid,Integer reason){
        JSONObject param = new JSONObject();
        param.put("cmd",cmd);//"addcoins"=加金币 “reducecoins”=减金币 “forbidden”=踢人
        param.put("reason",reason);//100073=银行卡充值 100070=vip充值
        param.put("type",1);//1=加金币,2=加流水
        param.put("value",value);
        param.put("uid",uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                "data="+param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson!=null && resultJson.getInteger("code")==0){
            //返回操作后金额
            return AjaxResult.success("操作成功",resultJson.getInteger("curr"));
        }
        log.error("操作用户金币失败;参数:{};返回值:{}",param,result);
        return AjaxResult.error();
    }


    /**
     * 踢人
     * data={“cmd”:”forbidden”,”reason”:“游戏里里骂人”,”uid”:105519}
     */
    @PostMapping("/user/control")
    public AjaxResult control(String cmd,Integer uid,String reason){
        JSONObject param = new JSONObject();
        param.put("cmd",cmd);//"addcoins"=加金币 “reducecoins”=减金币 “forbidden”=踢人
        param.put("reason",reason);
        param.put("uid",uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                "data="+param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson!=null && resultJson.getInteger("code")==0){
            //返回操作后金额
            return AjaxResult.success("操作成功");
        }
        log.error("踢人失败;参数:{};返回值:{}",param,result);
        return AjaxResult.error();
    }

    /**
     * 给用户 发邮件
     * data={"cmd":"notifynewmail","mail_type":2,"range":“105519,105520"}
     */
    @PostMapping("/mail/send")
    public AjaxResult sendEmail(String cmd,Integer mailType,String range){
        JSONObject param = new JSONObject();
        param.put("cmd",cmd);
        param.put("mail_type",mailType);//1=全服邮件 2=指定玩家邮件
        param.put("range",range);
        //发邮件
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getMail(),
                "data="+param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson!=null && resultJson.getInteger("code")==0){
            return AjaxResult.success();
        }
        log.error("发邮件失败;参数:{};返回值:{}",param,result);
        return AjaxResult.error();
    }

}
