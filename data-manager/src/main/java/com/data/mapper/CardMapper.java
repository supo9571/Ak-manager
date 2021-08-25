package com.data.mapper;

import com.manager.common.core.domain.model.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/8/25
 */
@Mapper
public interface CardMapper {

    List<Card> selectCard(Card card);
}
