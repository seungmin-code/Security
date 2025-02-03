package com.min.security.mapper;

import com.min.security.model.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface SecurityMapper {

    // 아이디 존재여부 조회
    @Select("SELECT username, password, role FROM common_user WHERE username = #{username}")
    UserEntity selectByUsername(String username);

    // 회원가입
    @Insert("INSERT INTO Mms.common_user " +
            "(username, password, create_date, change_date) " +
            "VALUES(#{username}, #{password}, current_timestamp(), current_timestamp());")
    void memberJoin(Map<String, Object> params);

}
