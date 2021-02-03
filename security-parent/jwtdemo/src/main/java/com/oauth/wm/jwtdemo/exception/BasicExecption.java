package com.oauth.wm.jwtdemo.exception;

/**
 * @author wangm
 * @title: BaseExecption
 * @projectName security-parent
 * @description: TODO
 * @date 2021/2/323:37
 */

public class BasicExecption {
    public BasicExecption(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
