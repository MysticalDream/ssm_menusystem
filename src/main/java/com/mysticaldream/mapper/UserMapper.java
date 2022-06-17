package com.mysticaldream.mapper;

import com.mysticaldream.domain.User;

/**
 * @description: UserMapper
 * @date: 2022/5/28 23:01
 * @author: MysticalDream
 */

public interface UserMapper {
    /**
     * 根据主键删除用户
     *
     * @param userId
     * @return
     */

    int deleteByPrimaryKey(Long userId);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 添加用户，使用传入对象的非空属性
     *
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     * 根据主键查询用户
     *
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Long userId);

    /**
     * 更新用户，使用传入对象的非空属性
     *
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 通过主键更新用户
     *
     * @param user
     * @return
     */
    int updateByPrimaryKey(User user);

    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    User selectUserByUsername(String username);

    /**
     * 检查用户名的唯一性
     *
     * @param username
     * @return
     */
    int checkUsernameUnique(String username);

    /**
     * 修改用户头像
     *
     * @param user
     * @return
     */
    int updateUserAvatar(User user);



}