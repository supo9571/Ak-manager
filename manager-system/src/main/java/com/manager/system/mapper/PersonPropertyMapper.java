package com.manager.system.mapper;

import com.manager.common.core.domain.entity.PersonProperty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 */
@Mapper
public interface PersonPropertyMapper {

    List getPersonPropertys();

    Integer editPersonPropertys(PersonProperty personProperty);

    List<Map> getPersonPropertyList();
}
