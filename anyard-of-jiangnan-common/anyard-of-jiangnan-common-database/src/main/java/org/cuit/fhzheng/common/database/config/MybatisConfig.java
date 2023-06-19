package org.cuit.fhzheng.common.database.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date 2020/6/24
 */
@Configuration
@MapperScan({ "org.cuit.fhzheng.**.mapper" })
public class MybatisConfig {


}
