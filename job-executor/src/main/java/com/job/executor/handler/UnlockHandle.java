package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.executor.mapper.UnlockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author marvin 2021/11/10
 */
@Component
@Slf4j
public class UnlockHandle {

    @Autowired
    private UnlockMapper unlockMapper;
    /**
     * 账号自动解封
     */
    @XxlJob("user_unlock")
    @PostConstruct
    public void unlock() {
        List<Long> list =  unlockMapper.selectUnlock();
        if(list.size()>0){
            list.forEach(l->{
                unlockMapper.updateLock(l);
                unlockMapper.saveLockInfo(l);
            });
        }
    }
}
