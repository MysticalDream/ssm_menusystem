package com.mysticaldream.web.controller;

import com.mysticaldream.common.annotation.RequireLogin;
import com.mysticaldream.common.constant.ProjectVariables;
import com.mysticaldream.common.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 视图控制器
 *
 * @description: ViewController
 * @date: 2022/6/3 19:04
 * @author: MysticalDream
 */
@Controller
public class ViewController {

    @RequestMapping("/login")
    public String login(Model model) {
        System.out.println(ProjectVariables.WORK_PATH);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println(request.getContextPath());
        model.addAttribute("rightActive", false);
        return "page/come";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("rightActive", true);
        return "page/come";
    }

    @GetMapping("/management")
//    @RequireLogin(role = Role.ADMIN)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("page/admin/menuManager");
        return modelAndView;
    }

    @GetMapping("/detailPage")
    public ModelAndView detailPage() {
        ModelAndView modelAndView = new ModelAndView("page/detail");
        return modelAndView;
    }

}
