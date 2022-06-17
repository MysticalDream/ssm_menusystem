package com.mysticaldream.web.controller;

import com.mysticaldream.common.annotation.RequireLogin;
import com.mysticaldream.common.enums.ResultCode;
import com.mysticaldream.common.enums.Role;
import com.mysticaldream.domain.User;
import com.mysticaldream.service.UserService;
import com.mysticaldream.service.dto.UserDTO;
import com.mysticaldream.web.vo.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * 登录控制器
 *
 * @description: LoginController
 * @date: 2022/5/29 13:14
 * @author: MysticalDream
 */
@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sessions")
    public ApiResult login(@RequestBody User user, HttpSession session) {
        session.setMaxInactiveInterval(-1);
        UserDTO userDTO = userService.login(user);
        if (userDTO == null) {
            return new ApiResult(ResultCode.PASSWORD_OR_USER_NAME_IS_INCORRECT);
        }
        return ApiResult.success(userDTO);
    }

    @PostMapping("/sessions/destroy")
    @RequireLogin(role = {Role.USER, Role.ADMIN})
    public ApiResult loginOut(HttpSession session) {
        session.invalidate();
        return ApiResult.success();
    }
}
