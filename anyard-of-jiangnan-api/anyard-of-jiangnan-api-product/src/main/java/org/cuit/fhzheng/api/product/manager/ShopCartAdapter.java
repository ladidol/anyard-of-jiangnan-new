package org.cuit.fhzheng.api.product.manager;

import cn.hutool.core.collection.CollectionUtil;
import org.cuit.fhzheng.api.multishop.feign.ShopDetailFeignClient;
import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.api.product.dto.ShopCartItemDTO;
import org.cuit.fhzheng.api.product.feign.ShopCartFeignClient;
import org.cuit.fhzheng.api.product.feign.SpuFeignClient;
import org.cuit.fhzheng.common.order.vo.ShopCartItemVO;
import org.cuit.fhzheng.api.product.vo.SkuVO;
import org.cuit.fhzheng.api.product.vo.SpuAndSkuVO;
import org.cuit.fhzheng.api.product.vo.SpuVO;
import org.cuit.fhzheng.common.order.vo.ShopCartVO;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.util.BooleanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 购物车适配器
 * @author
 * @date 2020/12/07
 */
@Component
public class ShopCartAdapter {

    @Autowired
    private SpuFeignClient spuFeignClient;

    @Autowired
    private ShopCartFeignClient shopCartFeignClient;

    @Autowired
    private ShopDetailFeignClient shopDetailFeignClient;

    /**
     * 获取购物项组装信息
     * @param shopCartItemParam 购物项参数
     * @return 购物项组装信息
     */
    public ServerResponseEntity<List<ShopCartItemVO>> getShopCartItems(ShopCartItemDTO shopCartItemParam) {
        ServerResponseEntity<List<ShopCartItemVO>> shopCartItemResponse;
        // 当立即购买时，没有提交的订单是没有购物车信息的
        if (shopCartItemParam != null) {
            shopCartItemResponse = conversionShopCartItem(shopCartItemParam);
        }
        // 从购物车提交订单
        else {
            shopCartItemResponse = shopCartFeignClient.getCheckedShopCartItems();
        }
        if (!shopCartItemResponse.isSuccess()) {
            return ServerResponseEntity.transform(shopCartItemResponse);
        }
        // 请选择您需要的商品加入购物车
        if (CollectionUtil.isEmpty(shopCartItemResponse.getData())) {
            return ServerResponseEntity.fail(ResponseEnum.SHOP_CART_NOT_EXIST);
        }
        // 返回购物车选择的商品信息
        return shopCartItemResponse;
    }

    /**
     * 将参数转换成组装好的购物项
     * @param shopCartItemParam 购物项参数
     * @return 组装好的购物项
     */
    public ServerResponseEntity<List<ShopCartItemVO>> conversionShopCartItem(ShopCartItemDTO shopCartItemParam){
        ServerResponseEntity<SpuAndSkuVO> spuAndSkuResponse = spuFeignClient.getSpuAndSkuById(shopCartItemParam.getSpuId(),shopCartItemParam.getSkuId());
        if (!spuAndSkuResponse.isSuccess()) {
            return ServerResponseEntity.transform(spuAndSkuResponse);
        }
        SkuVO sku = spuAndSkuResponse.getData().getSku();
        SpuVO spu = spuAndSkuResponse.getData().getSpu();
        // 拿到购物车的所有item
        ShopCartItemVO shopCartItem = new ShopCartItemVO();
        shopCartItem.setCartItemId(0L);
        shopCartItem.setSkuId(shopCartItemParam.getSkuId());
        shopCartItem.setCount(shopCartItemParam.getCount());
        shopCartItem.setSpuId(shopCartItemParam.getSpuId());
        shopCartItem.setSkuName(sku.getSkuName());
        shopCartItem.setSpuName(spu.getName());
        shopCartItem.setImgUrl(BooleanUtil.isTrue(spu.getHasSkuImg()) ? sku.getImgUrl() : spu.getMainImgUrl());
        shopCartItem.setSkuPriceFee(sku.getPriceFee());
        shopCartItem.setTotalAmount(shopCartItem.getCount() * shopCartItem.getSkuPriceFee());
        shopCartItem.setCreateTime(new Date());
        shopCartItem.setShopId(shopCartItemParam.getShopId());
        return ServerResponseEntity.success(Collections.singletonList(shopCartItem));
    }


    /**
     * 将参数转换成组装好的购物项
     * @param shopCartItems 订单参数
     * @return 组装好的购物项
     */
    public List<ShopCartVO> conversionShopCart(List<ShopCartItemVO> shopCartItems){

        // 根据店铺ID划分item
        Map<Long, List<ShopCartItemVO>> shopCartMap = shopCartItems.stream().collect(Collectors.groupingBy(ShopCartItemVO::getShopId));

        // 返回一个店铺的所有信息
        List<ShopCartVO> shopCarts = new ArrayList<>();
        for (Long shopId : shopCartMap.keySet()) {
            // 构建每个店铺的购物车信息
            ShopCartVO shopCart = buildShopCart(shopId,shopCartMap.get(shopId));
            shopCart.setShopId(shopId);
            shopCart.setshopCartItem(shopCartMap.get(shopId));
            // 店铺信息
            ServerResponseEntity<String> shopNameResponse = shopDetailFeignClient.getShopNameByShopId(shopId);
            if (!shopNameResponse.isSuccess()) {
                throw new anyardOfJiangnanException(shopNameResponse.getMsg());
            }
            shopCart.setShopName(shopNameResponse.getData());
            shopCarts.add(shopCart);
        }
        return shopCarts;
    }


    private ShopCartVO buildShopCart(Long shopId, List<ShopCartItemVO> shopCartItems) {
        ShopCartVO shopCart = new ShopCartVO();
        shopCart.setShopId(shopId);
        long total = 0L;
        int totalCount = 0;
        for (ShopCartItemVO shopCartItem : shopCartItems) {
            total += shopCartItem.getTotalAmount();
            totalCount += shopCartItem.getCount();
        }
        shopCart.setTotal(total);
        shopCart.setTotalCount(totalCount);
        return shopCart;
    }


}
