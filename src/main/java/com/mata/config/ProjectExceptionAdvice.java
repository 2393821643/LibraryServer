package com.mata.config;

import com.mata.dto.Result;
import com.mata.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    //其他异常
    @ExceptionHandler(Exception.class)
    public Result doException(Exception exception){
        return Result.error("系统异常");
    }
    //业务异常
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException businessException){
        return Result.error(businessException.getMessage());
    }

}
