package com.draw.open.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface OpenMapper  {


    @Insert("insert  into  cj_win values(#{id},#{uid},#{did},#{winNum})")
    void insertWin(@Param("id") String  id, @Param("uid")String uid, @Param("did")String did, @Param("winNum")Integer winNum);
}
