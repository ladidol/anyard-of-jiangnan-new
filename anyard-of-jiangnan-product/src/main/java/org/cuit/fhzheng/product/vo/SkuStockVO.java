package org.cuit.fhzheng.product.vo;

import org.cuit.fhzheng.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 库存信息VO
 *
 * @author
 * @date 2020-10-28 15:27:24
 */
public class SkuStockVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    @Schema(description = "库存id" )
    private Long stockId;

    @Schema(description = "SKU ID" )
    private Long skuId;

    @Schema(description = "库存" )
    private Integer stock;

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "StockVO{" +
				"stockId=" + stockId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",skuId=" + skuId +
				",stock=" + stock +
				'}';
	}
}
