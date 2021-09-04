package com.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.service.GameService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/27
 */
@RestController
@RequestMapping("/data/game")
@Slf4j
public class GameController extends BaseController{

    @Autowired
    private GlobalConfig globalConfig;

    @Autowired
    private GameService gameService;

    /**
     * 获取游戏下拉选项
     */
    @PostMapping("/option")
    public AjaxResult list() {
        return AjaxResult.success("查询成功", gameService.getGames());
    }

    /**
     * 发送配置到服务器
     */
    @PostMapping("/send")
    public AjaxResult send() {
        String domain = globalConfig.getDomain();
        String gameSend = globalConfig.getGameSend();
        //查询 游戏配置
        String param = gameService.getGameConfig();
        String result = HttpUtils.sendPost(domain + gameSend, "data="+param);
        if (!"scuess".equals(result)) {
            log.error(result);
            return AjaxResult.error(result);
        }
        return AjaxResult.success("发送成功");
    }

    /**
     * 添加 测试ip
     */
    @PostMapping("/addIp")
    public AjaxResult addIp(String ip,String createBy) {
        gameService.saveIp(ip,createBy);
        return AjaxResult.success();
    }

    /**
     * 查询 测试ip
     */
    @PostMapping("/findIp")
    public AjaxResult findIp(String ip,String createBy,String beginTime,String endTime) {
        return AjaxResult.success(gameService.findIp(ip,createBy,beginTime,endTime));
    }

    /**
     * 删除 测试ip
     */
    @PostMapping("/delIp")
    public AjaxResult delIp(Integer id) {
        gameService.delIp(id);
        return AjaxResult.success();
    }
}
