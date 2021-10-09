package com.data.controller;

import com.data.service.AddUserService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.SubGameData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
@RestController
@RequestMapping("/data/addUser")
@Slf4j
public class AddUserController extends BaseController {

    @Autowired
    private AddUserService addUserService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody AddUser addUser) {
        return AjaxResult.success("查询成功", addUserService.getList(addUser));
    }

}
