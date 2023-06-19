package org.cuit.fhzheng.user.feign;

import org.cuit.fhzheng.api.user.feign.UserAddrFeignClient;
import org.cuit.fhzheng.common.order.vo.UserAddrVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.user.service.UserAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户地址feign连接
 * @author
 * @date 2020/12/07
 */
@RestController
public class UserAddrFeignController implements UserAddrFeignClient {

    @Autowired
    private UserAddrService userAddrService;

    @Override
    public ServerResponseEntity<UserAddrVO> getUserAddrByAddrId(Long addrId) {
        return ServerResponseEntity.success(userAddrService.getUserAddrByUserId(addrId,AuthUserContext.get().getUserId()));
    }
}
