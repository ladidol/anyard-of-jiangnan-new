package org.cuit.fhzheng.rbac.controller;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.common.util.BooleanUtil;
import org.cuit.fhzheng.rbac.dto.MenuPermissionDTO;
import org.cuit.fhzheng.rbac.model.MenuPermission;
import org.cuit.fhzheng.rbac.service.MenuPermissionService;
import org.cuit.fhzheng.rbac.vo.MenuPermissionVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * @author
 * @date 2020/09/02
 */
@RequestMapping(value = "/menu_permission")
@RestController
@Tag(name = "权限接口")
public class MenuPermissionController {

	@Autowired
	private MenuPermissionService menuPermissionService;

	@Autowired
	private MapperFacade mapperFacade;

	@GetMapping("/list_by_menu")
	@Operation(summary = "获取菜单资源列表" , description = "分页获取菜单资源列表")
	public ServerResponseEntity<List<MenuPermissionVO>> listByMenuId(Long menuId) {
		List<MenuPermissionVO> menuPermissionVOList = menuPermissionService.listByMenuId(menuId);
		return ServerResponseEntity.success(menuPermissionVOList);
	}

	@GetMapping
	@Operation(summary = "获取菜单资源" , description = "根据menuPermissionId获取菜单资源")
	public ServerResponseEntity<MenuPermissionVO> getByMenuPermissionId(@RequestParam Long menuPermissionId) {
		return ServerResponseEntity.success(menuPermissionService.getByMenuPermissionId(menuPermissionId));
	}

	@PostMapping
	@Operation(summary = "保存菜单资源" , description = "保存菜单资源")
	public ServerResponseEntity<Void> save(@Valid @RequestBody MenuPermissionDTO menuPermissionDTO) {
		MenuPermission menuPermission = mapperFacade.map(menuPermissionDTO, MenuPermission.class);
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		menuPermission.setMenuPermissionId(null);
		menuPermission.setBizType(userInfoInTokenBO.getSysType());
		return menuPermissionService.save(menuPermission);
	}

	@PutMapping
	@Operation(summary = "更新菜单资源" , description = "更新菜单资源")
	public ServerResponseEntity<Void> update(@Valid @RequestBody MenuPermissionDTO menuPermissionDTO) {
		MenuPermission menuPermission = mapperFacade.map(menuPermissionDTO, MenuPermission.class);
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		menuPermission.setBizType(userInfoInTokenBO.getSysType());
		return menuPermissionService.update(menuPermission);
	}

	@DeleteMapping
	@Operation(summary = "删除菜单资源" , description = "根据菜单资源id删除菜单资源")
	public ServerResponseEntity<Void> delete(@RequestParam Long menuPermissionId) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		menuPermissionService.deleteById(menuPermissionId,userInfoInTokenBO.getSysType());
		return ServerResponseEntity.success();
	}

	@GetMapping(value = "/list")
	@Operation(summary = "获取当前用户拥有的权限" , description = "当前用户所拥有的所有权限，精确到按钮，实际上element admin里面的roles就可以理解成权限")
	public ServerResponseEntity<List<String>> permissions() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		return ServerResponseEntity.success(menuPermissionService.listUserPermissions(userInfoInTokenBO.getUserId(),
				userInfoInTokenBO.getSysType(), BooleanUtil.isTrue(userInfoInTokenBO.getIsAdmin())));
	}

	@GetMapping(value = "/page")
	@Operation(summary = "获取当前用户拥有的权限" , description = "当前用户所拥有的所有权限，精确到按钮，实际上element admin里面的roles就可以理解成权限")
	public ServerResponseEntity<PageVO<MenuPermissionVO>> pagePermissions(PageDTO pageDTO) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<MenuPermissionVO> permissionPage = menuPermissionService.page(pageDTO, userInfoInTokenBO.getSysType());
		return ServerResponseEntity.success(permissionPage);
	}

}
