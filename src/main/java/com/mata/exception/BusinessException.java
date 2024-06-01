package com.mata.exception;

//建立自定义异常 业务异常
public class BusinessException extends RuntimeException {
    //继承RuntimeException（运行时异常）
    private Integer code;


    public BusinessException(String message) {
        super(message);
        this.code = 0;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
