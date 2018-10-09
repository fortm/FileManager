package com.tiger.service;

import com.tiger.vo.WeatherResponse;

/**
 * Created by wangshaohu on 9/28/18.
 */
public interface WeatherDataService {

    WeatherResponse getDataByCityId(String cityId);

    WeatherResponse getDataByCityName(String cityName);

}
