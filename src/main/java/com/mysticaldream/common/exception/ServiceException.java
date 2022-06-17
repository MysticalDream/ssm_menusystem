package com.mysticaldream.common.exception;

import java.text.MessageFormat;

/**
 * 业务异常
 *
 * @description: ServiceException
 * @date: 2022/5/23 9:33
 * @author: MysticalDream
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 异常码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    public ServiceException(String message) {
        this.message = message;
    }


    public ServiceException(String code, String message) {
        super("[code=" + code + ", msg=" + message + "]");
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message, Object... args) {
        super("msg=" + MessageFormat.format("业务异常:"+message, args) + "]");
        this.message = MessageFormat.format("业务异常:"+message, args);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
