package com.manager.web.controller.system;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.SysUser;
import com.manager.common.core.domain.model.LoginUser;
import com.manager.common.utils.ServletUtils;
import com.manager.framework.web.service.TokenService;
import com.manager.system.service.SysIpBlackService;
import com.manager.system.service.SysIpWhiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/ip")
@Api(tags = "IP管理")
public class SysIpController extends BaseController {

    @Autowired
    private SysIpWhiteService sysIpWhiteService;

    @Autowired
    private TokenService tokenService;
    /**
     * 添加白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:add')")
    @ApiOperation(value = "添加白名单")
    @GetMapping("/white/add")
    public AjaxResult add(long deptId,long userId,String ips){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        sysIpWhiteService.addIpWhite(deptId,userId,ips,user.getUserId());
        return success();
    }

    /**
     * 删除白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:delete')")
    @ApiOperation(value = "删除白名单")
    @GetMapping("/white/delete")
    public AjaxResult delete(long id){
        sysIpWhiteService.delIpWhite(id);
        return success();
    }

    /**
     * 查询白名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:list')")
    @ApiOperation(value = "查询白名单")
    @GetMapping("/white/list")
    public AjaxResult list(String deptId,String userId,String ip){
        startPage();
        List list = sysIpWhiteService.selectIpWhiteList(deptId,userId,ip);
        return AjaxResult.success("查询白名单成功",getDataTable(list));
    }

    @Autowired
    private SysIpBlackService sysIpBlackService;

    /**
     * 添加黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:add')")
    @ApiOperation(value = "添加黑名单")
    @GetMapping("/black/add")
    public AjaxResult addBlack(long deptId,long userId,String ips){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        sysIpBlackService.addIpBlack(deptId,userId,ips,user.getUserId());
        return success();
    }

    /**
     * 删除黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:delete')")
    @ApiOperation(value = "删除黑名单")
    @GetMapping("/black/delete")
    public AjaxResult deleteBlack(long id){
        sysIpBlackService.delIpBlack(id);
        return success();
    }

    /**
     * 查询黑名单
     */
    @PreAuthorize("@ss.hasPermi('system:ip:list')")
    @ApiOperation(value = "查询黑名单")
    @GetMapping("/black/list")
    public AjaxResult listBlack(String deptId,String userId,String ip){
        startPage();
        List list = sysIpBlackService.selectIpBlackList(deptId,userId,ip);
        return AjaxResult.success("查询白名单成功",getDataTable(list));
    }
}
