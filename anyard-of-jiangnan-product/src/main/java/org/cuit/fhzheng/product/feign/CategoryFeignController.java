package org.cuit.fhzheng.product.feign;

import org.cuit.fhzheng.api.product.feign.CategoryFeignClient;
import org.cuit.fhzheng.api.product.vo.CategoryVO;
import org.cuit.fhzheng.common.constant.Constant;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhd
 * @date 2020/12/23
 */
@RestController
public class CategoryFeignController implements CategoryFeignClient {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ServerResponseEntity<List<CategoryVO>> listByOneLevel() {
        return ServerResponseEntity.success(categoryService.listByShopIdAndParenId(Constant.PLATFORM_SHOP_ID, Constant.CATEGORY_ID));
    }

    @Override
    public ServerResponseEntity<List<Long>> listCategoryId(Long categoryId) {
        CategoryVO category = categoryService.getById(categoryId);
        List<Long> categoryIds = categoryService.listCategoryId(category.getShopId(), category.getParentId());
        return ServerResponseEntity.success(categoryIds);
    }
}
