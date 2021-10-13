package com.manager.system.mapper;

import com.manager.common.core.domain.entity.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author marvin 2021/10/12
 */
@Mapper
public interface TagsMapper {

    int addTags(Tags tags);

    int editTags(Tags tags);

    List getTags(@Param("tagType") int tagType);

    int deleteTags(@Param("id") Integer id);
}
