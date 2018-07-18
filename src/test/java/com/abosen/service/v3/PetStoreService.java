package com.abosen.service.v3;

import com.abosen.dao.v3.AccountDao;
import com.abosen.dao.v3.ItemDao;
import lombok.*;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */
@Getter
@Setter
@AllArgsConstructor
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;


    public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }
}
