package org.cuit.fhzheng.product.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import org.cuit.fhzheng.common.cache.constant.CacheNames;
import org.cuit.fhzheng.common.cache.util.CacheManagerUtil;
import org.cuit.fhzheng.common.order.vo.ShopCartItemVO;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.product.dto.shopcart.ChangeShopCartItemDTO;
import org.cuit.fhzheng.product.dto.shopcart.CheckShopCartItemDTO;
import org.cuit.fhzheng.product.mapper.ShopCartItemMapper;
import org.cuit.fhzheng.product.model.ShopCartItem;
import org.cuit.fhzheng.product.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车
 *
 * @author
 * @date 2020-11-20 15:47:32
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShopCartItemMapper shopCartItemMapper;

    @Autowired
    private CacheManagerUtil cacheManagerUtil;

    @Override
    @CacheEvict(cacheNames = CacheNames.SHOP_CART_ITEM_COUNT, key = "#userId")
    public void deleteShopCartItemsByShopCartItemIds(Long userId,List<Long> shopCartItemIds) {
        shopCartItemMapper.deleteShopCartItemsByShopCartItemIds(userId, shopCartItemIds);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.SHOP_CART_ITEM_COUNT, key = "#userId")
    public void addShopCartItem(Long userId, ChangeShopCartItemDTO param, Long priceFee) {
        ShopCartItem shopCartItem = new ShopCartItem();
        shopCartItem.setCount(param.getCount());
        shopCartItem.setSpuId(param.getSpuId());
        shopCartItem.setShopId(param.getShopId());
        shopCartItem.setUserId(userId);
        shopCartItem.setSkuId(param.getSkuId());
        shopCartItem.setIsChecked(1);
        shopCartItem.setPriceFee(priceFee);
        shopCartItemMapper.save(shopCartItem);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.SHOP_CART_ITEM_COUNT, key = "#userId")
    public void updateShopCartItem(Long userId,ShopCartItem shopCartItem) {
        shopCartItemMapper.update(shopCartItem);
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.SHOP_CART_ITEM_COUNT, key = "#userId")
    public void deleteAllShopCartItems(Long userId) {
        shopCartItemMapper.deleteAllShopCartItems(userId);
    }

    @Override
    public List<ShopCartItemVO> getShopCartItems() {
        Long userId = AuthUserContext.get().getUserId();
        List<ShopCartItemVO> shopCartItems = shopCartItemMapper.getShopCartItems(userId, false, null);
        for (ShopCartItemVO shopCartItem : shopCartItems) {
            shopCartItem.setTotalAmount(shopCartItem.getCount() * shopCartItem.getSkuPriceFee());
        }
        return shopCartItems;
    }

    @Override
    public List<ShopCartItemVO> getShopCartExpiryItems() {
        Long userId = AuthUserContext.get().getUserId();
        List<ShopCartItemVO> shopCartItems = shopCartItemMapper.getShopCartItems(userId, true, null);
        for (ShopCartItemVO shopCartItem : shopCartItems) {
            shopCartItem.setTotalAmount(shopCartItem.getCount() * shopCartItem.getSkuPriceFee());
        }
        return shopCartItems;
    }

    @Override
    @Cacheable(cacheNames = CacheNames.SHOP_CART_ITEM_COUNT, key = "#userId")
    public Integer getShopCartItemCount(Long userId) {
        return shopCartItemMapper.getShopCartItemCount(userId);
    }

    @Override
    public List<ShopCartItemVO> getCheckedShopCartItems() {
        Long userId = AuthUserContext.get().getUserId();
        return shopCartItemMapper.getShopCartItems(userId, false, true);
    }

    @Override
    public void removeShopCartItemCache(Long spuId) {
        List<String> userIds = shopCartItemMapper.listUserIdBySpuId(spuId);
        if (CollectionUtil.isEmpty(userIds)) {
            return;
        }
        for (String userId : userIds) {
            cacheManagerUtil.evictCache(CacheNames.SHOP_CART_ITEM_COUNT, userId);
        }
    }

    @Override
    public void checkShopCartItems(Long userId, List<CheckShopCartItemDTO> params) {
        shopCartItemMapper.checkShopCartItems(userId, params);
    }

}
