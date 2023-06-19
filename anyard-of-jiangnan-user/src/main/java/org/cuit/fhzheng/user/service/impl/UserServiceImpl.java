package org.cuit.fhzheng.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.cuit.fhzheng.api.auth.constant.SysTypeEnum;
import org.cuit.fhzheng.api.auth.dto.AuthAccountDTO;
import org.cuit.fhzheng.api.auth.feign.AccountFeignClient;
import org.cuit.fhzheng.api.auth.vo.AuthAccountVO;
import org.cuit.fhzheng.api.leaf.feign.SegmentFeignClient;
import org.cuit.fhzheng.api.user.vo.UserApiVO;
import org.cuit.fhzheng.common.cache.constant.UserCacheNames;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.util.PageUtil;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.util.IpHelper;
import org.cuit.fhzheng.user.dto.UserRegisterDTO;
import org.cuit.fhzheng.user.model.User;
import org.cuit.fhzheng.user.mapper.UserMapper;
import org.cuit.fhzheng.user.service.UserService;
import io.seata.spring.annotation.GlobalTransactional;
import ma.glasnost.orika.MapperFacade;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 用户表
 *
 * @author YXF
 * @date 2020-12-08 11:18:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccountFeignClient accountFeignClient;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private SegmentFeignClient segmentFeignClient;

    @Override
    public PageVO<UserApiVO> page(PageDTO pageDTO) {
        return PageUtil.doPage(pageDTO, () -> userMapper.list());
    }

    @Override
    @Cacheable(cacheNames = UserCacheNames.USER_INFO, key = "#userId")
    public UserApiVO getByUserId(Long userId) {
        return userMapper.getByUserId(userId);
    }

    @Override
    @CacheEvict(cacheNames = UserCacheNames.USER_INFO, key = "#user.userId")
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<UserApiVO> getUserByUserIds(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return new ArrayList<>();
        }
        return userMapper.getUserByUserIds(userIds);
    }

    @Override
    public UserApiVO getUserAndOpenIdsByUserId(Long userId) {
        return userMapper.getUserAndOpenIdsByUserId(userId);
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Long save(UserRegisterDTO param) {
        this.checkRegisterInfo(param);

        ServerResponseEntity<Long> segmentIdResponse = segmentFeignClient.getSegmentId(User.DISTRIBUTED_ID_KEY);
        if (!segmentIdResponse.isSuccess()) {
            throw new anyardOfJiangnanException(ResponseEnum.EXCEPTION);
        }
        Long userId = segmentIdResponse.getData();

        param.setUserId(userId);

        AuthAccountDTO authAccountDTO = new AuthAccountDTO();
        authAccountDTO.setCreateIp(IpHelper.getIpAddr());
        authAccountDTO.setPassword(param.getPassword());
        authAccountDTO.setIsAdmin(0);
        authAccountDTO.setSysType(SysTypeEnum.ORDINARY.value());
        authAccountDTO.setUsername(param.getUserName());
        authAccountDTO.setStatus(1);
        authAccountDTO.setUserId(userId);

        // 保存统一账户信息
        ServerResponseEntity<Long> serverResponse = accountFeignClient.save(authAccountDTO);
        // 抛异常回滚
        if (!serverResponse.isSuccess()) {
            throw new anyardOfJiangnanException(serverResponse.getMsg());
        }

        User user = new User();
        user.setUserId(userId);
        user.setPic(param.getImg());
        user.setNickName(param.getNickName());
        user.setStatus(1);
        // 这里保存之后才有用户id
        userMapper.save(user);

        return serverResponse.getData();
    }

    private void checkRegisterInfo(UserRegisterDTO userRegisterDTO) {
        ServerResponseEntity<AuthAccountVO> responseEntity = accountFeignClient.getByUsernameAndSysType(userRegisterDTO.getUserName(), SysTypeEnum.ORDINARY);
        if (!responseEntity.isSuccess()) {
            throw new anyardOfJiangnanException(responseEntity.getMsg());
        }
        if (Objects.nonNull(responseEntity.getData())) {
            throw new anyardOfJiangnanException("用户名已存在");
        }
    }

}
