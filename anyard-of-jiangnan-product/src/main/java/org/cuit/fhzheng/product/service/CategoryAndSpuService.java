package org.cuit.fhzheng.product.service;

import org.cuit.fhzheng.product.dto.CategoryDTO;

/**
 * 分类信息
 *
 * @author
 * @date 2020-10-28 15:27:24
 */
public interface CategoryAndSpuService {
	/**
	 * 分类的启用和禁用
	 * @param categoryDTO
	 * @return
	 */
    Boolean categoryEnableOrDisable(CategoryDTO categoryDTO);

}
