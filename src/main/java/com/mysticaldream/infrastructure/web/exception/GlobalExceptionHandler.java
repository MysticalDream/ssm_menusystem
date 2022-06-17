package com.mysticaldream.infrastructure.web.exception;

import com.mysticaldream.common.enums.ResultCode;
import com.mysticaldream.common.exception.AccessDeniedException;
import com.mysticaldream.common.exception.ServiceException;
import com.mysticaldream.common.utils.IpUtils;
import com.mysticaldream.web.vo.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 全局异常处理
 *
 * @description: GlobalExceptionHandler
 * @date: 2022/5/23 9:48
 * @author: MysticalDream
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                         HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return ApiResult.failure(ResultCode.REQUEST_METHOD_NOT_ALLOWED, e.getMessage());
    }


    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResult> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("权限校验失败原因:{},客户端ip:{},请求地址:{},请求参数:{}", e.getMessage(), IpUtils.getIpAddr(request), request.getRequestURI(), request.getParameterMap());
        return new ResponseEntity<>(ApiResult.failure(ResultCode.REJECT_THE_REQUEST), HttpStatus.FORBIDDEN);
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public ApiResult handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return ApiResult.failure(ResultCode.SERVER_RUNNING_EXCEPTION);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
        return ApiResult.failure(e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return ApiResult.failure(e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ApiResult handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ApiResult.failure(ResultCode.REQUEST_SYNTAX_ERROR, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResult.failure(ResultCode.REQUEST_SYNTAX_ERROR, message);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoFoundException(HttpServletRequest request, HttpServletResponse response, NoHandlerFoundException e) {
        log.error("NoHandlerFoundException Exception Cause by:{}", e.getMessage());
        request.setAttribute("requestPath", e.getRequestURL());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("page/error/404");
        return modelAndView;
    }

    /**
     * 类型转化失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiResult handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("参数转换失败，方法：" + Objects.requireNonNull(e.getParameter().getMethod()).getName() + ",参数：" +
                e.getName() + "，信息：" + e.getLocalizedMessage());
        return ApiResult.failure(ResultCode.REQUEST_SYNTAX_ERROR, e.getMessage());
    }


}
