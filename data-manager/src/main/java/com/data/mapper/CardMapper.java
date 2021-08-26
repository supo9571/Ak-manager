package com.data.mapper;

import com.manager.common.core.domain.model.Card;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/25
 */
@Mapper
public interface CardMapper {

    List<Map> selectCard(Card card);

    List<Map> selectCardUser(Card card);

    Map selectCardCount(Card card);

    Map selectCardUserCount(Card card);
}
