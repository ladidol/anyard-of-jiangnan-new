package org.cuit.fhzheng.order.config;

import org.cuit.fhzheng.common.cache.adapter.CacheTtlAdapter;
import org.cuit.fhzheng.common.cache.bo.CacheNameWithTtlBO;
import org.cuit.fhzheng.common.cache.constant.OrderCacheNames;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/12/18
 */
@Component
public class OrderCacheTtlAdapter implements CacheTtlAdapter {
    @Override
    public List<CacheNameWithTtlBO> listCacheNameWithTtl() {
        List<CacheNameWithTtlBO> cacheNameWithTtls = new ArrayList<>();
        // 确认订单缓存30分钟
        cacheNameWithTtls.add(new CacheNameWithTtlBO(OrderCacheNames.ORDER_CONFIRM_UUID_KEY, 60 * 30));
        cacheNameWithTtls.add(new CacheNameWithTtlBO(OrderCacheNames.ORDER_CONFIRM_KEY, 60 * 30));
        return cacheNameWithTtls;
    }
}
