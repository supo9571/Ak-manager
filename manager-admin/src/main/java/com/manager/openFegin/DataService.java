package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.*;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.param.PlayerReportParam;
import com.manager.common.core.domain.model.vo.PlayerDayReportVO;
import com.manager.common.core.domain.model.vo.PlayerGameReportVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/8/19
 */
@FeignClient(value = "data-manager")
public interface DataService {

    @PostMapping(value = "/data/coins/list", consumes = "application/json")
    AjaxResult getCoins(@RequestBody Coins coins);

    @PostMapping(value = "/data/login/list", consumes = "application/json")
    AjaxResult getLogins(@RequestBody Login login);

    @PostMapping(value = "/data/login/today")
    AjaxResult selectTodayLogins();

    @PostMapping(value = "/data/login/count")
    AjaxResult count(@RequestParam("type") String type);

    @PostMapping(value = "/data/online/list", consumes = "application/json")
    AjaxResult getOnlines(@RequestBody OnlinePlayer onlinePlayer);

    @PostMapping(value = "/data/online/forbidden")
    AjaxResult forbidden(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/list", consumes = "application/json")
    AjaxResult getPlayers(@RequestBody PlayUser playUser);

    @PostMapping(value = "/data/player/curr")
    AjaxResult getPlayerCurr(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/card/list", consumes = "application/json")
    AjaxResult getCards(@RequestBody Card card);

    @PostMapping(value = "/data/card/info")
    AjaxResult findCardInfo(@RequestParam("tableGid") String tableGid);

    @PostMapping(value = "/data/game/option")
    AjaxResult getGames();

    @PostMapping(value = "/data/game/send")
    AjaxResult send();

    @PostMapping(value = "/data/game/list", consumes = "application/json")
    AjaxResult getSubGameList(@RequestBody Game game);

    @PostMapping(value = "/data/game/edit", consumes = "application/json")
    AjaxResult editSubGame(@RequestBody Game game);

    @PostMapping(value = "/data/subGameActualData/list", consumes = "application/json")
    AjaxResult getSubGameActualDataList(@RequestBody SubGameData subGameActualData);

    @PostMapping(value = "/data/subGameActualData/export", consumes = "application/json")
    AjaxResult exportSubGameActualData(@RequestBody SubGameData subGameActualData);

    @PostMapping(value = "/data/game/addIp")
    AjaxResult addIp(@RequestParam("ip") String ip, @RequestParam("createBy") String createBy);

    @PostMapping(value = "/data/game/findIp")
    AjaxResult findIp(@RequestParam("ip") String ip, @RequestParam("createBy") String createBy,
                      @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    @PostMapping(value = "/data/game/delIp")
    AjaxResult delIp(@RequestParam("id") Integer id);

    @PostMapping(value = "/data/player/edit", consumes = "application/json")
    AjaxResult updatePlayer(@RequestBody PlayUser playUser);

    @PostMapping(value = "/data/allupdate/add", consumes = "application/json")
    AjaxResult addAllUpdate(@RequestBody Allupdate allupdate);

    @PostMapping(value = "/data/allupdate/list")
    AjaxResult findAllUpdate(@RequestBody Allupdate allupdate);

    @PostMapping(value = "/data/allupdate/history")
    AjaxResult findAllUpdateHistory(@RequestParam("tid") Integer tid);

    @PostMapping(value = "/data/allupdate/edit", consumes = "application/json")
    AjaxResult editAllUpdateHistory(@RequestBody Allupdate allupdate);

    @PostMapping(value = "/data/allupdate/del")
    AjaxResult deleteAllupdate(@RequestParam("id") Integer id);

    //热更新
    @PostMapping(value = "/data/hotupdate/list")
    AjaxResult findHotupdate(@RequestBody Hotupdate hotupdate);

    @PostMapping(value = "/data/hotupdate/add")
    AjaxResult addHotUpdate(@RequestBody Hotupdate hotUpdate);

    @PostMapping(value = "/data/hotupdate/edit")
    AjaxResult editHotUpdate(@RequestBody Hotupdate hotUpdate);

    @PostMapping(value = "/data/hotupdate/del")
    AjaxResult delHotupdate(@RequestParam("id") Integer id);

    @PostMapping(value = "/data/hotupdate/find")
    AjaxResult findhotUpdateById(@RequestParam("id") Integer id);

    @PostMapping(value = "/data/activity/report/list")
    AjaxResult getActivityList(@RequestBody Coins coins);

    @PostMapping(value = "/data/player/info")
    AjaxResult getInfo(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/editExchange", consumes = "application/json")
    AjaxResult updateBank(@RequestBody UserExchange userExchange);

    @PostMapping(value = "/data/player/recAndexc")
    AjaxResult recAndexc(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/rechargeInfo", consumes = "application/json")
    AjaxResult rechargeInfo(@RequestBody PlayUser playUser);

    @PostMapping(value = "/data/player/exchangeInfo", consumes = "application/json")
    AjaxResult exchangeInfo(@RequestBody PlayUser playUser);

    @PostMapping(value = "/data/addUser/list", consumes = "application/json")
    AjaxResult getAddUser(@RequestBody AddUser addUser);

    @PostMapping(value = "/data/player/userInfo")
    AjaxResult userInfo(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/waterInfo", consumes = "application/json")
    AjaxResult waterInfo(@RequestBody PlayWater playWater);

    @PostMapping(value = "/data/player/updateToken")
    AjaxResult updateToken(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/lock", consumes = "application/json")
    AjaxResult lock(@RequestBody UserLock userLock);

    @PostMapping(value = "/data/player/lock", consumes = "application/json")
    AjaxResult lockType(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/player/lockLog", consumes = "application/json")
    AjaxResult lockLog(@RequestParam("uid") Long uid);

    @PostMapping(value = "/data/report/withdraw/top/List", consumes = "application/json")
    AjaxResult withdrawTopList(@RequestBody DataAnalysisParam param);

    @PostMapping(value = "/data/report/water/top/List", consumes = "application/json")
    AjaxResult getDataWaterTopList(@RequestBody DataAnalysisParam param);

    @PostMapping(value = "/data/retainedAnalysis/list", consumes = "application/json")
    AjaxResult getRetainedAnalysis(@RequestBody RetainedAnalysis retainedAnalysis);

    @PostMapping(value = "/data/report/recharge/top/List", consumes = "application/json")
    AjaxResult getRechargeTopList(@RequestBody DataAnalysisParam param);

    @PostMapping(value = "/data/ltvReport/list", consumes = "application/json")
    AjaxResult getLtvReport(@RequestBody LtvReport ltvReport);

    @PostMapping(value = "/data/report/earnings/top/List", consumes = "application/json")
    AjaxResult getEarningsTopList(@RequestBody DataAnalysisParam param);

    @PostMapping("/data/directRecharge/list")
    AjaxResult getDirectRecharge(@RequestBody DirectRecharge param);

    @PostMapping("/data/directRecharge/subList")
    AjaxResult getSubDirectRecharge(@RequestBody DirectRecharge param);

    @PostMapping(value = "/data/report/agent/top/List", consumes = "application/json")
    AjaxResult getAgentTopList(@RequestBody DataAnalysisParam param);

    @PostMapping(value = "/data/report/pay/top/List", consumes = "application/json")
    AjaxResult getPayInfoList(@RequestBody DataAnalysisParam param);

    @PostMapping(value = "/data/rechargeAndExchange/list", consumes = "application/json")
    AjaxResult getRechargeAndExchange(@RequestBody RechargeAndExchange param);

    @PostMapping(value = "/data/report/player/List", consumes = "application/json")
    AjaxResult getPlayerReportList(@RequestBody PlayerReportParam param);

    @PostMapping(value = "/data/report/player/export", consumes = "application/json")
    AjaxResult getPlayerReportExport(@RequestBody PlayerReportParam param);

    @PostMapping(value = "/data/report/player/game/List", consumes = "application/json")
    AjaxResult getPlayerGameReportList(@RequestBody PlayerReportParam param);

    @PostMapping(value = "/data/report/player/day/List", consumes = "application/json")
    AjaxResult getPlayerDayReportList(@RequestBody PlayerReportParam param);

    @PostMapping(value = "/data/onlineDataDay/getOnlineUserData", consumes = "application/json")
    AjaxResult getOnlineUserData(@RequestBody OnlineDataDay param);

    @PostMapping(value = "/data/onlineDataDay/getPcuData", consumes = "application/json")
    AjaxResult getPcuData(@RequestBody OnlineDataDay param);

    @PostMapping(value = "/data/onlineDataDay/getOnlineUserNum", consumes = "application/json")
    AjaxResult getOnlineUserNum(@RequestBody OnlineDataDay param);

}
