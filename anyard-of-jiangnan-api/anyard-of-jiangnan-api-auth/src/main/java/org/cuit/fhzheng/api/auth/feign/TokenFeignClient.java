package org.cuit.fhzheng.api.auth.feign;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.common.constant.Auth;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "anyard-of-jiangnan-auth",contextId ="token")
public interface TokenFeignClient {

	/**
	 * 校验token并返回token保存的用户信息
	 * @param accessToken accessToken
	 * @return token保存的用户信息
	 */
	@GetMapping(value = Auth.CHECK_TOKEN_URI)
	ServerResponseEntity<UserInfoInTokenBO> checkToken(@RequestParam("accessToken") String accessToken);

}
