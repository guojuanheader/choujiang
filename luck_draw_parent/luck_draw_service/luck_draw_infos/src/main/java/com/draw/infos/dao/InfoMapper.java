package com.draw.infos.dao;

import com.draw.infos.pojo.Info;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InfoMapper extends Mapper<Info> {

    @Update("update cj_draw  set join_num=join_num+1  where id=#{did}")
    void update(@Param("did") String did);


    @Select("SELECT id,name,des, open_time as openTime,open_num as openNum,win_num as winNum,open_way as openType,join_num  as joinNum FROM cj_draw where id=#{id}")
    Info findById(@Param("id") String id);

    @Select("select id,name,des, open_time as openTime,open_num as openNum,win_num as winNum,open_way as openType,join_num  as joinNum from cj_draw  where  is_open='0'")
    List<Info> findAll();


    @Update("update cj_draw  set is_open=1,win_num=#{code}  where id=#{id}")
    void updateOpen(@Param("id")String id,@Param("code") Integer code);
}
