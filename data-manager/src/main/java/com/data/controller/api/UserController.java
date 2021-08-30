package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.controller.BaseController;
import com.data.service.UserService;
import com.data.utils.Verification;
import com.manager.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/27
 * 客服端 玩家接口
 */
@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController {

    @Autowired
    private GlobalConfig globalConfig;
    @Autowired
    private UserService userService;

    @PostMapping("/hotupdate")
    public AjaxResult hotUpdate() {
//        String device = getHeader("Client-Device");//设备类型 windows,ios，android
//        String machineid = getHeader("Client-MachineCode");//设备机器码（设备唯一id）
//        String version = getHeader("Client-VersionRes");//当前包内热更资源版本号 例：1.0.1
//        String channel = getHeader("Client-PackageChannel");//渠道

        String channelId = getHeader("Client-ChannelId");//渠道id
        String versionId = getHeader("Client-VersionId");//版本号
        String platform = getHeader("Client-platform");//平台 windows,ios，android
        String ip = getIp();

        List<Map> list = userService.selectPackage(ip,channelId,versionId,platform);
        if(!list.isEmpty()){
            //添加 更新信息
        }


        return AjaxResult.success();
    }


    /**
     * 注册接口
     */
    @PostMapping("/register")
    public AjaxResult register() {
        if(Verification.checkHeader()) return AjaxResult.error("参数不合法");
        return AjaxResult.success();
    }


    /**
     * 短信验证码
     * @return
     */
    @PostMapping("code")
    public AjaxResult sendCode(String phone,String password){
        //判断手机号 是否存在
        Integer i = userService.findByphone(phone);
        if(i>0){
            return AjaxResult.error("手机号已存在！");
        }
        return AjaxResult.error();
    }
}
