package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.Tags;
import com.manager.system.mapper.TagsMapper;
import com.manager.system.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/10/12
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public int addTags(Tags tags) {
        return tagsMapper.addTags(tags);
    }

    @Override
    public int editTags(Tags tags) {
        return tagsMapper.editTags(tags);
    }

    @Override
    public List getTags(int tagType) {
        return tagsMapper.getTags(tagType);
    }

    @Override
    public int deleteTags(Integer id) {
        return tagsMapper.deleteTags(id);
    }
}
