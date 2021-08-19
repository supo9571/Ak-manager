package com.data.controller.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.page.PageDomain;
import com.manager.common.core.page.TableDataInfo;

import java.util.List;

/**
 * @author marvin 2021/8/19
 */
public class BaseController {

    /**
     * 设置请求分页数据
     */
    protected void startPage(Integer page,Integer size,String orderByColumn,String isAsc) {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(page);
        pageDomain.setPageSize(size);
        pageDomain.setOrderByColumn(orderByColumn);
        pageDomain.setIsAsc(isAsc);

        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(page, size, orderByColumn);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        PageInfo pageInfo = new PageInfo(list);
        TableDataInfo rspData = new TableDataInfo(pageInfo.getTotal(), list, pageInfo.getPageSize(), pageInfo.getPageNum());
        return rspData;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

}
