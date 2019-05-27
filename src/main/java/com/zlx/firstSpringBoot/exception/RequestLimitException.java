package com.zlx.firstSpringBoot.exception;

/**
 * 接口请求限制异常
 */
public class RequestLimitException extends RuntimeException {

    public RequestLimitException(String message) {
        super(message);
    }
}
