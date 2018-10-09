package com.tiger.vo;

import java.io.Serializable;

/**
 * Created by wangshaohu on 9/28/18.
 */
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = -1365420767878565178L;

    private Weather data;
    private String status;
    private String desc;

    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
