package com.mysticaldream.service;

import com.mysticaldream.domain.Category;

import java.util.List;

/**
 * @description: CategoryService
 * @date: 2022/5/28 22:59
 * @author: MysticalDream
 */
public interface CategoryService {


    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> getAllCategory();

}
