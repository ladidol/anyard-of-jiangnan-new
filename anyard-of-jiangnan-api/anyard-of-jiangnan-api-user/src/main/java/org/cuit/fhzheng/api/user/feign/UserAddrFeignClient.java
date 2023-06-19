package org.cuit.fhzheng.api.user.feign;

import org.cuit.fhzheng.common.feign.FeignInsideAuthConfig;
import org.cuit.fhzheng.common.order.vo.UserAddrVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户地址feign连接
 * @author
 * @date 2020/12/07
 */
@FeignClient(value = "anyard-of-jiangnan-user",contextId = "userAddr")
public interface UserAddrFeignClient {


    /**
     * 根据地址id获取用户地址信息
     * @param addrId 地址id
     * @return 用户地址信息
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/userAddr/getUserAddrByAddrId")
    ServerResponseEntity<UserAddrVO> getUserAddrByAddrId(@RequestParam("addrId") Long addrId);

}
