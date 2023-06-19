package org.cuit.fhzheng.multishop.controller.app;

import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.multishop.service.HotSearchService;
import org.cuit.fhzheng.multishop.vo.HotSearchVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 热搜
 *
 * @author YXF
 * @date 2021-01-27 09:10:00
 */
@RestController("appHotSearchController")
@RequestMapping("/ua/app/hot_search")
@Tag(name = "app-热搜")
public class HotSearchController {

    @Autowired
    private HotSearchService hotSearchService;

	@GetMapping("/list")
	@Operation(summary = "获取热搜列表" , description = "获取热搜列表")
	@Parameter(name = "shopId", description = "店铺id")
	public ServerResponseEntity<List<HotSearchVO>> listByShopId(@RequestParam("shopId") Long shopId) {
		List<HotSearchVO> hotSearches = hotSearchService.listByShopId(shopId);
		return ServerResponseEntity.success(hotSearches);
	}
}
