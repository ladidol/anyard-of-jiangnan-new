package org.cuit.fhzheng.api.rbac.feign;

import org.cuit.fhzheng.api.rbac.dto.ClearUserPermissionsCacheDTO;
import org.cuit.fhzheng.common.constant.Auth;
import org.cuit.fhzheng.common.feign.FeignInsideAuthConfig;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author
 * @date 2020/09/02
 */
@FeignClient(value = PermissionFeignClient.SERVICE_NAME,contextId = "permission")
public interface PermissionFeignClient {

	String SERVICE_NAME = "anyard-of-jiangnan-rbac";

	/**
	 * 校验是否有某个uri的权限
	 * @param userId
	 * @param sysType
	 * @param uri
	 * @param isAdmin
	 * @param method
	 * @return 是否有某个uri的权限
	 */
	@GetMapping(value = Auth.CHECK_RBAC_URI)
	ServerResponseEntity<Boolean> checkPermission(@RequestParam("userId") Long userId,@RequestParam("sysType") Integer sysType,
												  @RequestParam("uri") String uri,@RequestParam("isAdmin") Integer isAdmin,
												  @RequestParam("method") Integer method);

	/**
	 * 清除用户权限缓存
	 * @param clearUserPermissionsCacheDTO
	 * @return 是否调用成功
	 */
	@PostMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/permission/clearUserPermissionsCache")
	ServerResponseEntity<Void> clearUserPermissionsCache(@RequestBody ClearUserPermissionsCacheDTO clearUserPermissionsCacheDTO);

}
