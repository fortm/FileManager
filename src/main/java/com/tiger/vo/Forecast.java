package com.tiger.vo;

import java.io.Serializable;

/**
 * Created by wangshaohu on 9/28/18.
 */
public class Forecast implements Serializable{
    private static final long serialVersionUID = 1469381119638477857L;

//"date": "29日星期六",
//        "high": "高温 22℃",
//        "fengli": "<![CDATA[4-5级]]>",
//        "low": "低温 11℃",
//        "fengxiang": "西北风",
//        "type": "晴"
    private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
