package com.data.controller;

import com.data.service.SubGameDataService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@RestController
@RequestMapping("/data/subgame")
@Slf4j
public class SubGameDataController extends BaseController {

    @Autowired
    private SubGameDataService subGameDataService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody SubGameData subGameData) {
        startOrder("betCount","desc");
        return AjaxResult.success("查询成功", subGameDataService.getSubGameDataList(subGameData));
    }

    @PostMapping("/table")
    public AjaxResult table(@RequestBody SubGameData subGameData) {
        startOrder("betCount","desc");
        return AjaxResult.success("查询成功", subGameDataService.getTableDate(subGameData));
    }

}
