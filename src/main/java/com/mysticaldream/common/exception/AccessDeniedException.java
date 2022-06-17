package com.mysticaldream.common.exception;

import java.text.MessageFormat;

/**
 * 业务异常
 *
 * @description: ServiceException
 * @date: 2022/5/23 9:33
 * @author: MysticalDream
 */
public class AccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 异常信息
     */
    private String message;

    public AccessDeniedException(String message) {
        this.message = message;
    }


    public AccessDeniedException(String message, Object... args) {
        this.message = MessageFormat.format(message, args);
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
        return "AccessDeniedException{" +
                "message='" + message + '\'' +
                '}';
    }
}
