package org.cuit.fhzheng.multishop.service;

import org.cuit.fhzheng.common.database.dto.PageDTO;
import org.cuit.fhzheng.common.database.vo.PageVO;
import org.cuit.fhzheng.multishop.dto.IndexImgDTO;
import org.cuit.fhzheng.multishop.model.IndexImg;
import org.cuit.fhzheng.multishop.vo.IndexImgVO;

import java.util.List;

/**
 * 轮播图
 *
 * @author YXF
 * @date 2020-11-24 16:38:32
 */
public interface IndexImgService {

	/**
	 * 分页获取轮播图列表
	 * @param pageDTO 分页参数
	 * @param indexImgDTO
	 * @return 轮播图列表分页数据
	 */
	PageVO<IndexImgVO> page(PageDTO pageDTO, IndexImgDTO indexImgDTO);

	/**
	 * 根据轮播图id获取轮播图
	 * @param imgId 轮播图id
	 * @return 轮播图
	 */
	IndexImgVO getByImgId(Long imgId);

	/**
	 * 保存轮播图
	 * @param indexImg 轮播图
	 */
	void save(IndexImg indexImg);

	/**
	 * 更新轮播图
	 * @param indexImg 轮播图
	 */
	void update(IndexImg indexImg);

	/**
	 * 根据轮播图id删除轮播图
	 * @param imgId
	 * @param shopId
	 */
	void deleteById(Long imgId, Long shopId);

	/**
	 * 根据店铺id，获取轮播图列表
	 * @param shopId
	 * @return
	 */
    List<IndexImgVO> getListByShopId(Long shopId);

	/**
	 * 根据商品d删除轮播图信息
	 * @param spuId
	 * @param shopId
	 */
	void deleteBySpuId(Long spuId, Long shopId);
}
