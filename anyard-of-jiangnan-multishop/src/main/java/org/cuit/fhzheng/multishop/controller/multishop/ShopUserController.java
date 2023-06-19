package org.cuit.fhzheng.multishop.controller.multishop;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;
import org.cuit.fhzheng.api.multishop.vo.ShopDetailVO;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.multishop.dto.ShopUserDTO;
import org.cuit.fhzheng.multishop.model.ShopUser;
import org.cuit.fhzheng.multishop.service.ShopDetailService;
import org.cuit.fhzheng.multishop.service.ShopUserService;
import org.cuit.fhzheng.multishop.vo.ShopUserVO;
import org.cuit.fhzheng.multishop.vo.ShopUserSimpleVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author
 * @date 2020/09/02
 */
@RequestMapping(value = "/m/shop_user")
@RestController("multishopShopUserController")
@Tag(name = "店铺用户信息")
public class ShopUserController {

	@Autowired
	private ShopUserService shopUserService;

	@Autowired
	private ShopDetailService shopDetailService;

	@Autowired
	private MapperFacade mapperFacade;

	@GetMapping("/info")
	@Operation(summary = "登陆店铺用户信息" , description = "获取当前登陆店铺用户的用户信息")
	public ServerResponseEntity<ShopUserSimpleVO> info() {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		ShopUserSimpleVO shopUserSimple = new ShopUserSimpleVO();
		shopUserSimple.setIsAdmin(userInfoInTokenBO.getIsAdmin());
		ShopDetailVO shopDetail = shopDetailService.getByShopId(userInfoInTokenBO.getTenantId());
		shopUserSimple.setAvatar(shopDetail.getShopLogo());
		shopUserSimple.setNickName(shopDetail.getShopName());
		return ServerResponseEntity.success(shopUserSimple);
	}

	@GetMapping("/page")
	@Operation(summary = "店铺用户列表" , description = "获取店铺用户列表")
	public ServerResponseEntity<PageVO<ShopUserVO>> page(@Valid PageDTO pageDTO, String nickName) {
		UserInfoInTokenBO userInfoInTokenBO = AuthUserContext.get();
		PageVO<ShopUserVO> shopUserPage = shopUserService.pageByShopId(pageDTO, userInfoInTokenBO.getTenantId(), nickName);
		return ServerResponseEntity.success(shopUserPage);
	}

	@GetMapping
	@Operation(summary = "获取店铺用户信息" , description = "根据用户id获取店铺用户信息")
	public ServerResponseEntity<ShopUserVO> detail(@RequestParam Long shopUserId) {
		return ServerResponseEntity.success(shopUserService.getByUserId(shopUserId));
	}

	@PostMapping
	@Operation(summary = "保存店铺用户信息" , description = "保存店铺用户信息")
	public ServerResponseEntity<Void> save(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = mapperFacade.map(shopUserDTO, ShopUser.class);
		shopUser.setShopUserId(null);
		shopUser.setShopId(AuthUserContext.get().getTenantId());
		shopUser.setHasAccount(0);
		shopUserService.save(shopUser,shopUserDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@PutMapping
	@Operation(summary = "更新店铺用户信息" , description = "更新店铺用户信息")
	public ServerResponseEntity<Void> update(@Valid @RequestBody ShopUserDTO shopUserDTO) {
		ShopUser shopUser = mapperFacade.map(shopUserDTO, ShopUser.class);
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserDTO.getShopUserId());
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		shopUser.setShopId(dbShopUser.getShopId());
		shopUserService.update(shopUser,shopUserDTO.getRoleIds());
		return ServerResponseEntity.success();
	}

	@DeleteMapping
	@Operation(summary = "删除店铺用户信息" , description = "根据店铺用户id删除店铺用户信息")
	public ServerResponseEntity<Void> delete(@RequestParam Long shopUserId) {
		ShopUserVO dbShopUser = shopUserService.getByUserId(shopUserId);
		if (!Objects.equals(dbShopUser.getShopId(), AuthUserContext.get().getTenantId())) {
			return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
		}
		shopUserService.deleteById(shopUserId);
		return ServerResponseEntity.success();
	}
}
