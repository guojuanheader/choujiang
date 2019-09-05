package com.draw.infos.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cj_draw")
public class Info {

    @Id
    private String id;
    private String  name;
    private Date  openTime;
    private Integer  openNum;
    private Integer winNum;
    private String  openType;
    private String  des;
    private Integer   joinNum;

    public Integer getJoinNum() {
        return joinNum;
    }

    public void setJoinNum(Integer joinNum) {
        this.joinNum = joinNum;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpenNum() {
        return openNum;
    }

    public void setOpenNum(Integer openNum) {
        this.openNum = openNum;
    }

    public Integer getWinNum() {
        return winNum;
    }

    public void setWinNum(Integer winNum) {
        this.winNum = winNum;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
