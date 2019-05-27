package com.zlx.firstSpringBoot.handler;

import com.zlx.firstSpringBoot.constant.ReturnUtil;
import com.zlx.firstSpringBoot.constant.response.StringResp;
import com.zlx.firstSpringBoot.exception.RequestLimitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Map<String, Object> exceptionHandler(HttpServletRequest req, Exception e) {
        return ReturnUtil.returnFailured(new StringResp(e.getMessage()));
    }

    @ExceptionHandler(value = RequestLimitException.class)
    @ResponseBody
    private Map<String, Object> requestLimitExceptionHandler(HttpServletRequest req, RequestLimitException e) {
        System.out.println(req.getRequestURL()+"  "+e.toString());
        return ReturnUtil.returnFailured(new StringResp(e.getMessage()));
    }
}
