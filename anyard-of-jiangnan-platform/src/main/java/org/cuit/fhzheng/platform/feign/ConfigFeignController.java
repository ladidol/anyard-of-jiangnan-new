package org.cuit.fhzheng.platform.feign;

import org.cuit.fhzheng.api.platform.feign.ConfigFeignClient;
import org.cuit.fhzheng.common.response.ServerResponseEntity;
import org.cuit.fhzheng.platform.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  
 * @date 2020/12/30
 */
@RestController
public class ConfigFeignController implements ConfigFeignClient {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public ServerResponseEntity<String> getConfig(String key) {
        return ServerResponseEntity.success(sysConfigService.getValue(key));
    }
}
