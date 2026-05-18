package com.example.dailyexerciseauth.mapper;

import com.example.dailyexerciseauth.entity.OrdinaryUser;
import com.example.dailyexerciseauth.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    // 普通用户登录
    @Select("""
        SELECT u.userID, u.userPassword, u.userType, o.userName, o.phoneNumber
        FROM user u
        LEFT JOIN ordinary_user o ON u.userID = o.userID
        WHERE (o.userName = #{userName} OR o.phoneNumber = #{phoneNumber} OR u.userID = #{userID})
          AND u.userPassword = #{userPassword}
          AND u.userType = 1
        """)
    User login(User user);

    // 管理员登录 - 查询管理员表
    @Select("""
        SELECT u.userID, u.userPassword, u.userType, a.adminName as userName, a.adminId as phoneNumber
        FROM user u
        LEFT JOIN administrator a ON u.userID = a.userID
        WHERE (a.adminId = #{userName} OR a.adminName = #{userName} OR u.userID = #{userID})
          AND u.userPassword = #{userPassword}
          AND u.userType = 0
          AND a.adminId IS NOT NULL
        """)
    User adminLogin(User user);

    // 插入用户基础信息
    @Insert("INSERT INTO user (userPassword, userType) VALUES (#{userPassword}, #{userType})")
    int insertUser(User user);

    // 获取刚插入的用户ID
    @Select("SELECT LAST_INSERT_ID()")
    Integer getLastInsertId();

    // 插入普通用户详细信息
    @Insert("""
        INSERT INTO ordinary_user
        (userID, userName, phoneNumber, userMailbox, gender, birthday, registerTime, age, weight)
        VALUES
        (#{userID}, #{userName}, #{phoneNumber}, #{userMailbox}, #{gender}, #{birthday}, #{registerTime}, #{age}, #{weight})
        """)
    int insertOrdinaryUser(OrdinaryUser user);

    // 更新用户密码
    @Update("UPDATE user SET userPassword = #{userPassword} WHERE userID = #{userID}")
    int updateUserPassword(User user);

    // ✅ 修正后的普通用户更新方法
    @Update("""
        UPDATE ordinary_user
        SET userName = #{userName},
            phoneNumber = #{phoneNumber},
            userMailbox = #{userMailbox},
            gender = #{gender},
            birthday = #{birthday},
            age = #{age},
            weight = #{weight}
        WHERE userID = #{userID}
        """)
    int updateOrdinaryUser(OrdinaryUser user);

    @Select("""
        SELECT o.userID, o.userName, o.phoneNumber, o.userMailbox, o.gender, 
               o.birthday, o.registerTime, o.age, o.weight, u.userPassword, u.userType
        FROM ordinary_user o
        JOIN user u ON o.userID = u.userID
        WHERE o.userID = #{userID}
        """)
    OrdinaryUser getOrdinaryUserByUserId(Integer userID);

    @Select("SELECT u.userID, u.userPassword, u.userType FROM user u JOIN ordinary_user o ON u.userID = o.userID WHERE o.phoneNumber = #{phoneNumber}")
    User findUserByPhoneNumber(String phoneNumber);

    @Update("UPDATE user SET userPassword = #{newPassword} WHERE userID = #{userID}")
    int resetPassword(@Param("userID") Integer userID, @Param("newPassword") String newPassword);
}