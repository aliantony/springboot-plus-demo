package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author wangqian
 * created on 2019-10-28
 * @version 1.0.0
 * @program demo1
 * @description Gson学习
 */
@RunWith(MockitoJUnitRunner.class)
public class GsonTest {

    @Test
    public void test_jsonObject_serialization() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 400);
        jsonObject.addProperty("message", "参数错误");
        String toJson = gson.toJson(jsonObject);
        String exceptedJson = "{\"code\":400,\"message\":\"参数错误\"}";
        assertEquals(exceptedJson, toJson); //true
    }

    @Test
    public void testAdd() {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        JsonObject nestJsonObject = new JsonObject();
        nestJsonObject.addProperty("username", "one");
        nestJsonObject.addProperty("score", 99);
        jsonObject.add("data", nestJsonObject);
        String toJson2 = gson.toJson(jsonObject);
        System.out.println(toJson2);
// {"data":{"username":"one","score":99}}
    }

    /**
     * 会报错，默认是LinkedHashMap
     */
    @Test
    public void givenJsonString_whenIncorrectDeserializing() {
        Gson gson = new Gson();
        String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
        List<Person> outputList = gson.fromJson(inputString, List.class);
        outputList.get(0).getId();
    }

    @Test
    public void givenJsonString_whenCorrectDeserializing_() {
        Gson gson = new Gson();
        String inputString = "[{\"id\":1,\"name\":\"one\"},{\"id\":2,\"name\":\"two\"}]";
        Type type = new TypeToken<List<Person>>() {
        }.getType();
        List<Person> outputList = gson.fromJson(inputString, type);
        int id = outputList.get(0).getId();
        assertEquals(1, id); // true
        assertEquals("one", outputList.get(0).getName()); // true
    }

    @Test
    public void test_array() {
        Gson gson = new Gson();
        int[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
        String s = gson.toJson(ints);// [1,2,3,4,5]
        assertEquals("[1,2,3,4,5]", s); // true

        String s1 = gson.toJson(strings);// ["abc", "def", "ghi"]
        assertEquals("[\"abc\",\"def\",\"ghi\"]", s1);
        String[] strings1 = gson.fromJson(s1, String[].class);
        assertEquals(strings.length, strings1.length); // true
        assertEquals(strings[0], strings1[0]); // true

        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);
        assertEquals(1, ints2[0]); // true
        assertEquals(5, ints2[4]); // true
    }

    @Test
    public void test_map() {
        String jsonString = "{'employee.name':'one','employee.salary':10}";
        Gson gson = new Gson();
        Map map = gson.fromJson(jsonString, Map.class);
        assertEquals(2, map.size());
        assertEquals("one", map.get("employee.name"));
        assertEquals(Double.class, map.get("employee.salary").getClass());
    }

    @Test
    public void test_deserialization() {
        String json = "{\"code\":400,\"message\":\"参数错误\"}";
        Result result = new Gson().fromJson(json, Result.class);
        Assert.assertEquals(400, (Object) result.getCode()); // true
        assertEquals("参数错误", result.message); // true
    }

    @Data
    private class Person {
        private Integer id;
        private String name;
    }

    @Data
    private class Result {
        private Integer code;
        private String message;
    }


}
