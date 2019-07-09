package com.dj.springboot.mapper;

import com.dj.springboot.pojo.UserVO;

import java.util.List;

public interface UserMapper {

    /**
     * 查询用户列表
     *
     * @param userVO
     * @return UserVO集合
     * @throws Exception
     */
    List<UserVO> selectUserVOList(UserVO userVO) throws Exception;


    /**
     * 查询用户数量
     *
     * @param userVO
     * @return 用户数量
     * @throws Exception
     */
    Integer selectUserVOCount(UserVO userVO) throws Exception;

}
