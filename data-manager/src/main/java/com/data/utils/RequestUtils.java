package com.data.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.domain.entity.DataUser;
import com.manager.common.core.domain.entity.ResponeSms;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author LV
 * @date 18:06 2021/9/8
 * @return
 **/
@Component
public class RequestUtils {
    /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRETID = "720006eac787e84733be0568c6e64a1e";
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRETKEY = "6101056b6b3ea783b805d03d4e01a9f4";
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESSID = "898b960212514c0984cf27f97567e74c";
    /**
     * 本机认证服务身份证实人认证在线检测接口地址
     */
    private static final String URI_SEND_SMS = "https://sms.dun.163.com/v2/sendsms";
    private static final String URI_VERIFY_OTP = "https://sms.dun.163.com/v2/verifysms";

    @Autowired
    private RedisTemplate redisTemplate;

    public static Map sandTosms(String phone) {
        Map<String, String> params = createToParams(phone);
        return postForEntity(URI_SEND_SMS, params, Map.class);
    }

    public static ResponeSms verifyTosms(String requestId, String code) {
        Map<String, String> param = verifyToParams(requestId, code);
        ResponeSms sms = postForEntity(URI_VERIFY_OTP, param, ResponeSms.class);
        return sms;
    }

    public static Map<String, String> createToParams(String phone) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String nonce = "duanxinfasong" + String.valueOf(System.currentTimeMillis());
        params.put("nonce", nonce);
        params.put("templateId", "14414");
        params.put("mobile", phone);
        params.put("paramType", "json");
        Map<String, String> variables = Collections.emptyMap();
        params.put("codeName", "code");
        params.put("codeLen", "6");
        params.put("codeValidSec", "600");
        params.put("params", serializeVariables(variables));
        String sign = genSignature(SECRETKEY, params);
        params.put("signature", sign);
        return params;
    }

    public static Map<String, String> verifyToParams(String requestId, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("secretId", SECRETID);
        params.put("businessId", BUSINESSID);
        params.put("version", "v2");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        String nonce = "duanxinfasong" + String.valueOf(System.currentTimeMillis());
        params.put("nonce", nonce);

        params.put("requestId", requestId);
        params.put("code", code);


        String sign = genSignature(SECRETKEY, params);
        params.put("signature", sign);
        return params;
    }

    public static <R> R postForEntity(String uri, Map<String, String> params, Class<R> responseType) {
        try {
            String strResponse = Request.Post(uri)
                    .bodyForm(convertToFormData(params), StandardCharsets.UTF_8)
                    .execute()
                    .returnContent()
                    .asString();

            return parseResponse(strResponse, responseType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<NameValuePair> convertToFormData(Map<String, String> paramMap) {
        Form form = Form.form();
        paramMap.forEach(form::add);
        return form.build();
    }

    private static <R> R parseResponse(String strResponse, Class<R> responseType) {
        try {
            return JSON.parseObject(strResponse, responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 序列化短信内容的变量
     */
    public static String serializeVariables(Map<String, String> variables) {
        if (variables == null || variables.size() == 0) {
            return "{}";
        }

        JSONObject json = new JSONObject();
        variables.forEach(json::put);
        return json.toJSONString();
    }

    public static String genSignature(String secretKey, Map<String, String> params) {
        // 1. 参数名按照ASCII码表升序排序
        String[] paramNames = params.keySet().toArray(new String[0]);
        Arrays.sort(paramNames);

        // 2. 按照排序拼接参数名与参数值
        StringBuilder sb = new StringBuilder();
        for (String name : paramNames) {
            String value = ObjectUtils.defaultIfNull(params.get(name), StringUtils.EMPTY);
            sb.append(name).append(value);
        }
        // 3. 将secretKey拼接到最后
        sb.append(secretKey);

        // 4. MD5是128位长度的摘要算法，用16进制表示，一个十六进制的字符能表示4个位，所以签名后的字符串长度固定为32个十六进制字符。
        return DigestUtils.md5Hex(sb.toString().getBytes(StandardCharsets.UTF_8));
    }

    public String getMD5Str(DataUser dataUser) {
        StringBuilder sb = new StringBuilder();
        sb.append(dataUser.getPhone()).append(dataUser.getPassword());
        String md5str = DigestUtils.md5Hex(sb.toString());
        redisTemplate.opsForValue().set(md5str, dataUser.getAccountId(), 1, TimeUnit.DAYS);
        return md5str;
    }

    public String getRomodphone() {
        String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + third;

    }

    public int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
