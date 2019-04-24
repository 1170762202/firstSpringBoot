package com.zlx.firstSpringBoot.constant.response;

import java.util.List;
import java.util.Map;

public class UserResp {
    private List<Map> userData;

    public UserResp(List<Map> datas) {
        this.userData = datas;
    }

    public List<Map> getUserData() {
        return userData;
    }

    public void setUserData(List<Map> userData) {
        this.userData = userData;
    }
}
