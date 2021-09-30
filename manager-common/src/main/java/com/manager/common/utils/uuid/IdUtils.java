package com.manager.common.utils.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ID生成器工具类
 *
 * @author marvin
 */
public class IdUtils {
    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastSimpleUUID() {
        return UUID.fastUUID().toString(true);
    }

    /**
     * 生成 充值订单号
     *
     * @return
     */
    public static String getOrderId() {
        int machineId = 2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String newDate = sdf.format(new Date());
        Random random = new Random();
        return machineId + newDate + String.format("%06d", random.nextInt(999999));
    }

    /**
     * 生成 提现订单号
     *
     * @return
     */
    public static String getExchangeOrderId() {
        int machineId = 1;
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String newDate = sdf.format(new Date());
        Random random = new Random();
        return machineId + newDate + String.format("%06d", random.nextInt(999999));
    }
}
