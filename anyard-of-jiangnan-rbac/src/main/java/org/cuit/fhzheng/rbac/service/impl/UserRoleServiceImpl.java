package org.cuit.fhzheng.rbac.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.cuit.fhzheng.rbac.mapper.UserRoleMapper;
import org.cuit.fhzheng.rbac.service.UserRoleService;

/**
 * @author
 * @date 2020/6/23
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Resource
	private UserRoleMapper userRoleMapper;

}
