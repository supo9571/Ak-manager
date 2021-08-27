package com.data.controller;

import com.data.service.CardService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Card;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/25
 * 牌局记录
 */
@RestController
@RequestMapping("/data/card")
public class CardController extends BaseController {

    @Autowired
    private CardService cardService;
    /**
     * 获取用户列表
     */
    @PostMapping("/list")
    public AjaxResult list(Card card) {
        startPage(card.getPage(),card.getSize(),card.getOrderByColumn(),card.getIsAsc());
        List list;
        Map map;
        if(StringUtils.isBlank(card.getUid())){
            list = cardService.selectCard(card);
            map = cardService.selectCardCount(card);
        }else{
            list = cardService.selectCardUser(card);
            map = cardService.selectCardUserCount(card);
        }
        Map result = new HashMap();
        result.put("list",getDataTable(list));
        result.put("count",map);
        return AjaxResult.success("查询成功",result);
    }
}
