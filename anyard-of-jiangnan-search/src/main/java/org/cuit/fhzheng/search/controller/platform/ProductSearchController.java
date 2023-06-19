package org.cuit.fhzheng.search.controller.platform;

import org.cuit.fhzheng.api.dto.EsPageDTO;
import org.cuit.fhzheng.api.dto.ProductSearchDTO;
import org.cuit.fhzheng.api.vo.EsPageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.search.constant.SearchTypeEnum;
import org.cuit.fhzheng.search.manager.ProductSearchManager;
import org.cuit.fhzheng.search.vo.SpuAdminVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author lth
 * @Date  2023/6/21 13:48
 */
@RestController("platformSearchSpuController")
@RequestMapping("/p/search")
@Tag(name = "platform-spu列表接口")
public class ProductSearchController {

    @Autowired
    private ProductSearchManager productSearchManager;

    @GetMapping("/page")
    @Operation(summary = "商品管理信息列表（平台端）" , description = "商品管理信息列表（平台端）")
    public ServerResponseEntity<EsPageVO<SpuAdminVO>> adminPage(@Valid EsPageDTO pageDTO, ProductSearchDTO productSearchDTO) {
        productSearchDTO.setSearchType(SearchTypeEnum.PLATFORM.value());
        EsPageVO<SpuAdminVO> searchPage =  productSearchManager.adminPage(pageDTO, productSearchDTO);
        return ServerResponseEntity.success(searchPage);
    }
}
