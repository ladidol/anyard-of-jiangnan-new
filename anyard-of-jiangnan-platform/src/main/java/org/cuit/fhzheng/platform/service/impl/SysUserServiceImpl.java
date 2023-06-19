package org.cuit.fhzheng.platform.service.impl;

import org.cuit.fhzheng.platform.model.SysUser;
import org.cuit.fhzheng.platform.vo.SysUserVO;
import org.springframework.cache.annotation.Cacheable;
import org.cuit.fhzheng.api.auth.feign.AccountFeignClient;
import org.cuit.fhzheng.api.rbac.dto.UserRoleDTO;
import org.cuit.fhzheng.api.rbac.feign.UserRoleFeignClient;
import org.cuit.fhzheng.common.cache.constant.CacheNames;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.util.PageUtil;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.platform.mapper.SysUserMapper;
import org.cuit.fhzheng.platform.service.SysUserService;
import org.cuit.fhzheng.platform.vo.SysUserSimpleVO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lhd
 * @date 2020/12/22
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserMapper sysUserMapper;
	@Autowired
	private AccountFeignClient accountFeignClient;
	@Autowired
	private UserRoleFeignClient userRoleFeignClient;



	@Override
	@Cacheable(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#userId")
	public SysUserSimpleVO getSimpleByUserId(Long userId) {
		return sysUserMapper.getSimpleByUserId(userId);
	}

	@Override
	public PageVO<SysUserVO> pageByShopId(PageDTO pageDTO, Long shopId, String nickName) {
		return PageUtil.doPage(pageDTO, () -> sysUserMapper.listByShopId(shopId, nickName));
	}

	@Override
	public SysUserVO getByUserId(Long userId) {
		SysUserVO sysUser = sysUserMapper.getByUserId(userId);
		ServerResponseEntity<List<Long>> roleIds = userRoleFeignClient.getRoleIds(sysUser.getSysUserId());
		sysUser.setRoleIds(roleIds.getData());
		return sysUser;
	}

    @Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
    public void save(SysUser sysUser, List<Long> roleIds) {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRoleIds(roleIds);
		sysUserMapper.save(sysUser);
		userRoleDTO.setUserId(sysUser.getSysUserId());
		userRoleFeignClient.saveByUserIdAndSysType(userRoleDTO);
	}

	@Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#sysUser.sysUserId")
	public void update(SysUser sysUser, List<Long> roleIds) {
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setRoleIds(roleIds);
		userRoleDTO.setUserId(sysUser.getSysUserId());
		sysUserMapper.update(sysUser);
		userRoleFeignClient.updateByUserIdAndSysType(userRoleDTO);
	}

	@Override
	@GlobalTransactional(rollbackFor = Exception.class)
	@Transactional(rollbackFor = Exception.class)
	@CacheEvict(cacheNames = CacheNames.PLATFORM_SIMPLE_INFO_KEY, key = "#sysUserId")
	public void deleteById(Long sysUserId) {
		accountFeignClient.deleteByUserIdAndSysType(sysUserId);
		userRoleFeignClient.deleteByUserIdAndSysType(sysUserId);
		sysUserMapper.deleteById(sysUserId);
	}

}