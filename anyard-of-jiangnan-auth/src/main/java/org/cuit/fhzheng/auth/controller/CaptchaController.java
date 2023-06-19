package org.cuit.fhzheng.auth.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ua/captcha")
@Tag(name = "验证码")
public class CaptchaController {

	private final CaptchaService captchaService;

	public CaptchaController(CaptchaService captchaService) {
		this.captchaService = captchaService;
	}

	@PostMapping({ "/get" })
	public ServerResponseEntity<ResponseModel> get(@RequestBody CaptchaVO captchaVO) {
		return ServerResponseEntity.success(captchaService.get(captchaVO));
	}

	@PostMapping({ "/check" })
	public ServerResponseEntity<ResponseModel> check(@RequestBody CaptchaVO captchaVO) {
		return ServerResponseEntity.success(captchaService.check(captchaVO));
	}

}
