package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Login;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/8/21
 */
@FeignClient(value = "data-manager")
public interface LoginService {
    @PostMapping(value = "/data/login/list",consumes = "application/json")
    AjaxResult list(@RequestBody Login login);

    @PostMapping(value = "/data/login/today")
    AjaxResult selectTodayLogins();

    @PostMapping(value = "/data/login/count",consumes = "application/json")
    AjaxResult count(@RequestParam("type") String type);
}
