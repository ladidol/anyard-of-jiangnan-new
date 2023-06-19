package org.cuit.fhzheng.user.service.impl;

import org.cuit.fhzheng.common.cache.constant.UserCacheNames;
import org.cuit.fhzheng.common.order.vo.UserAddrVO;
import org.cuit.fhzheng.user.mapper.UserAddrMapper;
import org.cuit.fhzheng.user.model.UserAddr;
import org.cuit.fhzheng.user.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lth
 * @Date  2023/7/1 17:38
 */
@Service
public class UserAddrServiceImpl implements UserAddrService {

    @Autowired
    private UserAddrMapper userAddrMapper;

    @Override
    public List<UserAddrVO> list(Long userId) {
        return userAddrMapper.list(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserAddr userAddr) {
        if (userAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)) {
            userAddrMapper.removeDefaultUserAddr(userAddr.getUserId());
        }
        userAddrMapper.save(userAddr);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserAddr userAddr) {
        if (userAddr.getIsDefault().equals(UserAddr.DEFAULT_ADDR)) {
            userAddrMapper.removeDefaultUserAddr(userAddr.getUserId());
        }
        userAddrMapper.update(userAddr);
    }

    @Override
    public void deleteUserAddrByUserId(Long addrId, Long userId) {
        userAddrMapper.deleteById(addrId,userId);
    }

    @Override
    public UserAddrVO getUserAddrByUserId(Long addrId, Long userId) {
        // 获取用户默认地址
        if (addrId == 0) {
            return userAddrMapper.getUserDefaultAddrByUserId(userId);
        }
        return userAddrMapper.getByAddrId(addrId,userId);
    }

    @Override
    public int countByUserId(Long userId) {
        return userAddrMapper.countByUserId(userId);
    }

    @Override
    @CacheEvict(cacheNames = UserCacheNames.USER_DEFAULT_ADDR, key = "#userId")
    public void removeUserDefaultAddrCacheByUserId(Long userId) {

    }
}
