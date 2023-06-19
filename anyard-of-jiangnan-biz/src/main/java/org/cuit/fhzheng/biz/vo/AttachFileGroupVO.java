package org.cuit.fhzheng.biz.vo;

import org.cuit.fhzheng.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * VO
 *
 * @author YXF
 * @date 2020-12-04 16:15:02
 */
public class AttachFileGroupVO extends BaseVO{
    private static final long serialVersionUID = 1L;

    @Schema()
    private Long attachFileGroupId;

    @Schema(description = "店铺id" )
    private Long shopId;

    @Schema(description = "分组名称" )
    private String name;

	public Long getAttachFileGroupId() {
		return attachFileGroupId;
	}

	public void setAttachFileGroupId(Long attachFileGroupId) {
		this.attachFileGroupId = attachFileGroupId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AttachFileGroupVO{" +
				"attachFileGroupId=" + attachFileGroupId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",shopId=" + shopId +
				",name=" + name +
				'}';
	}
}
