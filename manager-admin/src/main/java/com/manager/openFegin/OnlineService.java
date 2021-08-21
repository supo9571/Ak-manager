package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.OnlinePlayer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author marvin 2021/8/21
 */
@FeignClient(value = "data-manager")
public interface OnlineService {
    @PostMapping(value = "/data/online/list",consumes = "application/json")
    AjaxResult list(@RequestBody OnlinePlayer onlinePlayer);
}
