package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {

        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception while getting value from redis : ", e);
            return null;
        }
    }

    public void set(String key, Object o, Long timeOut) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonValue = mapper.writeValueAsString(o); // return JsonString
            // Because we have configured redis will have redisString key value then we can only store that.
            redisTemplate.opsForValue().set(key, jsonValue, timeOut, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Exception while setting value to redis : ", e);
        }
    }
}
