package org.cuit.fhzheng.common.security.bo;

import org.cuit.fhzheng.api.auth.bo.UserInfoInTokenBO;

/**
 * token信息，该信息存在redis中
 *
 * @author
 * @date 2020/7/2
 */
public class TokenInfoBO {

	/**
	 * 保存在token信息里面的用户信息
	 */
	private UserInfoInTokenBO userInfoInToken;

	private String accessToken;

	private String refreshToken;

	/**
	 * 在多少秒后过期
	 */
	private Integer expiresIn;

	public UserInfoInTokenBO getUserInfoInToken() {
		return userInfoInToken;
	}

	public void setUserInfoInToken(UserInfoInTokenBO userInfoInToken) {
		this.userInfoInToken = userInfoInToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	@Override
	public String toString() {
		return "TokenInfoBO{" + "userInfoInToken=" + userInfoInToken + ", accessToken='" + accessToken + '\''
				+ ", refreshToken='" + refreshToken + '\'' + ", expiresIn=" + expiresIn + '}';
	}

}
