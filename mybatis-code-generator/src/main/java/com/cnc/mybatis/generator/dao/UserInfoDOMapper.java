package com.cnc.mybatis.generator.dao;

import com.cnc.mybatis.generator.dataObject.UserInfoDO;

public interface UserInfoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    int insert(UserInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    int insertSelective(UserInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    UserInfoDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    int updateByPrimaryKeySelective(UserInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Sat Nov 13 16:30:09 CST 2021
     */
    int updateByPrimaryKey(UserInfoDO record);
}