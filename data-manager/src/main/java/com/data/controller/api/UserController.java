package com.data.controller.api;

import com.data.controller.BaseController;
import com.data.service.UserService;
import com.data.utils.RequestUtils;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.DataUser;
import com.manager.common.core.domain.entity.ResponeSms;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author marvin 2021/8/27
 * 客服端 玩家接口
 */
@RestController
@RequestMapping("/api/v1")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RequestUtils requestUtils;
    /**
     * 短信验证码发送
     * @return
     */
    @GetMapping("/user/sandCode")
    public AjaxResult sendCode(String phone){
        return AjaxResult.success(RequestUtils.sandTosms(phone));
    }

    /**
     * 注册
     * @return
     */
    @GetMapping("/user/register_tourist")
    public AjaxResult register(String requestId, String code,String phone,String password){
        if(StringUtils.isEmpty(requestId)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(phone)
                ||StringUtils.isEmpty(password)) {
            return AjaxResult.error("参数不能为空!");
        }
        if(userService.findByphone(phone)!=null){
            return AjaxResult.error("手机号码已注册!");
        };
        ResponeSms sms=RequestUtils.verifyTosms(requestId,code);
        if (sms.getData().isMatch()) {
            String pwd=DigestUtils.md5Hex(password);
            DataUser d=new DataUser();
            d.setPhone(phone);
            d.setPassword(pwd);
            int n=userService.insertToDataUser(d);
            if(n>0) {
                String str=requestUtils.getMD5Str(d);
                Map map = new HashMap();
                map.put("token",str);
                return AjaxResult.success(map);
            }
        }
        return AjaxResult.error("验证码验证失败!");
    }

    /**
     * 登录接口
     * @param dataUser
     * @return
     */
    @GetMapping("/user/login")
    public AjaxResult login(DataUser dataUser) {
        String pwd=DigestUtils.md5Hex(dataUser.getPassword());
        dataUser.setPassword(pwd);
        int n = userService.loadDataUserName(dataUser);
        if(n>0){
            String str=requestUtils.getMD5Str(dataUser);
            Map map = new HashMap();
            map.put("token",str);
            return AjaxResult.success(map);
        }
        return AjaxResult.error("登录失败");
    }

    /**
     * 权限校验
     * @param token
     * @return
     */
    @GetMapping("/user/verify")
    public AjaxResult verify(String token) {
        String ss= (String) redisTemplate.opsForValue().get(token);
        if(ss!=null && !ss.equals("")){
            return AjaxResult.success("权限验证成功!");
        }
        return AjaxResult.error("权限不足!");
    }
}
