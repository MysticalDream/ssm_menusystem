package com.mysticaldream.common.enums;

import lombok.Getter;

/**
 * @description: ResultCode
 * @date: 2022/5/22 19:13
 * @author: MysticalDream
 */
@Getter
public enum ResultCode {
    /**
     * 成功状态码 200
     */
    SUCCESS(200, "请求成功", true),
    /**
     * 客户端错误 4000-4999
     */
    REQUEST_SYNTAX_ERROR(4000, "请求语法错误"),
    //未登录时返回
    UNAUTHENTICATED(4001, "未验证身份"),
    REJECT_THE_REQUEST(4003, "拒绝该请求"),
    THE_PAGE_NOT_FOUND(4004, "资源不存在"),
    REQUEST_METHOD_NOT_ALLOWED(4005, "请求方法不允许"),
    THE_TOKEN_IS_INVALID(4098, "令牌失效"),
    THE_TOKEN_ERROR(4099, "客户端令牌错误"),

    /**
     * 服务器错误5000-5999
     */
    SERVER_RUNNING_EXCEPTION(5000, "服务器运行异常"),
    RESOURCE_STORAGE_FAILED(5500, "资源存储异常"),
    /**
     * 用户错误6000-6999
     */
    PASSWORD_OR_USER_NAME_IS_INCORRECT(6000, "密码或用户名错误"),

    USER_NAME_ALREADY_EXISTS(60001, "该用户名不可用"),

    USER_PASSWORD_NOT_EQUAL(6002, "两次输入的密码不相等"),

    INCORRECT_USERNAME_FORMAT(6003, "用户名只能由由数字、26个英文字母或者下划线组成，长度在3-20之间"),

    INCORRECT_PASSWORD_FORMAT(6004, "密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-10 之间"),
    UNKNOWN_USER(6005, "未知用户"),
    UNAUTHORIZED_ACCESS(6666, "越权访问"),
    DISALLOWED_TYPE(6006, "不允许的类型");


    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
        //默认不成功
        this.isSuccess = false;
    }

    ResultCode(int code, String msg, boolean isSuccess) {
        this.code = code;
        this.msg = msg;
        this.isSuccess = isSuccess;
    }

    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;

    /**
     * 请求是否成功
     */
    private boolean isSuccess;


}
