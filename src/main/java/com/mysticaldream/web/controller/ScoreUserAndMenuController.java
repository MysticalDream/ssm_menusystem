package com.mysticaldream.web.controller;

import com.mysticaldream.domain.ScoreUserAndMenu;
import com.mysticaldream.domain.User;
import com.mysticaldream.service.ScoreUserAndMenuService;
import com.mysticaldream.web.vo.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * (score_user_and_menu)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/score_user_and_menu")
@RequiredArgsConstructor
public class ScoreUserAndMenuController {
    /**
     * 服务对象
     */

    private final ScoreUserAndMenuService scoreUserAndMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ScoreUserAndMenu selectOne(Integer id) {
        return scoreUserAndMenuService.selectByPrimaryKey(Long.valueOf(id));
    }


    @PostMapping("/score")
    public ApiResult score(ScoreUserAndMenu scoreUserAndMenu, HttpSession httpSession) {
        User loginUser = (User) httpSession.getAttribute("loginUser");
        if (loginUser == null) {
            return ApiResult.failure();
        }
        scoreUserAndMenu.setUid(loginUser.getId());
        if (scoreUserAndMenuService.score(scoreUserAndMenu)) {
            return ApiResult.success();
        }
        return ApiResult.failure();
    }


}
