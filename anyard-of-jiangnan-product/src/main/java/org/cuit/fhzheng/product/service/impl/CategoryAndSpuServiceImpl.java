package org.cuit.fhzheng.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.cuit.fhzheng.api.product.constant.CategoryLevel;
import org.cuit.fhzheng.api.product.vo.CategoryVO;
import org.cuit.fhzheng.common.constant.Constant;
import org.cuit.fhzheng.common.constant.StatusEnum;
import org.cuit.fhzheng.common.security.AuthUserContext;
import org.cuit.fhzheng.product.dto.CategoryDTO;
import org.cuit.fhzheng.product.model.Category;
import org.cuit.fhzheng.product.service.CategoryAndSpuService;
import org.cuit.fhzheng.product.service.CategoryService;
import org.cuit.fhzheng.product.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 分类及商品的关联处理信息
 *
 * @author
 * @date 2020-10-28 15:27:24
 */
@Service
public class CategoryAndSpuServiceImpl implements CategoryAndSpuService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SpuService spuService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean categoryEnableOrDisable(CategoryDTO categoryDTO) {
        CategoryVO categoryDb = categoryService.getById(categoryDTO.getCategoryId());
        // 如果是重复提交，则直接返回
        if (Objects.equals(categoryDb.getStatus(), categoryDTO.getStatus())) {
            return Boolean.TRUE;
        }
        List<Long> updateList = new ArrayList<>();
        List<Long> thirdIdList = new ArrayList<>();
        if (!categoryDb.getLevel().equals(CategoryLevel.THIRD.value())) {
            // 如果是店铺的二级分类需要将分类id放进去
            if (!Objects.equals(categoryDb.getShopId(), Constant.PLATFORM_SHOP_ID) && Objects.equals(categoryDb.getLevel(), CategoryLevel.SECOND.value())) {
                thirdIdList.add(categoryDb.getCategoryId());
            }

            List<Category> categoryList = categoryService.getChildCategory(categoryDb.getCategoryId());
            categoryList.forEach(category -> {
                updateList.add(category.getCategoryId());
                if (Objects.equals(categoryDb.getShopId(), Constant.PLATFORM_SHOP_ID) && Objects.equals(category.getLevel(), CategoryLevel.THIRD.value())) {
                    thirdIdList.add(category.getCategoryId());
                } else if (!Objects.equals(categoryDb.getShopId(), Constant.PLATFORM_SHOP_ID) && Objects.equals(category.getLevel(), CategoryLevel.SECOND.value())) {
                    thirdIdList.add(category.getCategoryId());
                }
            });
        } else {
            updateList.add(categoryDb.getCategoryId());
            thirdIdList.add(categoryDb.getCategoryId());
        }
        updateList.add(categoryDb.getCategoryId());
        categoryService.updateBatchOfStatus(updateList, categoryDTO.getStatus());

        categoryService.removeCategoryCache(AuthUserContext.get().getTenantId(), null);

        // 分类下架后，下架分类中的商品
        if (Objects.equals(categoryDTO.getStatus(), StatusEnum.DISABLE.value())) {
            if (CollUtil.isEmpty(thirdIdList)) {
                return Boolean.TRUE;
            }
            spuService.batchChangeSpuStatusByCids(thirdIdList, categoryDb.getShopId(), StatusEnum.DISABLE.value());
        }
        return Boolean.TRUE;
    }
}
