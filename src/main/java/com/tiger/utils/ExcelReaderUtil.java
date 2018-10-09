package com.tiger.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangshaohu on 10/8/18.
 */
public class ExcelReaderUtil extends ExcelAbstract{

    private boolean isHasTitle = false;

    private Map<String,String> title = new HashMap<>();

    @Override
    public void optRow(int rownum, Map<String, String> rowValueMap) {
        if( rownum ==0 && isHasTitle){
            this.title = rowValueMap;
        }
        rowValueMap.forEach((k,v)-> {
            System.out.println("current Row is :"+rownum+" ,value:"+k+"->"+v);
        });
    }

    public static void main(String[] args) {
        ExcelReaderUtil excel = new ExcelReaderUtil();
        long st = System.currentTimeMillis();
        excel.readOneSheet("/Users/wangshaohu/Downloads/DPS.DPS_FACTORY_AREA20180927.xlsx",1);
        System.out.println(System.currentTimeMillis()-st);
    }
}


