package org.cuit.fhzheng.auth.model;

import org.cuit.fhzheng.common.database.annotations.DistributedId;
import org.cuit.fhzheng.common.model.BaseModel;

/**
 * 统一账户信息
 *
 * @author
 * @date 2020/07/02
 */
public class AuthAccount extends BaseModel {

	/**
	 * 全平台用户唯一id
	 */
	@DistributedId("anyard-of-jiangnan-auth-account")
	private Long uid;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 创建ip
	 */
	private String createIp;

	/**
	 * 状态 1:启用 0:禁用 -1:删除
	 */
	private Integer status;

	/**
	 * 系统类型见SysTypeEnum 0.普通用户系统 1.商家端
	 */
	private Integer sysType;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 所属租户
	 */
	private Long tenantId;

	/**
	 * 是否是管理员
	 */
	private Integer isAdmin;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSysType() {
		return sysType;
	}

	public void setSysType(Integer sysType) {
		this.sysType = sysType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "AuthAccount{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", createIp='" + createIp + '\'' +
				", status=" + status +
				", sysType=" + sysType +
				", userId=" + userId +
				", tenantId=" + tenantId +
				", isAdmin=" + isAdmin +
				'}';
	}

}
