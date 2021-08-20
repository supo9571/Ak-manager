package com.data.controller.data;

import com.data.controller.common.BaseController;
import com.data.service.PlayerService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AjaxResult list(PlayUser playUser){
        startPage(playUser.getPage(),playUser.getSize(),playUser.getOrderByColumn(),playUser.getIsAsc());
        List list = playerService.selectPlayer(playUser);
        return AjaxResult.success(getDataTable(list));
    }
}
