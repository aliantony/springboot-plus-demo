package com.example.handler;

import com.example.dto.ErrorResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2020-05-06
 * @version  1.0.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ErrorResult handleHttpMessageNotReadableException(Exception e) {
        ErrorResult result = new ErrorResult();
        return result;
    }
}
