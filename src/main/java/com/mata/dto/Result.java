package com.mata.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> {
    private T data;
    private Integer isSuccess; // 1：成功 0：失败
    private String message;

    public static <T> Result<T> success(T data,String message){
        return new Result<>(data,1,message);
    }

    public static Result error(String message){
        return new Result(null,0,message);
    }
}
