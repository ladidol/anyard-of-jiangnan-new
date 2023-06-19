package org.cuit.fhzheng.auth.feign;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.api.auth.feign.TokenFeignClient;
import org.cuit.fhzheng.auth.manager.TokenStore;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2020/7/15
 */
@RestController
public class TokenFeignController implements TokenFeignClient {

	private static final Logger logger = LoggerFactory.getLogger(TokenFeignController.class);

	@Autowired
	private TokenStore tokenStore;

	@Override
	public ServerResponseEntity<UserInfoInTokenBO> checkToken(String accessToken) {
		ServerResponseEntity<UserInfoInTokenBO> userInfoByAccessTokenResponse = tokenStore
				.getUserInfoByAccessToken(accessToken, true);
		if (!userInfoByAccessTokenResponse.isSuccess()) {
			return ServerResponseEntity.transform(userInfoByAccessTokenResponse);
		}
		return ServerResponseEntity.success(userInfoByAccessTokenResponse.getData());
	}

}
