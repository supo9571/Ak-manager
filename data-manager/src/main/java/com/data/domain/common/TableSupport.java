package com.data.domain.common;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 表格数据处理
 *
 * @author marvin
 */
public class TableSupport {
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "page";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "size";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        pageDomain.setPageNum(getInteger(PAGE_NUM,attributes));
        pageDomain.setPageSize(getInteger(PAGE_SIZE,attributes));
        pageDomain.setOrderByColumn(attributes.getRequest().getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(attributes.getRequest().getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }

    private static Integer getInteger(String key,ServletRequestAttributes attributes){
        String s = attributes.getRequest().getParameter(key);
        if(StringUtils.isBlank(s)){
            if(PAGE_NUM.equals(key)){
                return 1;
            }else{
                return 10;
            }
        }
        return Integer.valueOf(s);
    }
}
