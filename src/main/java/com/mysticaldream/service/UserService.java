package com.mysticaldream.service;

import com.mysticaldream.domain.User;
import com.mysticaldream.service.dto.UserDTO;

/**
 * @description: UserService
 * @date: 2022/5/28 23:01
 * @author: MysticalDream
 */
public interface UserService {

    /**
     * 删除用户根据用户id
     *
     * @param userId
     * @return
     */
    int removeUserById(Long userId);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 添加用户对象的非空属性
     *
     * @param user
     * @return
     */
    int addUserSelective(User user);

    /**
     * 根据用户id获取用户信息
     *
     * @param userId
     * @return
     */
    UserDTO getUserById(Long userId);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 更新用户属性
     *
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据用户id更新全部属性
     *
     * @param user
     * @return
     */
    int updateUserById(User user);

    /**
     * 检查用户名的唯一性
     *
     * @param username
     * @return
     */
    boolean checkUsernameUnique(String username);

    /**
     * 修改用户头像
     *
     * @param user
     * @return
     */
    boolean updateUserAvatar(User user);

    /**
     * 登录
     *
     * @param user
     * @return
     */

    UserDTO login(User user);

}
