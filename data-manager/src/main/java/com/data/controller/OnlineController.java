package com.data.controller;

import com.data.service.CoinsService;
import com.data.service.OnlineService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import com.manager.common.core.domain.model.OnlinePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public AjaxResult list(OnlinePlayer onlinePlayer) {
        startPage(onlinePlayer.getPage(),onlinePlayer.getSize(),onlinePlayer.getOrderByColumn(),onlinePlayer.getIsAsc());
        List list =onlineService.selectOnline(onlinePlayer);
        return AjaxResult.success("查询成功", getDataTable(list));
    }
}
