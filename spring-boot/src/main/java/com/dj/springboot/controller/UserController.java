package com.dj.springboot.controller;

import com.dj.springboot.pojo.*;
import com.dj.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("toExcel")
    public String toExcel() {
        return "excel";
    }


    @GetMapping("/export")
    @ResponseBody
    public String exportUser(UserVO userVO, HttpServletResponse response) {
        try {
            this.userService.export(userVO, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误");

        }
        return "导入成功";
    }


}
