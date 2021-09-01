package com.data.service.impl;

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

    @Override
    public List<Map> selectPackage(String ip, String channelId, String versionId, String platform) {
        return updateMapper.selectPackage(ip,channelId,versionId,platform);
    }

    @Override
    public List<Map> selectConsumer() {
        return updateMapper.selectConsumer();
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
        if(map==null || versionId.equals(map.get("version"))){
            return null;
        }
        return (String) map.get("apkUpdateUrl");
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

    private Integer verInt(String version){
        //计算版本号
        String[] vers = version.split("\\.");
        int verInt = Integer.valueOf(vers[0])*10000+Integer.valueOf(vers[1])*100+Integer.valueOf(vers[2]);
        return verInt;
    }
}
