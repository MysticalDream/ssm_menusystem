package com.mysticaldream.service.impl;

import com.github.pagehelper.PageInfo;
import com.mysticaldream.common.constant.ProjectVariables;
import com.mysticaldream.common.utils.FileUtils;
import com.mysticaldream.domain.Condition;
import com.mysticaldream.service.dto.MenuDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.mysticaldream.mapper.MenuMapper;
import com.mysticaldream.domain.Menu;
import com.mysticaldream.service.MenuService;

import java.util.List;
import java.util.Optional;

/**
 * @description: MenuServiceImpl
 * @date: 2022/5/28 22:55
 * @author: MysticalDream
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        return menuMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean insert(Menu record) {
        record.setCoverUrl(FileUtils.getFileName(record.getCoverUrl()));
        return menuMapper.insert(record) == 1;
    }

    @Override
    public int insertSelective(Menu record) {
        record.setCoverUrl(FileUtils.getFileName(record.getCoverUrl()));
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        menu.setCoverUrl(ProjectVariables.MENU_RESOURCE + "/" + menu.getCoverUrl());
        return menu;
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public boolean updateByPrimaryKey(Menu record) {
        record.setCoverUrl(FileUtils.getFileName(record.getCoverUrl()));
        return menuMapper.updateByPrimaryKey(record) == 1;
    }

    @Override
    public PageInfo<Menu> getMenuByCondition(Condition condition) {
        List<Menu> menus = menuMapper.selectAllMenuByPageNumSize(condition, condition.getPageNum(), condition.getPageSize());
        menus.forEach(menu -> {
            menu.setCoverUrl(ProjectVariables.MENU_RESOURCE + "/" + menu.getCoverUrl());
        });
        return new PageInfo<>(menus);
    }

    @Override
    public PageInfo<MenuDTO> getMenuList(Condition condition) {
        List<MenuDTO> menuDTOS = menuMapper.selectDtoList(Optional.ofNullable(condition.getKey()).orElse(""), condition.getPageNum(), condition.getPageSize());
        menuDTOS.forEach(menuDTO -> {
            menuDTO.setCoverUrl(ProjectVariables.MENU_RESOURCE + "/" + menuDTO.getCoverUrl());
        });
        return new PageInfo<>(menuDTOS);
    }

    @Override
    public boolean removeBatch(List<Long> list) {
        int i = menuMapper.deleteMenuBatch(list);
        return i != 0;
    }

}
