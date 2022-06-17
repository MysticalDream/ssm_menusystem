package com.mysticaldream.service.impl;

import com.mysticaldream.domain.Menu;
import com.mysticaldream.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.mysticaldream.mapper.ScoreUserAndMenuMapper;
import com.mysticaldream.domain.ScoreUserAndMenu;
import com.mysticaldream.service.ScoreUserAndMenuService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * @description: ScoreUserAndMenuServiceImpl
 * @date: 2022/5/28 23:02
 * @author: MysticalDream
 */
@Service
public class ScoreUserAndMenuServiceImpl implements ScoreUserAndMenuService {

    @Resource
    private ScoreUserAndMenuMapper scoreUserAndMenuMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return scoreUserAndMenuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(ScoreUserAndMenu record) {
        return scoreUserAndMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(ScoreUserAndMenu record) {
        return scoreUserAndMenuMapper.insertSelective(record);
    }

    @Override
    public ScoreUserAndMenu selectByPrimaryKey(Long id) {
        return scoreUserAndMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(ScoreUserAndMenu record) {
        return scoreUserAndMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ScoreUserAndMenu record) {
        return scoreUserAndMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean score(ScoreUserAndMenu scoreUserAndMenu) {
        Long id = scoreUserAndMenuMapper.vertifyExist(scoreUserAndMenu);
        Menu menu = menuMapper.selectByPrimaryKey(scoreUserAndMenu.getMenuId());
        //但前分数
        BigDecimal score = menu.getScore();
        //打分人数
        Integer scoreCount = menu.getScoreCount();
        //总分
        BigDecimal total = score.multiply(BigDecimal.valueOf(scoreCount));
        BigDecimal scale = total.add(scoreUserAndMenu.getScore()).setScale(1, RoundingMode.FLOOR);
        Integer nextScoreCount = scoreCount + 1;
        BigDecimal nextScore = scale.divide(BigDecimal.valueOf(nextScoreCount), RoundingMode.FLOOR);
        //菜单分数更新
        Menu menu1 = new Menu();
        menu1.setId(menu.getId());
        menu1.setScore(nextScore);
        menu1.setScoreCount(nextScoreCount);
        menuMapper.updateByPrimaryKeySelective(menu1);

        if (id == null) {
            Date date = new Date();
            scoreUserAndMenu.setCreateTime(date);
            scoreUserAndMenu.setUpdateTime(date);
            scoreUserAndMenuMapper.insert(scoreUserAndMenu);
        } else {
            ScoreUserAndMenu scoreUserAndMenu1 = new ScoreUserAndMenu();
            scoreUserAndMenu1.setId(id);
            scoreUserAndMenu1.setScore(scoreUserAndMenu.getScore());
            scoreUserAndMenu1.setUpdateTime(new Date());
            scoreUserAndMenuMapper.updateByPrimaryKeySelective(scoreUserAndMenu1);
        }

        return true;
    }


}
