package com.draw.infos.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cj_draw_user")
public class UserAndDraw {

    @Id
    private String  id; //string 类型得
    private String  uid;
    private String  did;
    private Long   drawCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Long getDrawCode() {
        return drawCode;
    }

    public void setDrawCode(Long drawCode) {
        this.drawCode = drawCode;
    }
}
