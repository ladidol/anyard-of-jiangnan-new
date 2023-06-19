package org.cuit.fhzheng.rbac.service;

import org.cuit.fhzheng.api.rbac.bo.UriPermissionBO;

import java.util.List;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.rbac.model.MenuPermission;
import org.cuit.fhzheng.rbac.vo.MenuPermissionVO;

/**
 * 菜单资源
 *
 * @author
 * @date 2020-09-14 16:27:55
 */
public interface MenuPermissionService {

	/**
	 * 分页获取菜单资源列表
	 * @param pageDTO 分页参数
	 * @param sysType 系统类型
	 * @return 菜单资源列表分页数据
	 */
	PageVO<MenuPermissionVO> page(PageDTO pageDTO,Integer sysType);

	/**
	 * 根据菜单资源id获取菜单资源
	 *
	 * @param menuPermissionId 菜单资源id
	 * @return 菜单资源
	 */
	MenuPermissionVO getByMenuPermissionId(Long menuPermissionId);

	/**
	 * 保存菜单资源
	 * @param menuPermission 菜单资源
	 * @return
	 */
	ServerResponseEntity<Void> save(MenuPermission menuPermission);

	/**
	 * 更新菜单资源
	 * @param menuPermission 菜单资源
	 * @return
	 */
	ServerResponseEntity<Void> update(MenuPermission menuPermission);

	/**
	 * 根据菜单资源id删除菜单资源
	 * @param menuPermissionId
	 * @param sysType
	 */
	void deleteById(Long menuPermissionId, Integer sysType);

	/**
	 * 根据用户所在系统的用户id，用户所在系统类型，获取用户的权限列表
	 * @param userId 用户id
	 * @param sysType 系统类型
	 * @param isAdmin 是否为管理员
	 * @return 权限列表
	 */
	List<String> listUserPermissions(Long userId, Integer sysType, boolean isAdmin);

	/**
	 * 清除用户权限缓存
	 * @param userId 用户id
	 * @param sysType 系统类型
	 */
	void clearUserPermissionsCache(Long userId, Integer sysType);

	/**
	 * 根据系统类型，获取该类型用户拥有的所有权限数据
	 * @param sysType 系统类型
	 * @return uri权限列表
	 */
	List<UriPermissionBO> listUriPermissionInfo(Integer sysType);

	/**
	 * 根据menuId获取菜单资源列表
	 * @param menuId 菜单id
	 * @return 菜单资源列表数据
	 */
	List<MenuPermissionVO> listByMenuId(Long menuId);

}
