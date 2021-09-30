package com.data.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author marvin 2021/8/28
 * 验证签名
 */
public class Verification {


    private static String TIME = "time";
    private static String UID = "uid";
    private static String SIGN = "sign";

    //过期时间 间隔 秒
    private static long EXPIRETIME = 1800;
    //密钥
    private static String key = "ymq0ytgdv37ov95r0py4dbfik6xj1fhl";

    /**
     * 请求 签名验证 true 不通过  false 通过
     *
     * @return
     */
    public static boolean checkHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String time = request.getHeader(TIME);
        String uid = request.getHeader(UID);
        String sign = request.getHeader(SIGN);
        if (time == null || uid == null || StringUtils.isBlank(sign)) {
            return true;
        }
        Long nowTimems = new Date().getTime() / 1000;
        if (nowTimems - Long.valueOf(time) > EXPIRETIME) {
            return true;
        }
        String md5Str = DigestUtils.md5DigestAsHex((uid + "" + time + key).getBytes(StandardCharsets.UTF_8));
        if (!sign.equals(md5Str)) {
            return true;
        }
        return false;
    }

}
