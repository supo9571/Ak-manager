package com.manager.system.service;

import com.manager.common.core.domain.entity.Tags;

import java.util.List;

/**
 * @author marvin 2021/10/12
 */
public interface TagsService {

    int addTags(Tags tags);

    int editTags(Tags tags);

    List getTags(int tagType);

    int deleteTags(Integer id);
}
