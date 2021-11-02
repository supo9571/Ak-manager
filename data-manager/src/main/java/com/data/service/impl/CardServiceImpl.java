package com.data.service.impl;

import com.data.mapper.CardMapper;
import com.data.service.CardService;
import com.manager.common.core.domain.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/25
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;

    @Override
    public List selectCard(Card card) {
        String tid = card.getTid();
        if(!"0".equals(tid)){
            card.setChannel(cardMapper.getChannel(tid));
        }
        return cardMapper.selectCard(card);
    }

    @Override
    public List selectCardUser(Card card) {
        return cardMapper.selectCardUser(card);
    }

    @Override
    public Map selectCardCount(Card card) {
        String tid = card.getTid();
        if(!"0".equals(tid)){
            card.setChannel(cardMapper.getChannel(tid));
        }
        return cardMapper.selectCardCount(card);
    }

    @Override
    public List selectCardUserCount(Card card) {
        return cardMapper.selectCardUserCount(card);
    }

    @Override
    public Map findCardInfo(String tableGid) {
        Map map = cardMapper.findList(tableGid);
        map.put("userList", cardMapper.findUserInfo(tableGid));
        return map;
    }
}
