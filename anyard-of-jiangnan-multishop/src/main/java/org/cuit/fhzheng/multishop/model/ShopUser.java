package org.cuit.fhzheng.multishop.model;

import java.io.Serializable;

import org.cuit.fhzheng.common.database.annotations.DistributedId;
import org.cuit.fhzheng.common.model.BaseModel;
/**
 * 商家用户
 *
 * @author
 * @date 2020-12-05 15:50:25
 */
public class ShopUser extends BaseModel implements Serializable{

    /**
     * 商家用户id
     */
	@DistributedId("anyard-of-jiangnan-multishop-user")
    private Long shopUserId;

    /**
     * 关联店铺id
     */
    private Long shopId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 员工编号
     */
    private String code;

    /**
     * 联系方式
     */
    private String phoneNum;

    /**
     * 是否已经设置账号
     */
    private Integer hasAccount;

	public Long getShopUserId() {
		return shopUserId;
	}

	public void setShopUserId(Long shopUserId) {
		this.shopUserId = shopUserId;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getHasAccount() {
		return hasAccount;
	}

	public void setHasAccount(Integer hasAccount) {
		this.hasAccount = hasAccount;
	}

	@Override
	public String toString() {
		return "ShopUser{" +
				"shopUserId=" + shopUserId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",shopId=" + shopId +
				",nickName=" + nickName +
				",code=" + code +
				",phoneNum=" + phoneNum +
				",hasAccount=" + hasAccount +
				'}';
	}
}
