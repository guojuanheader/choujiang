package com.draw.infos.dao;

import com.draw.infos.pojo.UserAndDraw;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserAndDrawMapper extends Mapper<UserAndDraw> {

   // @Select("select user_id from cj_draw_user where draw_id =#{id}")
    //List<String> findUid(@Param("id") String id);

    @Insert("insert into cj_draw_user(id,user_id,draw_id,draw_code) values(#{id},#{uid},#{did},#{drawCode}) ")
    void insertTeam(UserAndDraw userAndDraw);

    @Select("select user_id from cj_draw_user where draw_id =#{did} and draw_code=#{code}")
    String findUid(@Param("code") Long code, @Param("did") String did);
}
