package com.recruit.wm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wangm
 * @title: BaseException
 * @projectName recruit-parent
 * @description: 公共错误处理类
 * @date 2020/12/2016:28
 */
@Data
public class BaseException extends Exception {
    // 错误码
    public int code;

    // 错误描述
    public String message;

    public BaseException(int code, String message ) {
        super(message);
        this.code = code;
    }
}
