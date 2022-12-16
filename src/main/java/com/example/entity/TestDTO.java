package com.example.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TestDTO {

    public static void main(String[] args) {
        LinkedHashMap<Integer,String> map = new LinkedHashMap<>();
        map.put(0,"张三");
        map.put(1,"123123asd");
        LinkedHashMap<Integer,String> map2 = new LinkedHashMap<>();
        map2.put(0,"张三2");
        map2.put(1,"456456ert");
        List<LinkedHashMap<Integer,String>> list = new ArrayList<>();
        list.add(map);
        list.add(map2);

        ObjectMapper mapper = new ObjectMapper();

        User resultUser = mapper.convertValue(map, User.class);
        System.out.println(resultUser.toString());

    }



}
