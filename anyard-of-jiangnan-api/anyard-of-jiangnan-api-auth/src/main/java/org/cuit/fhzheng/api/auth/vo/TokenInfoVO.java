package org.cuit.fhzheng.api.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;


public class TokenInfoVO {

	@Schema(description = "accessToken" )
	private String accessToken;

	@Schema(description = "refreshToken" )
	private String refreshToken;

	@Schema(description = "在多少秒后过期" )
	private Integer expiresIn;

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
		return "TokenInfoVO{" + "accessToken='" + accessToken + '\'' + ", refreshToken='" + refreshToken + '\''
				+ ", expiresIn=" + expiresIn + '}';
	}

}
