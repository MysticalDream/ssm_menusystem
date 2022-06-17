package com.mysticaldream.service.impl;

import com.mysticaldream.common.constant.ProjectVariables;
import com.mysticaldream.common.utils.Md5Utils;
import com.mysticaldream.common.utils.ServletUtils;
import com.mysticaldream.domain.User;
import com.mysticaldream.mapper.UserMapper;
import com.mysticaldream.service.UserService;
import com.mysticaldream.service.dto.UserDTO;
import com.mysticaldream.service.translator.mapstruct.UserTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 用户服务
 *
 * @description: UserServiceImpl
 * @date: 2022/5/28 23:01
 * @author: MysticalDream
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    private UserMapper userMapper;

    private final UserTranslator userTranslator;

    public UserServiceImpl(UserMapper userMapper, UserTranslator userTranslator) {
        this.userMapper = userMapper;
        this.userTranslator = userTranslator;
    }

    @Override
    public int removeUserById(Long userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user) == 1;
    }

    @Override
    public int addUserSelective(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserDTO userDTO = userTranslator.toTarget(user);
        return userDTO;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean checkUsernameUnique(String username) {
        return userMapper.checkUsernameUnique(username) == 0;
    }

    @Override
    public boolean updateUserAvatar(User user) {
        return userMapper.updateUserAvatar(user) == 1;
    }

    @Override
    public UserDTO login(User user) {
        User userByUsername = this.getUserByUsername(user.getUsername());

        boolean fail =
                userByUsername == null ||
                        !userByUsername.getPassword()
                                .equals(Md5Utils.hash(user.getPassword()));


        if (!fail) {
            //保存用户信息到session
            userByUsername.setAvatar(ProjectVariables.AVATAR_RESOURCE+"/"+userByUsername.getAvatar());
            ServletUtils.getSession().setAttribute("loginUser", userByUsername);
            return userTranslator.toTarget(userByUsername);
        }
        return null;
    }

}
