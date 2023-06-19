package org.cuit.fhzheng.api.auth.bo;

import org.cuit.fhzheng.api.auth.constant.SysTypeEnum;


public class UserInfoInTokenBO {

	/**
	 * 用户在自己系统的用户id
	 */
	private Long userId;

	/**
	 * 全局唯一的id,
	 */
	private Long uid;

	/**
	 * 租户id (商家id)
	 */
	private Long tenantId;

	/**
	 * 系统类型
	 * @see SysTypeEnum
	 */
	private Integer sysType;

	/**
	 * 是否是管理员
	 */
	private Integer isAdmin;

	private String bizUserId;

	private String bizUid;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getBizUserId() {
		return bizUserId;
	}

	public void setBizUserId(String bizUserId) {
		this.bizUserId = bizUserId;
	}

	public String getBizUid() {
		return bizUid;
	}

	public void setBizUid(String bizUid) {
		this.bizUid = bizUid;
	}

	@Override
	public String toString() {
		return "UserInfoInTokenBO{" +
				"userId=" + userId +
				", uid=" + uid +
				", tenantId=" + tenantId +
				", sysType=" + sysType +
				", isAdmin=" + isAdmin +
				", bizUserId='" + bizUserId + '\'' +
				", bizUid='" + bizUid + '\'' +
				'}';
	}
}
