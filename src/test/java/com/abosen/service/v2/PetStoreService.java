package com.abosen.service.v2;

import com.abosen.dao.v2.AccountDao;
import com.abosen.dao.v2.ItemDao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qiubaisen
 * @date 2018/7/14
 */

@Setter
@Getter
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;
    private int version;
}
