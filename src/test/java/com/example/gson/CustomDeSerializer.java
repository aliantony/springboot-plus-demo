package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-10-28
 * @version  1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomDeSerializer {

    class Result<T> {
        private int code;
        private String message;
        private T data;

        public Result(int code, String message, T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    class DateSerializer implements JsonSerializer<Date> {
        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(dateTime.format(src));
        }
    }

    class ResultDeserializer implements JsonDeserializer<Result> {
        @Override
        public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Result<Object> result = new Result<>(object.getAsJsonPrimitive("CODE").getAsInt(),object.getAsJsonPrimitive("MESSAGE").getAsString(), null);
            return result;
        }
    }


    @Test
    public void test_dateSerializer() {
        MyObject myObject = new MyObject(new Date(), "one");
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).create();
        String json = gson.toJson(myObject);
        String exceptedJson = "{\"date\":\"2019-10-28\",\"name\":\"one\"}";
        assertEquals(exceptedJson, json); // true
    }

    @Test
    public void test_resultDeserializer() {
        //language=JSON
        String json = "{\"CODE\": 400,\"MESSAGE\": \"参数错误\"}";
        Gson gson = new GsonBuilder().registerTypeAdapter(Result.class, new ResultDeserializer()) .create();
        Result result = gson.fromJson(json, Result.class);
        assertEquals(400, result.code); // true
        assertEquals("参数错误", result.message); // true
    }

    class MyObject {
        private Date date;
        private String name;

        public MyObject(Date date, String name) {
            this.date = date;
            this.name = name;
        }

        public MyObject() {
        }
    }
}
