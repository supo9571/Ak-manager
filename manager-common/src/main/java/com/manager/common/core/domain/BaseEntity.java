package com.manager.common.core.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Entity基类
 *
 * @author marvin
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    /**
     * 备注
     */
    private String remark;

    private Integer page;

    private Integer size;

    private String orderByColumn;

    private String isAsc;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * 开始时间 ms
     */
    private String beginms;

    /**
     * 结束时间 ms
     */
    private String endms;

    public String getBeginms(){
        if(StringUtils.isNotBlank(this.beginTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(this.beginTime).getTime() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return beginms;
    }

    public String getEndms(){
        if(StringUtils.isNotBlank(this.endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return sdf.parse(this.endTime).getTime() + "";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return endms;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
