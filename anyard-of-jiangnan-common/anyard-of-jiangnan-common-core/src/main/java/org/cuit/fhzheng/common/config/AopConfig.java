package org.cuit.fhzheng.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author
 * @date 2020/9/21
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
public class AopConfig {
}
