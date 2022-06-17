package com.mysticaldream.service;

import com.mysticaldream.domain.ScoreUserAndMenu;

/**
 * @description: ScoreUserAndMenuService
 * @date: 2022/5/28 23:02
 * @author: MysticalDream
 */
public interface ScoreUserAndMenuService {


    int deleteByPrimaryKey(Long id);

    int insert(ScoreUserAndMenu record);

    int insertSelective(ScoreUserAndMenu record);

    ScoreUserAndMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScoreUserAndMenu record);

    int updateByPrimaryKey(ScoreUserAndMenu record);

    boolean score(ScoreUserAndMenu scoreUserAndMenu);

}
