package org.cuit.fhzheng.order.dto.multishop;

import org.cuit.fhzheng.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 订单项VO
 *
 * @author
 * @date 2020-12-04 11:27:35
 */
public class OrderItemDTO extends BaseVO{
    private static final long serialVersionUID = 1L;

    @Schema(description = "订单项ID" )
    private Long orderItemId;

	@Schema(description = "变化金额" , required = true)
	private Long changeAmount;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(Long changeAmount) {
		this.changeAmount = changeAmount;
	}

	@Override
	public String toString() {
		return "OrderItemDTO{" +
				"orderItemId=" + orderItemId +
				", changeAmount=" + changeAmount +
				'}';
	}
}
