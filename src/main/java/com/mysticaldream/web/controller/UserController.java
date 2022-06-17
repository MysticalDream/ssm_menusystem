package com.mysticaldream.web.controller;

import com.mysticaldream.domain.User;
import com.mysticaldream.service.dto.UserDTO;
import com.mysticaldream.service.UserService;
import com.mysticaldream.web.translator.mapstruct.LoginUserVOTranslator;
import com.mysticaldream.web.vo.ApiResult;
import com.mysticaldream.web.vo.LoginUserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户控制层
 *
 * @author MysticalDream
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    /**
     * 服务对象
     */
    private final UserService userService;
    /**
     * 用户注册视图转化
     */
    private final LoginUserVOTranslator loginUserVOTranslator;


    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    public ApiResult<UserDTO> getUserInfo(@PathVariable Long id) {
        return ApiResult.success(userService.getUserById(id));
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public ApiResult<User> registerUser(@Valid LoginUserVO user) {

        System.out.println(user);
//        if (userService.addUser(user)) {
//            ApiResult.success();
//        }
//
        return ApiResult.failure();
    }


}
