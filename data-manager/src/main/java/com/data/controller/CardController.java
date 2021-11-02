package com.data.controller;

import com.data.service.CardService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Card;
import org.apache.commons.lang3.StringUtils;
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
 * @author marvin 2021/8/25
 * 牌局记录
 */
@RestController
@RequestMapping("/data/card")
public class CardController extends BaseController {

    @Autowired
    private CardService cardService;

    /**
     * 获取牌局记录列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Card card) {
        startPage(card.getPage(), card.getSize(), null, null);
        List list;
        Map map = new HashMap();
        if (StringUtils.isBlank(card.getUid())) {
            list = cardService.selectCard(card);
            map = cardService.selectCardCount(card);
        } else {
            list = cardService.selectCardUser(card);
            List<Map> cardUserCount = cardService.selectCardUserCount(card);
            BigDecimal countScore = new BigDecimal(0);
            BigDecimal countPayfee = new BigDecimal(0);
            BigDecimal countBetCoins = new BigDecimal(0);
            for (int i = 0; i < cardUserCount.size(); i++) {
                Map m = cardUserCount.get(i);
                if(m!=null){
                    countScore = countScore.add((BigDecimal) m.get("countScore"));
                    countPayfee = countPayfee.add((BigDecimal) m.get("countPayfee"));
                    countBetCoins = countBetCoins.add((BigDecimal) m.get("countBetCoins"));
                }
            }
            map.put("countScore",countScore);
            map.put("countPayfee",countPayfee);
            map.put("countBetCoins",countBetCoins);
        }
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", map);
        return AjaxResult.success("查询成功", result);
    }

    /**
     * 获取牌局详情
     */
    @PostMapping("/info")
    public AjaxResult info(String tableGid) {
        return AjaxResult.success("查询成功", cardService.findCardInfo(tableGid));
    }
}
