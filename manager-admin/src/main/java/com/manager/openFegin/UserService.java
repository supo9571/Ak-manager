package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author marvin 2021/8/19
 */
//@FeignClient(value = "data-manager")
public interface UserService {

//    @PostMapping(value = "/data/player/list")
    AjaxResult list(PlayUser playUser);
}
