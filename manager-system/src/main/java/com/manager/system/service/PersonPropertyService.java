package com.manager.system.service;

import com.manager.common.core.domain.entity.PersonProperty;

import java.util.List;

/**
 * @author marvin 2021/10/13
 */
public interface PersonPropertyService {

    List getPersonPropertys();

    Integer editPersonPropertys(PersonProperty personProperty);

    String sendProperty();
}
