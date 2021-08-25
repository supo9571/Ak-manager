package com.data.controller;

import com.data.service.CardService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List list = cardService.selectCard(card);
        return AjaxResult.success("查询成功", getDataTable(list));
    }
}
