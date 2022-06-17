package com.mysticaldream.mapper;

import com.mysticaldream.domain.Condition;
import com.mysticaldream.domain.Menu;
import com.mysticaldream.service.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: MenuMapper
 * @date: 2022/5/28 22:55
 * @author: MysticalDream
 */
public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAllMenuByPageNumSize(
            @Param("condition") Condition condition,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize);

    int deleteMenuBatch(List ids);

    List<MenuDTO> selectDtoList(@Param("key") String key,
                                @Param("pageNum") int pageNum,
                                @Param("pageSize") int pageSize);
}