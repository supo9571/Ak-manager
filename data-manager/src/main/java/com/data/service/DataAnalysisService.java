package com.data.service;

import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;

import java.util.List;

/**
 * @author jason
 * @date 2021-10-08
 */
public interface DataAnalysisService {


    List<DataAnalysisVO> withdrawTopList(DataAnalysisParam param);


}
