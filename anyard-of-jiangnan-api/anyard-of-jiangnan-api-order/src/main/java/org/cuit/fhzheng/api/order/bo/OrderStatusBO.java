package org.cuit.fhzheng.api.order.bo;

/**
 * @author
 * @date 2020/12/30
 */
public class OrderStatusBO {

    private Long orderId;

    private Integer status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderStatusBO{" +
                "orderId=" + orderId +
                ", status=" + status +
                '}';
    }
}
