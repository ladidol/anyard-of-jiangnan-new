package org.cuit.fhzheng.api.auth.constant;


public enum SysTypeEnum {

	/**
	 * 普通用户系统
	 */
	ORDINARY(0),

	/**
	 * 商家端
	 */
	MULTISHOP(1),

	/**
	 * 平台端
	 */
	PLATFORM(2),

	;

	private final Integer value;

	public Integer value() {
		return value;
	}

	SysTypeEnum(Integer value) {
		this.value = value;
	}

}
