package org.cuit.fhzheng.product.controller.app;

import org.cuit.fhzheng.product.dto.BrandDTO;
import org.cuit.fhzheng.product.service.BrandService;
import org.cuit.fhzheng.api.product.vo.BrandVO;
import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
 * 品牌信息
 *
 * @author
 * @date 2020-10-28 15:27:24
 */
@RestController("appBrandController")
@RequestMapping("/ua/brand")
@Tag(name = "app-品牌信息")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/page")
    @Operation(summary = "获取品牌信息列表" , description = "分页获取品牌信息列表")
    public ServerResponseEntity<PageVO<BrandVO>> page(@Valid PageDTO pageDTO, BrandDTO brandDTO) {
        PageVO<BrandVO> brandPage = brandService.page(pageDTO, brandDTO);
        return ServerResponseEntity.success(brandPage);
    }

    @GetMapping("/top_brand_list")
    @Operation(summary = "置顶品牌列表" , description = "置顶品牌列表")
    public ServerResponseEntity<List<BrandVO>> topBrandList() {
        List<BrandVO> brandPage = brandService.topBrandList();
        return ServerResponseEntity.success(brandPage);
    }

    @GetMapping("/list_by_category")
    @Operation(summary = "分类-推荐品牌信息列表" , description = "分类-推荐品牌信息列表")
    public ServerResponseEntity<List<BrandVO>> getTopBrandList(Long categoryId) {
        List<BrandVO> brandPage = brandService.listByCategory(categoryId);
        return ServerResponseEntity.success(brandPage);
    }
}
