package com.mysticaldream.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.mysticaldream.domain.Category;
import com.mysticaldream.mapper.CategoryMapper;
import com.mysticaldream.service.CategoryService;

import java.util.List;

/**
 * @description: CategoryServiceImpl 
 * @date: 2022/5/28 22:59 
 * @author: MysticalDream 
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Category record) {
        return categoryMapper.insert(record);
    }

    @Override
    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Category record) {
        return categoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Category record) {
        return categoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.selectAll();
    }

}
