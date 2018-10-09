package com.tiger.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiger.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangshaohu on 9/28/18.
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
    private Logger logger = LoggerFactory.getLogger(WeatherDataService.class);
    @Autowired
    private RestTemplate restTemplate;

    private final String WEATHER_API = "http://wthrcdn.etouch.cn/weather_mini";
    private final Long TIME_OUT = 1800L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_API + "?citykey="+cityId;
        return this.doGetWeatherData(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_API + "?city="+cityName;
        return this.doGetWeatherData(uri);
    }

    private WeatherResponse doGetWeatherData(String uri){
        ValueOperations<String,String> ops = this.stringRedisTemplate.opsForValue();
        final String key = uri;
        String body = null;
        if (!this.stringRedisTemplate.hasKey(key)) {
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            if (response.getStatusCodeValue() == 200) {
                body = response.getBody();
            }
            ops.set(key,body,TIME_OUT, TimeUnit.SECONDS);
        }else{
            body = ops.get(key);
            logger.info("Found key" + key +", value="+ body);
        }
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weather = null;
        try {
            weather = mapper.readValue(body, WeatherResponse.class);
        } catch (IOException e) {
            logger.error(body+" mapper error.");
        }
        return weather;
    }
}
