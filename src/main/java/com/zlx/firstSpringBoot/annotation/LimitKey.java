package com.zlx.firstSpringBoot.annotation;

import java.lang.annotation.*;

/**
 * 接口防刷注解
 * 使用：
 * 在相应需要防刷的方法上加上
 * 该注解，即可
 *
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitKey {

    /**
     * 方法名称
     *
     * @return
     */
    String methodName() default "";

    /**
     * 访问次数
     *
     * @return
     */
    int frequency() default 10;

    /**
     * 业务key区分
     *
     * @return
     */
    String paramKey() default "";

    /**
     * 方法请求地址
     *
     * @return
     */
    String url() default "";

    /**
     * 过期时间
     *
     * @return
     */
    long timeout() default 3001;
}