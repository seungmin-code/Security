package com.min.security.mapper;

import com.min.security.model.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface SecurityMapper {

    @Select("SELECT username, password, role FROM common_user WHERE username = #{username}")
    UserEntity selectByUsername(String username);

    @Insert("INSERT INTO Mms.common_user " +
            "(username, password, create_date, change_date) " +
            "VALUES(#{username}, #{password}, current_timestamp(), current_timestamp());")
    void memberJoin(Map<String, Object> params);

}
