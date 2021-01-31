package com.recruit.wm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wangm
 * @title: RedisOps
 * @projectName recruit-parent
 * @description: TODO
 * @date 2020/12/201:21
 */
@Component
public class RedisOps {

    @Autowired
    public ValueOperations valueOperations;

    public ObjectMapper objectMapper = new ObjectMapper();

    public void set(String key,String value){

        valueOperations.set(key, value);
    }
    public String get(String key){
        String value = (String) valueOperations.get(key);
        return value;
    }



    public void setObject(String key,Object object){
        if(key != null && object != null){
            try {
                String userStr = object instanceof String  ? (String)object : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
                valueOperations.set(key, userStr);
            }catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getObject(String key,Class clazz){

        String value = (String) valueOperations.get(key);
        Object object = null;
        if(value == null) return null;
        try {
            object = objectMapper.readValue(value, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return object;
        }
    }

}
