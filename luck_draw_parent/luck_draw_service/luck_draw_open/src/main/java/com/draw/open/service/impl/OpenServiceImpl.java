package com.draw.open.service.impl;


import com.draw.entity.Result;
import com.draw.infos.feign.InfoFeign;
import com.draw.infos.pojo.Info;
import com.draw.open.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OpenServiceImpl implements OpenService {

    @Autowired
    private InfoFeign  infoFeign;
    @Override
    public void open() {
        Date date = new Date();
        List<Info> infos = (List<Info>) infoFeign.findAll().getData();
        for (Info info : infos) {
            if (info.getOpenTime().before(date)){
                //开奖

            }
        }
    }
}
