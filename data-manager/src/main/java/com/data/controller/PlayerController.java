package com.data.controller;

import com.data.service.PlayerService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author marvin 2021/8/20
 */
@RestController
@RequestMapping("data/player")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody PlayUser playUser){
        startPage(playUser.getPage(),playUser.getSize(),playUser.getOrderByColumn(),playUser.getIsAsc());
        List list = playerService.selectPlayer(playUser);
        return AjaxResult.success(getDataTable(list));
    }

    @PostMapping("/curr")
    public AjaxResult curr(Long uid){
        List list = playerService.selectPlayerCurr(uid);
        return AjaxResult.success(list);
    }

    @PostMapping("/edit")
    public AjaxResult update(@RequestBody PlayUser playUser){
        if(StringUtils.isNotBlank(playUser.getPassword())){
            String password = DigestUtils.md5DigestAsHex(playUser.getPassword().getBytes(StandardCharsets.UTF_8));
            playUser.setPassword(password);
        }
        Integer i = playerService.updatePlayer(playUser);
        return i>0?AjaxResult.success():AjaxResult.error();
    }
}
