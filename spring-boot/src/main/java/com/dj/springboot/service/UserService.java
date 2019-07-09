package com.dj.springboot.service;

import com.dj.springboot.pojo.UserVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface UserService {


    /**
     * 导出用户EXCEL
     *
     * @param userVO
     * @return VOID
     * @throws Exception
     */
    void export(UserVO userVO, HttpServletResponse response) throws Exception;


}
