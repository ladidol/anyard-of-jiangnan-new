package org.cuit.fhzheng.multishop.service;

import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.multishop.model.ShopUser;
import org.cuit.fhzheng.multishop.vo.ShopUserVO;

import java.util.List;

/**
 * @author
 * @date 2020/09/03
 */
public interface ShopUserService {

	/**
	 * 分页获取店铺用户列表
	 * @param pageDTO 分页参数
	 * @param shopId 店铺id
	 * @param nickName 昵称
	 * @return 店铺用户列表
	 */
	PageVO<ShopUserVO> pageByShopId(PageDTO pageDTO, Long shopId, String nickName);

	/**
	 * 根据用户id获取商家用户信息
	 *
	 * @param userId 用户id
	 * @return 商家用户信息
	 */
	ShopUserVO getByUserId(Long userId);

	/**
	 * 保存店铺用户信息
	 * @param shopUser 店铺用户id
	 * @param roleIds 角色id列表
	 */
	void save(ShopUser shopUser, List<Long> roleIds);

	/**
	 * 更新店铺用户信息
	 * @param shopUser 店铺用户id
	 * @param roleIds 角色id列表
	 */
	void update(ShopUser shopUser,List<Long> roleIds);

	/**
	 * 根据店铺用户id删除店铺用户信息
	 * @param shopUserId 店铺用户id
	 */
	void deleteById(Long shopUserId);

	/**
	 * 获取店主账号的用户id-第一个创建的账号（仅用于审核店铺）
	 * @param shopId
	 * @return
	 */
	Long getUserIdByShopId(Long shopId);
}
