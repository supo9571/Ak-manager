package com.data.controller.api;

import com.data.controller.BaseController;
import com.data.service.UserService;
import com.data.utils.Verification;
import com.manager.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/27
 * 客服端 玩家接口
 */
@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

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
