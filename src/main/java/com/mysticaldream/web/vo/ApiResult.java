package com.mysticaldream.web.vo;

import com.mysticaldream.common.enums.ResultCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * 接口响应信息
 *
 * @description: ApiResult
 * @date: 2022/5/22 19:54
 * @author: MysticalDream
 */
@Getter
public class ApiResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 结果码
     */
    private int code;
    /**
     * 响应消息说明
     */
    private String message;
    /**
     * 响应请求是否成功
     */
    private boolean success;
    /**
     * 响应的数据，不一定有
     */
    private T data;


    public ApiResult(ResultCode apiResultCode) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getMsg();
        this.success = apiResultCode.isSuccess();
    }

    public ApiResult(ResultCode apiResultCode, T data) {
        this(apiResultCode);

        if (!Objects.isNull(data)) {
            this.data = data;
        }

    }

    /**
     * 默认成功响应
     *
     * @param data 要响应的数据
     */
    public ApiResult(T data) {
        this(ResultCode.SUCCESS, data);
    }


    public static <T> ApiResult<T> success() {
        return ApiResult.success(null);
    }


    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ResultCode.SUCCESS, data);
    }


    public static <T> ApiResult<T> failure() {
        return ApiResult.failure(null);
    }

    public static <T> ApiResult<T> failure(T data) {
        return ApiResult.failure(ResultCode.SERVER_RUNNING_EXCEPTION, data);
    }

    public static <T> ApiResult<T> failure(ResultCode resultCode, T data) {
        return new ApiResult<>(resultCode, data);
    }
}
