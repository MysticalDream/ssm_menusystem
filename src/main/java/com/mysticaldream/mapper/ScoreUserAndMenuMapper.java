package com.mysticaldream.mapper;

import com.mysticaldream.domain.ScoreUserAndMenu;

/**
 * @description: ScoreUserAndMenuMapper
 * @date: 2022/5/28 23:02
 * @author: MysticalDream
 */
public interface ScoreUserAndMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScoreUserAndMenu record);

    int insertSelective(ScoreUserAndMenu record);

    ScoreUserAndMenu selectByPrimaryKey(Long id);

    Long vertifyExist(ScoreUserAndMenu scoreUserAndMenu);

    int updateByPrimaryKeySelective(ScoreUserAndMenu record);

    int updateByPrimaryKey(ScoreUserAndMenu record);
}