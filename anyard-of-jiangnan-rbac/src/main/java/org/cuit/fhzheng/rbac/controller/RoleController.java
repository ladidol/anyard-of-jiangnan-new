package org.cuit.fhzheng.rbac.controller;

import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.rbac.model.Role;
import org.cuit.fhzheng.rbac.service.RoleService;
import org.cuit.fhzheng.rbac.vo.RoleVO;
import org.cuit.fhzheng.rbac.dto.RoleDTO;
import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 角色
 *
 * @author
 * @date 2020-09-17 19:15:44
 */
@RestController
@RequestMapping("/role")
@Tag(name = "角色")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
	private MapperFacade mapperFacade;

	@GetMapping("/page")
	@Operation(summary = "分页获取角色列表" , description = "分页获取角色列表")
	public ServerResponseEntity<PageVO<RoleVO>> page(@Valid PageDTO pageDTO) {
        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<RoleVO> rolePage = roleService.page(pageDTO, userInfoInTokenBO.getSysType(), userInfoInTokenBO.getTenantId());
		return ServerResponseEntity.success(rolePage);
	}

    @GetMapping("/list")
    @Operation(summary = "获取角色列表" , description = "分页获取角色列表")
    public ServerResponseEntity<List<RoleVO>> list() {
        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
        return ServerResponseEntity.success(roleService.list(userInfoInTokenBO.getSysType(), userInfoInTokenBO.getTenantId()));
    }

	@GetMapping
    @Operation(summary = "获取角色" , description = "根据roleId获取角色")
    public ServerResponseEntity<RoleVO> getByRoleId(@RequestParam Long roleId) {
        return ServerResponseEntity.success(roleService.getByRoleId(roleId));
    }

    @PostMapping
    @Operation(summary = "保存角色" , description = "保存角色")
    public ServerResponseEntity<Void> save(@Valid @RequestBody RoleDTO roleDTO) {
        Role role = mapperFacade.map(roleDTO, Role.class);
        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
        role.setBizType(userInfoInTokenBO.getSysType());
        role.setRoleId(null);
        role.setCreateUserId(userInfoInTokenBO.getUserId());
        role.setTenantId(userInfoInTokenBO.getTenantId());
        roleService.save(role, roleDTO.getMenuIds(), roleDTO.getMenuPermissionIds());
        return ServerResponseEntity.success();
    }

    @PutMapping
    @Operation(summary = "更新角色" , description = "更新角色")
    public ServerResponseEntity<Void> update(@Valid @RequestBody RoleDTO roleDTO) {

        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();


        RoleVO dbRole = roleService.getByRoleId(roleDTO.getRoleId());

        if (!Objects.equals(dbRole.getBizType(), userInfoInTokenBO.getSysType()) || !Objects.equals(dbRole.getTenantId(), userInfoInTokenBO.getTenantId())) {
            return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
        }
        Role role = mapperFacade.map(roleDTO, Role.class);
        role.setBizType(userInfoInTokenBO.getSysType());

        roleService.update(role, roleDTO.getMenuIds(), roleDTO.getMenuPermissionIds());
        return ServerResponseEntity.success();
    }

    @DeleteMapping
    @Operation(summary = "删除角色" , description = "根据角色id删除角色")
    public ServerResponseEntity<Void> delete(@RequestParam Long roleId) {
        UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();

        RoleVO dbRole = roleService.getByRoleId(roleId);

        if (!Objects.equals(dbRole.getBizType(), userInfoInTokenBO.getSysType()) || !Objects.equals(dbRole.getTenantId(), userInfoInTokenBO.getTenantId())) {
            return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
        }
        roleService.deleteById(roleId,userInfoInTokenBO.getSysType());
        return ServerResponseEntity.success();
    }
}
