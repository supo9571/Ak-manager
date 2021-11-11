package com.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.mapper.TenantMapper;
import com.data.mapper.UpdateMapper;
import com.data.service.UpdateService;
import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 * 包更新 service
 */
@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UpdateMapper updateMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<Map> selectPackage(String ip, String channelId, String versionId, String platform) {
        if ("android".equals(platform)) {
            platform = "1";
        } else if ("ios".equals(platform)) {
            platform = "2";
        } else {
            platform = "3";
        }
        return updateMapper.selectPackage(ip, tenantMapper.getTidByCid(channelId), versionId, platform);
    }

    @Override
    public List<Map> selectConsumer() {
        List<Map> list = updateMapper.selectConsumer();
        list.forEach(m -> {
            try {
                Object object = JSON.parse((String) m.get("info"));
                if (object != null) {
                    m.put("info", object);
                } else {
                    m.remove("info");
                }
            } catch (Exception e) {
                m.remove("info");
            }
        });
        return list;
    }

    /**
     * 添加 整包更新
     */
    @Override
    public Integer addAllUpdate(Allupdate allupdate) {
        allupdate.setVerInt(verInt(allupdate.getVersion()));
        return updateMapper.addAllUpdate(allupdate);
    }

    @Override
    public List findAllUpdate() {
        return updateMapper.findAllUpdate();
    }

    @Override
    public List findAllUpdateHistory(Integer tid) {
        return updateMapper.findAllUpdateHistory(tid);
    }

    @Override
    public Integer editAllUpdate(Allupdate allupdate) {
        allupdate.setVerInt(verInt(allupdate.getVersion()));
        return updateMapper.editAllUpdate(allupdate);
    }

    @Override
    public Integer deleteAllupdate(String id) {
        return updateMapper.deleteAllupdate(id);
    }

    @Override
    public String selectAllupdate(String channelId, String versionId) {
        //查询该平台 最新版本更新地址
        Map map = updateMapper.selectAllupdate(channelId);
        if (map != null && !versionId.equals(map.get("version"))) {
            //计算版本号
            String[] vers = versionId.split("\\.");
            int verInt = Integer.valueOf(vers[0]) * 10000 + Integer.valueOf(vers[1]) * 100 + Integer.valueOf(vers[2]);
            int var = (int) map.get("verInt");
            if(var>verInt){
                return (String) map.get("apkUpdateUrl");
            }
        }
        return null;
    }

    /**
     * 添加 热更新
     */
    @Override
    public int addHotUpdate(Hotupdate hotUpdate) {
        hotUpdate.setVerInt(verInt(hotUpdate.getVersion()));
        return updateMapper.addHotUpdate(hotUpdate);
    }

    @Override
    public int editHotUpdate(Hotupdate hotUpdate) {
        hotUpdate.setVerInt(verInt(hotUpdate.getVersion()));
        return updateMapper.editHotUpdate(hotUpdate);
    }

    @Override
    public Integer delHotupdate(String id) {
        return updateMapper.delHotupdate(id);
    }

    @Override
    public List findHotupdate() {
        return updateMapper.findHotupdate();
    }

    @Override
    public List findHotupdateById(Integer id) {
        return updateMapper.findHotupdateById(id);
    }

    @Override
    public Map selectStopNotice(String channelId) {
        Integer tid = tenantMapper.getTidByCid(channelId);
        return updateMapper.selectStopNotice(tid);
    }

    private Integer verInt(String version) {
        //计算版本号
        String[] vers = version.split("\\.");
        int verInt = Integer.valueOf(vers[0]) * 10000 + Integer.valueOf(vers[1]) * 100 + Integer.valueOf(vers[2]);
        return verInt;
    }
}
