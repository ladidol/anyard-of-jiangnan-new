package org.cuit.fhzheng.common.database.annotations;

import org.cuit.fhzheng.common.database.interceptor.GeneratedKeyInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式id标识
 * 如果一个类是model中的类，也就是继承BaseModel的类，并且字段含有该注解，当插入数据的时候，会往该字段插入分布式id
 * @author
 * @date 2020/09/09
 * @see GeneratedKeyInterceptor
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedId {

    String value();
}
