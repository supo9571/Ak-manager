package com.data.service;

import com.manager.common.core.domain.model.Card;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/19
 */
public interface CardService {
    List selectCard(Card card);

    List selectCardUser(Card card);

    Map selectCardCount(Card card);

    Map selectCardUserCount(Card card);

    Map findCardInfo(String tableGid);
}
