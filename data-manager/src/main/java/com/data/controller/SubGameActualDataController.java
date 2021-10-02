package com.data.controller;

import com.data.service.SubGameActualDataService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Game;
import com.manager.common.core.domain.model.SubGameActualData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@RestController
@RequestMapping("/data/subGameActualData")
@Slf4j
public class SubGameActualDataController extends BaseController {

    @Autowired
    private SubGameActualDataService subGameActualDataService;

    /**
     * 查询
     * @param subGameActualData 过滤条件
     */
    @PostMapping("/list")
    public AjaxResult getSubGameActualDataList(@RequestBody SubGameActualData subGameActualData) {
        startOrder(subGameActualData.getOrderByColumn(),subGameActualData.getIsAsc());
        Map result = new HashMap();
        result.put("list", subGameActualDataService.getSubGameActualDataList(subGameActualData));
        return AjaxResult.success("查询成功", result);
    }


}
