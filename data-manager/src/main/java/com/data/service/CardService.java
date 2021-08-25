package com.data.service;

import com.manager.common.core.domain.model.Card;

import java.util.List;

/**
 * @author marvin 2021/8/19
 */
public interface CardService {
    List selectCard(Card card);
}
