package com.mysticaldream.service;

import com.github.pagehelper.PageInfo;
import com.mysticaldream.domain.Condition;
import com.mysticaldream.domain.Menu;
import com.mysticaldream.service.dto.MenuDTO;

import java.util.List;

/**
 * @description: MenuService
 * @date: 2022/5/28 22:55
 * @author: MysticalDream
 */
public interface MenuService {


    boolean deleteByPrimaryKey(Long id);

    boolean insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    boolean updateByPrimaryKey(Menu record);


    PageInfo<Menu> getMenuByCondition(Condition condition);

    PageInfo<MenuDTO> getMenuList(Condition condition);

    boolean removeBatch(List<Long> list);


}
