package org.cuit.fhzheng.multishop.service;

import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.multishop.dto.HotSearchDTO;
import org.cuit.fhzheng.multishop.model.HotSearch;
import org.cuit.fhzheng.multishop.vo.HotSearchVO;

import java.util.List;

/**
 * 热搜
 *
 * @author YXF
 * @date 2021-01-27 09:10:00
 */
public interface HotSearchService {

	/**
	 * 分页获取热搜列表
	 * @param pageDTO 分页参数
	 * @param hotSearchDTO 搜索参数
	 * @return 热搜列表分页数据
	 */
	PageVO<HotSearchVO> page(PageDTO pageDTO, HotSearchDTO hotSearchDTO);

	/**
	 * 根据热搜id获取热搜
	 *
	 * @param hotSearchId 热搜id
	 * @return 热搜
	 */
	HotSearchVO getByHotSearchId(Long hotSearchId);

	/**
	 * 保存热搜
	 * @param hotSearch 热搜
	 */
	void save(HotSearch hotSearch);

	/**
	 * 更新热搜
	 * @param hotSearch 热搜
	 */
	void update(HotSearch hotSearch);

	/**
	 * 根据热搜id删除热搜
	 * @param hotSearchId 热搜id
	 * @param shopId 店铺id
	 */
	void deleteById(Long hotSearchId, Long shopId);

	/**
	 * 获取热搜列表
	 * @param shopId
	 * @return
	 */
	List<HotSearchVO> listByShopId(Long shopId);

	/**
	 * 清除热搜列表缓存
	 * @param shopId
	 */
	void removeHotSearchListCache(Long shopId);
}
