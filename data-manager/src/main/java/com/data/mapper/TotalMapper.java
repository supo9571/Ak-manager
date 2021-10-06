package com.data.mapper;

import com.manager.common.core.domain.model.Summarize;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/10/6
 */
@Mapper
public interface TotalMapper {

    List getTotals(Summarize summarize);
}
