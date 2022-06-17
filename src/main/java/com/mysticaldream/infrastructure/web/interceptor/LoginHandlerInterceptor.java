package com.mysticaldream.infrastructure.web.interceptor;

import com.mysticaldream.common.annotation.NotRequireLogin;
import com.mysticaldream.common.annotation.RequireLogin;
import com.mysticaldream.common.enums.Role;
import com.mysticaldream.common.exception.AccessDeniedException;
import com.mysticaldream.common.utils.IpUtils;
import com.mysticaldream.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * 登录拦截器
 *
 * @description: LoginHandlerInterceptor
 * @date: 2022/6/2 16:16
 * @author: MysticalDream
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(LoginHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {

            Object bean = handlerMethod.getBean();

            Method method = handlerMethod.getMethod();
            //获取相关注解
            RequireLogin classRequireLogin = bean.getClass().getAnnotation(RequireLogin.class);
            NotRequireLogin methodNotRequireLogin = method.getAnnotation(NotRequireLogin.class);
            RequireLogin methodRequireLogin = method.getAnnotation(RequireLogin.class);

            //判断是否需要登录验证
            boolean isRequired = methodRequireLogin != null || (methodNotRequireLogin == null
                    && classRequireLogin != null);

            //获取非空注解
            RequireLogin requireLogin = Optional.ofNullable(methodRequireLogin).orElse(classRequireLogin);


            //不需要验证
            if (!isRequired) {
                return true;
            }

            Object loginUser = request.getSession().getAttribute("loginUser");

            if (loginUser == null) {
                //说明没有登录
                //获取跳转路径
                String redirectPath = requireLogin.value();

                if (log.isWarnEnabled()) {
                    log.warn("用户未登录,客户端ip:{}", IpUtils.getIpAddr(request));
                }
                response.sendRedirect(redirectPath);
                return false;
            } else {
                //说明登录，进一步判断角色是否符合条件
                Role[] roles = requireLogin.role();
                User user = (User) loginUser;
                for (Role role1 :
                        roles) {
                    if (role1.getName().equals(user.getRole())) {
                        //符合角色
                        return true;
                    }
                }
                throw new AccessDeniedException("用户角色{0},期望角色:{1}", user.getRole(), Arrays.toString(roles));
            }
        }
        return true;
    }
}
