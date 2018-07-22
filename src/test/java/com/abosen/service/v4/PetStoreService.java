package com.abosen.service.v4;

import com.abosen.beans.factory.annotation.Autowired;
import com.abosen.dao.v4.AccountDao;
import com.abosen.dao.v4.ItemDao;
import com.abosen.stereotype.Component;
import lombok.Getter;

/**
 * @author qiubaisen
 * @date 2018/7/22
 */

@Component(value = "petStore")
@Getter
public class PetStoreService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;
}
