package com.data.service;

import java.util.List;

/**
 * @author marvin 2021/9/25
 */
public interface MailService {
    List getTips(String channelId,String uid);

    List getMailList(String channelId, String uid);
}
