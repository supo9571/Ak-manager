package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/8/19
 */
@FeignClient(value = "data-manager")
public interface DataService {

    @PostMapping(value = "/data/coins/list",consumes = "application/json")
    AjaxResult getCOins(@RequestBody Coins coins);

    @PostMapping(value = "/data/login/list",consumes = "application/json")
    AjaxResult getLogins(@RequestBody Login login);

    @PostMapping(value = "/data/login/today")
    AjaxResult selectTodayLogins();

    @PostMapping(value = "/data/login/count",consumes = "application/json")
    AjaxResult count(@RequestParam("type") String type);

    @PostMapping(value = "/data/online/list",consumes = "application/json")
    AjaxResult getOnlines(@RequestBody OnlinePlayer onlinePlayer);

    @PostMapping(value = "/data/player/list",consumes = "application/json")
    AjaxResult getPlayers(@RequestBody PlayUser playUser);

    @PostMapping(value = "/data/card/list",consumes = "application/json")
    AjaxResult getCards(@RequestBody Card card);
}
