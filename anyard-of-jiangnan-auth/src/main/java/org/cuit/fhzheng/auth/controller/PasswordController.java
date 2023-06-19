package org.cuit.fhzheng.auth.controller;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.api.auth.vo.TokenInfoVO;
import org.cuit.fhzheng.auth.dto.UpdatePasswordDTO;
import org.cuit.fhzheng.auth.manager.TokenStore;
import org.cuit.fhzheng.auth.model.AuthAccount;
import org.cuit.fhzheng.auth.service.AuthAccountService;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author  
 * @date  2023/01/29
 */
@RestController
@Tag(name = "密码")
public class PasswordController {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private AuthAccountService authAccountService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PutMapping("/update_password")
	@Operation(summary = "更新密码" , description = "更新当前用户的密码, 更新密码之后要退出登录，清理token")
	public ServerResponseEntity<TokenInfoVO> updatePassword(@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
		UserInfoInTokenBO userInfoInToken = AuthUserContext.get();
		AuthAccount authAccount = authAccountService.getByUserIdAndType(userInfoInToken.getUserId(), userInfoInToken.getSysType());
		if (!passwordEncoder.matches(updatePasswordDTO.getOldPassword(), authAccount.getPassword())) {
			return ServerResponseEntity.showFailMsg("旧密码不正确");
		}
		authAccountService.updatePassword(userInfoInToken.getUserId(), userInfoInToken.getSysType(), updatePasswordDTO.getNewPassword());
		tokenStore.deleteAllToken(userInfoInToken.getSysType().toString(), userInfoInToken.getUid());
		return ServerResponseEntity.success();
	}


}
