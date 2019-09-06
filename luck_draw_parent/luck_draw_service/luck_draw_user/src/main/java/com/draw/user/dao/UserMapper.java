package com.draw.user.dao;


import com.draw.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Select("select  * from cj_user  where username=#{username}")
    User findByUsername(String username);


    @Select("select head from cj_user where id  in (select  user_id  as uid from cj_draw_user where draw_id=#{id})")
    List<String> findJoinUserHead(@Param("id") String id);


    @Select("select  * from cj_user  where id=#{id}")
    User findUser(@Param("id") String id);
}
