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
import com.manager.common.core.domain.entity.ResponeSms;
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
     *
     * @return
     */
    @GetMapping("/onebyone/sendsms")
    public Map sendCode(String phone_number) {
        return RequestUtils.sandTosms(phone_number);
    }


    /**
     * 注册
     *
     * @return
     */
    @PostMapping("/user/verify_register_invitation_code")
    public JSONObject register(@RequestBody JSONObject param) {
        JSONObject result = new JSONObject();
        String matchineToken = param.getString("matchine_token");
        String phoneNumber = param.getString("phone_number");
        String code = param.getString("code");
        Integer checkWay = param.getInteger("check_way");
        String password = param.getString("password");
        String requestId = param.getString("requestId");
        String pkgChannel = getHeader("Client-ChannelId") == null ? "" : getHeader("Client-ChannelId");

        if (checkWay == 6) { //登录
            DataUser dataUser = userService.findByPassword(phoneNumber, DigestUtils.md5Hex(password));
            if (dataUser == null) {
                result.put("code", -1);
                result.put("desc", "用户名密码错误");
            } else {
                result.put("code", 0);
                result.put("account_id", dataUser.getAccountId());
                result.put("pkg_channel", pkgChannel);
                result.put("key_token", setToken(dataUser.getAccountId()));
            }
        } else {
            ResponeSms sms = RequestUtils.verifyTosms(requestId, code);
            if (!globalConfig.isVerSwitch() || sms.getData().isMatch()) {
                if (checkWay == 7) {//修改密码
                    DataUser dataUser = userService.findByPhone(phoneNumber);
                    if (dataUser == null) {
                        result.put("code", -1);
                        result.put("desc", "手机号未注册");
                    } else {
                        userService.updatePassword(phoneNumber, DigestUtils.md5Hex(password));
                        result.put("code", 0);
                        result.put("account_id", dataUser.getAccountId());
                        result.put("pkg_channel", pkgChannel);
                        result.put("key_token", setToken(dataUser.getAccountId()));
                    }
                } else if (checkWay == 8) {//密码注册
                    String ip = getHeader("HTTP-CLIENT-IP");
                    DataUser dataUser = new DataUser(phoneNumber, DigestUtils.md5Hex(password), ip, matchineToken, pkgChannel);
                    int n = userService.insertToDataUser(dataUser);
                    if (n > 0) {
                        result.put("code", 0);
                        result.put("account_id", dataUser.getAccountId());
                        result.put("pkg_channel", pkgChannel);
                        result.put("key_token", setToken(dataUser.getAccountId()));
                    } else {
                        result.put("code", -1);
                        result.put("desc", "服务器出错");
                    }
                } else if (checkWay == 9) {//游客绑定手机
                    String accountId = getHeader("account-id");
                    DataUser dataUser = new DataUser(Long.valueOf(accountId), phoneNumber, DigestUtils.md5Hex(password));
                    int n = userService.updateDataUser(dataUser);
                    if (n > 0) {
                        result.put("code", 0);
                        result.put("account_id", dataUser.getAccountId());
                        result.put("pkg_channel", pkgChannel);
                        result.put("key_token", setToken(dataUser.getAccountId()));
                    } else {
                        result.put("code", -1);
                        result.put("desc", "服务器出错");
                    }
                }
            } else {
                result.put("code", -1);
                result.put("desc", "验证码错误");
            }
        }
        return result;
    }

    /**
     * 登录接口
     *
     * @param dataUser
     * @return
     */
    @PostMapping("/user/login")
    public AjaxResult login(DataUser dataUser) {
        String pwd = DigestUtils.md5Hex(dataUser.getPassword());
        dataUser.setPassword(pwd);
        int n = userService.loadDataUserName(dataUser);
        if (n > 0) {
            String str = requestUtils.getMD5Str(dataUser);
            Map map = new HashMap();
            map.put("token", str);
            return AjaxResult.success(map);
        }
        return AjaxResult.error("登录失败");
    }

    /**
     * 权限校验
     *
     * @return
     */
    @PostMapping("/user/check_token")
    public JSONObject verify(@RequestBody JSONObject param) {
        String key_token = param.getString("key_token");
        JSONObject relust = new JSONObject();
        Integer accountId = redisCache.getCacheObject(key_token);
        if (accountId != null && accountId > 0) {
            relust.put("account_id", accountId);
            relust.put("code", 0);
            relust.put("key_token", key_token);
        } else {
            relust.put("desc", "token不合法");
            relust.put("code", -1);
        }
        return relust;
    }

    /**
     * 游客 登录
     * @param dataUser
     * @return
     */
    private static String registerLock = "registerLock";
    @PostMapping("/user/register_tourist")
    public JSONObject tourist(@RequestBody DataUser dataUser) {
        JSONObject relust = new JSONObject();
        if (globalConfig.isVerSwitch() && Verification.checkHeader()) {
            relust.put("code", -1);
            relust.put("desc", "签名不合法");
            return relust;
        }
        String token = IdUtils.fastSimpleUUID();
        if (StringUtils.isBlank(dataUser.getPackage_channel()) || StringUtils.isBlank(dataUser.getSeed_token())) {
            relust.put("code", -1);
            relust.put("desc", "参数错误");
        } else {
            relust.put("pkg_channel", dataUser.getPackage_channel());
            relust.put("key_token", token);
            synchronized (registerLock) { //防止 重复提交
                //查询游客 之前是否登录过
                DataUser user = userService.findUserBySeedToken(dataUser.getSeed_token());
                if (user != null) {
                    redisCache.setCacheObject(token, user.getAccountId(), 15, TimeUnit.DAYS);
                    relust.put("code", 0);
                    relust.put("account_id", user.getAccountId());
                } else {
                    int n = userService.insertToDataUser(dataUser);
                    if (n > 0) {
                        redisCache.setCacheObject(token, dataUser.getAccountId(), 15, TimeUnit.DAYS);
                        relust.put("code", 0);
                        relust.put("account_id", dataUser.getAccountId());
                    } else {
                        relust.put("code", -1);
                        relust.put("desc", "服务器出错");
                    }
                }
            }
        }
        return relust;
    }

    /**
     * 查询是否封号
     * @return
     */
    @PostMapping("/user/forbidden")
    public JSONObject forbidden(@RequestBody JSONObject param) {
        String uid = param.getString("uid");
        JSONObject relust = new JSONObject();
        Map map = userService.selectLock(uid);
        if(map!=null){
            relust.put("is_forbidden",true);
            relust.put("reason",map.get("lockMark"));
            relust.put("end_time",map.get("endTime"));
        }else {
            relust.put("is_forbidden",false);
        }
        return relust;
    }

    /**
     * 获取用户信息
     * @return
     */
    @PostMapping("/user/become_agent")
    public JSONObject becomeAgent(@RequestBody JSONObject param) {
        String accountId = param.getString("account_id");
        Map map = userService.becomeAgent(accountId);
        map.put("code",0);
        JSONObject relust = new JSONObject(map);
        return relust;
    }

    private String setToken(Long accountId) {
        String token = IdUtils.fastSimpleUUID();
        redisCache.setCacheObject(token, accountId, 15, TimeUnit.DAYS);
        return token;
    }
}
