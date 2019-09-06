package com.draw.infos.service;

import com.draw.infos.pojo.Info;
import com.draw.user.pojo.User;

import java.util.List;

public interface InfoService {
    Info findById(String id);

    void update(String did);

    List<Info> findAll();

    void updateOpen(String id);
}
