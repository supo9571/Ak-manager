package com.data.service.impl;

import com.data.mapper.AddUserMapper;
import com.data.service.AddUserService;
import com.manager.common.core.domain.model.AddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
@Service
public class AddUserServiceImpl implements AddUserService {

    @Autowired
    private AddUserMapper addUserMapper;

    @Override
    public List getList(AddUser addUser) {
        // 默认查看当前身份的总数据
        Long tid = addUserMapper.getTid(addUser.getTid2());
        addUser.setTid2(tid);
        // 获取计算完成后的数据
        List<AddUser> addUserList = addUserMapper.getList(addUser);

        List list  = merge(addUserList);
        return list;
    }

    //  处理注册玩牌率/新增用户牌局数两特殊字段
    private List merge(List<AddUser> list) {

        for (AddUser addUser1 : list) {
            List<String> userList = addUserMapper.getUserList(addUser1.getDay());

            // 当天没有新增人数 直接给0
            if(userList != null && userList.size() > 0){
                // 注册玩牌率
                Integer cardPlayingRate = addUserMapper.getCardPlayingRate(userList,addUser1.getDay());
                // 新增用户牌局数
                Integer userCardCount = addUserMapper.getUserCardCount(userList,addUser1.getDay());

                // 注册玩牌率：新增用户中有牌局记录的玩家数（去重）/ 新增用户数
                if(cardPlayingRate != null){
                    addUser1.setCardPlayingRate(cardPlayingRate.doubleValue() / Double.parseDouble(addUser1.getUid()));
                    // 新增用户牌局数：新增用户产生的牌局数量
                    addUser1.setUserCardCount(userCardCount);
                } else {
                    addUser1.setCardPlayingRate(0);
                    addUser1.setUserCardCount(0);
                }
            } else {
                addUser1.setCardPlayingRate(0);
                addUser1.setUserCardCount(0);
            }
        }
        return list;
    }
}

