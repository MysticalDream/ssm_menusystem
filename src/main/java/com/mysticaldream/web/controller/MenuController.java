package com.mysticaldream.web.controller;

import com.github.pagehelper.PageInfo;
import com.google.protobuf.Api;
import com.mysticaldream.domain.Condition;
import com.mysticaldream.domain.Menu;
import com.mysticaldream.service.dto.MenuDTO;
import com.mysticaldream.service.impl.MenuServiceImpl;
import com.mysticaldream.web.vo.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (menu)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/menus")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuServiceImpl menuServiceImpl;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/detail")
    public ApiResult selectOne(Long id) {
        return ApiResult.success(menuServiceImpl.selectByPrimaryKey(id));
    }

    @GetMapping("/condition")
    public ApiResult getAllMenu(Condition condition) {
        System.out.println(condition);
        PageInfo<Menu> menuByCondition = menuServiceImpl.getMenuByCondition(condition);
        return ApiResult.success(menuByCondition);
    }

    @GetMapping("/list")
    public ApiResult getMenuList(Condition condition) {
        System.out.println(condition);
        PageInfo<MenuDTO> menuList = menuServiceImpl.getMenuList(condition);
        return ApiResult.success(menuList);
    }

    @PostMapping("/update")
    public ApiResult changeMenu(Menu menu) {
        if (menuServiceImpl.updateByPrimaryKey(menu)) {
            return ApiResult.success();
        }
        return ApiResult.failure();
    }

    @PostMapping("/remove/{id}")
    public ApiResult removeMenu(@PathVariable("id") Long id) {
        if (menuServiceImpl.deleteByPrimaryKey(id)) {
            return ApiResult.success();
        }
        return ApiResult.failure();
    }

    @PostMapping("/removeBatch")
    public ApiResult removeBatch(@RequestBody List<Long> ids) {
        if (menuServiceImpl.removeBatch(ids)) {
            return ApiResult.success();
        }
        return ApiResult.failure();
    }

    @PostMapping("/add")
    public ApiResult addMenu(Menu menu) {
        if (menuServiceImpl.insert(menu)) {
            return ApiResult.success();
        }
        return ApiResult.failure();
    }


}
