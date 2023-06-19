package org.cuit.fhzheng.multishop.controller.platform;

import org.cuit.fhzheng.api.multishop.vo.ShopDetailVO;
import org.cuit.fhzheng.common.constant.Constant;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.exception.anyardOfJiangnanException;
import org.cuit.fhzheng.common.response.ResponseEnum;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.multishop.dto.ShopDetailDTO;
import org.cuit.fhzheng.multishop.model.ShopDetail;
import org.cuit.fhzheng.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 店铺详情
 *
 * @author  
 * @date 2020-12-05 15:50:25
 */
@RestController("platformShopDetailController")
@RequestMapping("/platform/shop_detail")
@Tag(name = "platform-店铺信息")
public class ShopDetailController {

    @Autowired
    private ShopDetailService shopDetailService;

    @Autowired
    private MapperFacade mapperFacade;

    @GetMapping("/page")
    @Operation(summary = "分页查询" , description = "分页查询")
    public ServerResponseEntity<PageVO<ShopDetailVO>> getShopAuditingPage(PageDTO pageDTO, ShopDetailDTO shopDetailDTO) {
        if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())) {
            throw new anyardOfJiangnanException(ResponseEnum.UNAUTHORIZED);
        }
        return ServerResponseEntity.success(shopDetailService.page(pageDTO, shopDetailDTO));
    }

    @GetMapping("/info")
    @Operation(summary = "店铺详情" , description = "店铺详情")
    public ServerResponseEntity<ShopDetailVO> getInfo(@RequestParam Long shopId) {
        ShopDetailVO shopDetailVO = shopDetailService.getByShopId(shopId);
        return ServerResponseEntity.success(shopDetailVO);
    }

    /**
     * 新建店铺
     */
    @PostMapping("/create_shop")
    @Operation(summary = "新建店铺" , description = "新建店铺")
    public ServerResponseEntity<Void> createShop(@RequestBody ShopDetailDTO shopDetailDTO) {
        shopDetailService.createShop(shopDetailDTO);
        return ServerResponseEntity.success();
    }

    @PutMapping("/update_shop")
    @Operation(summary = "更新店铺" , description = "更新店铺")
    public ServerResponseEntity<Void> updateShop(@RequestBody ShopDetailDTO shopDetailDTO) {
        shopDetailService.update(mapperFacade.map(shopDetailDTO, ShopDetail.class));
        return ServerResponseEntity.success();
    }
}
