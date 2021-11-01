package com.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.service.OnlineService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.OnlinePlayer;
import com.manager.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/19
 */
@RestController
@RequestMapping("/data/online")
public class OnlineController extends BaseController {

    @Autowired
    private OnlineService onlineService;

    /**
     * 获取在线玩家列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody OnlinePlayer onlinePlayer) {
        startPage(onlinePlayer.getPage(), onlinePlayer.getSize(), onlinePlayer.getOrderByColumn(), onlinePlayer.getIsAsc());
        List list = onlineService.selectOnline(onlinePlayer);
        //计算总计
        Map count = onlineService.selectOnlineCount(onlinePlayer);
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", count);
        return AjaxResult.success("查询成功", result);
    }

    @Autowired
    private GlobalConfig globalConfig;
    /**
     * 下线
     */
    @PostMapping("/forbidden")
    public AjaxResult forbidden(Long uid) {
        JSONObject param = new JSONObject();
        param.put("cmd", "forbidden");//"addcoins"=加金币 “reducecoins”=减金币 “forbidden”=踢人
        param.put("reason", "");
        param.put("uid", uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                "data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //返回操作后金额
            return AjaxResult.success("操作成功");
        }
        return AjaxResult.error();
    }
}
