package org.cuit.fhzheng.rbac.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.cuit.fhzheng.rbac.mapper.RoleMenuMapper;
import org.cuit.fhzheng.rbac.service.RoleMenuService;

/**
 * @author
 * @date 2020/6/23
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Resource
	private RoleMenuMapper roleMenuMapper;

}
