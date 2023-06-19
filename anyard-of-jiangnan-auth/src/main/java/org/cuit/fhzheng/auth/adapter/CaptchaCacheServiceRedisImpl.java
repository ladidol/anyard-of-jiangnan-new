package org.cuit.fhzheng.auth.adapter;

import com.anji.captcha.service.CaptchaCacheService;
import org.cuit.fhzheng.common.cache.util.RedisUtil;

/**
 * 适配验证码在redis的存储
 *
 * @author
 * @date 2020/8/12
 */
public class CaptchaCacheServiceRedisImpl implements CaptchaCacheService {

	@Override
	public void set(String key, String value, long expiresInSeconds) {
		RedisUtil.set(key, value, expiresInSeconds);
	}

	@Override
	public boolean exists(String key) {
		return RedisUtil.hasKey(key);
	}

	@Override
	public void delete(String key) {
		RedisUtil.del(key);
	}

	@Override
	public String get(String key) {
		return RedisUtil.get(key);
	}

	@Override
	public String type() {
		return "redis";
	}

}
