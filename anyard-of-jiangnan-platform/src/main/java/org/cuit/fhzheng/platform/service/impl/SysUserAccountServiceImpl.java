package org.cuit.fhzheng.platform.service.impl;

import org.cuit.fhzheng.api.auth.dto.AuthAccountDTO;
import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.api.auth.feign.AccountFeignClient;
import org.cuit.fhzheng.api.auth.vo.AuthAccountVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.common.util.IpHelper;
import org.cuit.fhzheng.platform.dto.ChangeAccountDTO;
import org.cuit.fhzheng.platform.mapper.SysUserMapper;
import org.cuit.fhzheng.platform.model.SysUser;
import org.cuit.fhzheng.platform.service.SysUserAccountService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lhd
 * @date 2020/12/22
 */
@Service
public class SysUserAccountServiceImpl implements SysUserAccountService {

	@Resource
	private SysUserMapper sysUserMapper;
	@Autowired
	private AccountFeignClient accountFeignClient;

    @Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
    public ServerResponseEntity<Void> save(ChangeAccountDTO changeAccountDTO) {
		AuthAccountDTO authAccountDTO = getAuthAccountDTO(changeAccountDTO);
		authAccountDTO.setCreateIp(IpHelper.getIpAddr());
		authAccountDTO.setIsAdmin(0);
		// 保存
		ServerResponseEntity<Long> serverResponseEntity = accountFeignClient.save(authAccountDTO);
		if (!serverResponseEntity.isSuccess()) {
			return ServerResponseEntity.transform(serverResponseEntity);
		}
		SysUser sysUser = new SysUser();
		sysUser.setSysUserId(changeAccountDTO.getUserId());
		sysUser.setHasAccount(1);
		sysUserMapper.update(sysUser);
		return ServerResponseEntity.success();
    }

	@Override
	public ServerResponseEntity<Void> update(ChangeAccountDTO changeAccountDTO) {

		AuthAccountDTO authAccountDTO = getAuthAccountDTO(changeAccountDTO);
		// 更新，不涉及分布式事务
		ServerResponseEntity<Void> serverResponseEntity = accountFeignClient.update(authAccountDTO);
		if (!serverResponseEntity.isSuccess()) {
			return serverResponseEntity;
		}

		return ServerResponseEntity.success();
	}

	@Override
	public ServerResponseEntity<AuthAccountVO> getByUserIdAndSysType(Long userId, Integer sysType) {
		return accountFeignClient.getByUserIdAndSysType(userId,sysType);
	}

	private AuthAccountDTO getAuthAccountDTO(ChangeAccountDTO changeAccountDTO) {
		AuthAccountDTO authAccountDTO = new AuthAccountDTO();
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		authAccountDTO.setPassword(changeAccountDTO.getPassword());
		authAccountDTO.setUsername(changeAccountDTO.getUsername());
		authAccountDTO.setStatus(changeAccountDTO.getStatus());
		authAccountDTO.setSysType(userInfoInTokenBO.getSysType());
		authAccountDTO.setTenantId(userInfoInTokenBO.getTenantId());
		authAccountDTO.setUserId(changeAccountDTO.getUserId());
		return authAccountDTO;
	}

}
