package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.config.redis.RedisCache;
import com.data.controller.BaseController;
import com.data.service.UserService;
import com.data.utils.RequestUtils;
import com.data.utils.Verification;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.DataUser;
import com.manager.common.utils.uuid.IdUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private RedisCache redisCache;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 短信验证码发送
     * @return
     */
    @GetMapping("/onebyone/sendsms")
    public Map sendCode(String phone_number){
        return RequestUtils.sandTosms(phone_number);
    }



    /**
     * 注册
     * @return
     */
    @PostMapping("/user/register")
    public AjaxResult register(String requestId, String code,String phone,String password){
        if(StringUtils.isEmpty(requestId)
                ||StringUtils.isEmpty(code)
                ||StringUtils.isEmpty(phone)
                ||StringUtils.isEmpty(password)) {
            return AjaxResult.error("参数不能为空!");
        }
//        if(userService.findByphone(phone)!=null){
//            return AjaxResult.error("手机号码已注册!");
//        };
        //ResponeSms sms=RequestUtils.verifyTosms(requestId,code);
        //if (sms.getData().isMatch()) {
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
       // }
        return AjaxResult.error("验证码验证失败!");
    }

    /**
     * 登录接口
     * @param dataUser
     * @return
     */
    @PostMapping("/user/login")
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
     * @param key_token
     * @return
     */
    @PostMapping("/user/check_token")
    public JSONObject verify(String key_token) {
        JSONObject relust = new JSONObject();
        Integer accountId= redisCache.getCacheObject(key_token);
        if(accountId!=null && accountId >0){
            relust.put("account_id",accountId);
            relust.put("code",0);
        }else {
            relust.put("desc","token不合法");
            relust.put("code",-1);
        }
        return relust;
    }

    /**
     * 游客 登录
     * @param dataUser
     * @return
     */
    @PostMapping("/user/register_tourist")
    public JSONObject tourist(@RequestBody DataUser dataUser){
        JSONObject relust = new JSONObject();
        if(globalConfig.isVerSwitch() && Verification.checkHeader()){
            relust.put("code",-1);
            relust.put("desc","签名不合法");
            return relust;
        }
        String token = IdUtils.fastSimpleUUID();
        if(StringUtils.isBlank(dataUser.getPackage_channel()) || StringUtils.isBlank(dataUser.getPackage_channel())){
            relust.put("code",-1);
            relust.put("desc","参数错误");
        }
        relust.put("pkg_channel",dataUser.getPackage_channel());
        relust.put("key_token",token);
        //查询游客 之前是否登录过
        DataUser user = userService.findUserBySeedToken(dataUser.getSeed_token());
        if(user!=null){
            redisCache.setCacheObject(token,user.getAccountId(),10, TimeUnit.MINUTES);
            relust.put("code",0);
            relust.put("account_id",user.getAccountId());

        }else{
            String pwd=DigestUtils.md5Hex("123456");
            String phone=requestUtils.getRomodphone();
            //DataUser d=new DataUser();
            dataUser.setPhone(phone);
            dataUser.setPassword(pwd);
            int n=userService.insertToDataUser(dataUser);
            if(n>0){
//                String str=requestUtils.getMD5Str(dataUser);
                redisCache.setCacheObject(token,dataUser.getAccountId(),10, TimeUnit.MINUTES);
                relust.put("code",0);
                relust.put("account_id",dataUser.getAccountId());
            }else{
                relust.put("code",-1);
                relust.put("desc","服务器出错");
            }
        }
        return relust;
    }
}
