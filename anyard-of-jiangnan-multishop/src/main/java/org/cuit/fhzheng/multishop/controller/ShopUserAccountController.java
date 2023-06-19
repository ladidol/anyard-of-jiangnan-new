package org.cuit.fhzheng.multishop.controller;

import org.cuit.fhzheng.api.auth.vo.AuthAccountVO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.multishop.dto.ChangeAccountDTO;
import org.cuit.fhzheng.multishop.service.ShopUserAccountService;
import org.cuit.fhzheng.multishop.service.ShopUserService;
import org.cuit.fhzheng.multishop.vo.ShopUserVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Objects;

/**
 * @author
 * @date 2020/09/02
 */
@RequestMapping(value = "/shop_user/account")
@RestController
@Tag(name = "店铺用户账号信息")
public class ShopUserAccountController {

	private final ShopUserAccountService shopUserAccountService;

	private final ShopUserService shopUserService;

	public ShopUserAccountController(ShopUserAccountService shopUserAccountService, ShopUserService shopUserService) {
		this.shopUserAccountService = shopUserAccountService;
		this.shopUserService = shopUserService;
	}


	@GetMapping
	@Operation(summary = "获取账号信息" , description = "获取账号信息")
	public ServerResponseEntity<AuthAccountVO> getAccount(Long shopUserId) {
		return shopUserAccountService.getByUserIdAndSysType(shopUserId, AuthUserContext.get().getSysType());
	}


	@PostMapping
	@Operation(summary = "添加账号" , description = "添加账号")
	public ServerResponseEntity<Void> addAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
		ShopUserVO shopUserVO = shopUserService.getByUserId(changeAccountDTO.getUserId());
		if (shopUserVO == null) {
			return ServerResponseEntity.showFailMsg("无法获取账户信息");
		}
		if (Objects.equals(shopUserVO.getHasAccount(), 1)) {
			return ServerResponseEntity.showFailMsg("已有账号，无需重复添加");
		}
		if (!Objects.equals(shopUserVO.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		return shopUserAccountService.save(changeAccountDTO);
	}

	@PutMapping
	@Operation(summary = "修改账号" , description = "修改账号")
	public ServerResponseEntity<Void> updateAccount(@Valid @RequestBody ChangeAccountDTO changeAccountDTO) {
		ShopUserVO shopUserVO = shopUserService.getByUserId(changeAccountDTO.getUserId());
		if (shopUserVO == null || Objects.equals(shopUserVO.getHasAccount(), 0)) {
			return ServerResponseEntity.showFailMsg("无法获取账户信息");
		}
		if (!Objects.equals(shopUserVO.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		return shopUserAccountService.update(changeAccountDTO);
	}
}
